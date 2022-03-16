package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.swervedrivespecialties.swervelib.Mk4iSwerveModuleHelper;
import com.swervedrivespecialties.swervelib.SdsModuleConfigurations;
import com.swervedrivespecialties.swervelib.SwerveModule;
import frc.robot.Constants;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class drivingSystem extends SubsystemBase {
    
    public static final double MAX_VOLTAGE = 12.0;  

    public static final double MAX_VELOCITY_METERS_PER_SECOND = 6380.0 / 60.0 *
      SdsModuleConfigurations.MK4I_L2.getDriveReduction() *
      SdsModuleConfigurations.MK4I_L2.getWheelDiameter() * Math.PI;

    //Define Modules

    public static final double MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND = MAX_VELOCITY_METERS_PER_SECOND /
          Math.hypot(Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0);

    private final SwerveDriveKinematics m_kinematics = new SwerveDriveKinematics(
        //   Front left
          new Translation2d(Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0),
          // Front right
          new Translation2d(Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0),
          // Back left
          new Translation2d(-Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0),
          // Back right
          new Translation2d(-Constants.DRIVETRAIN_TRACKWIDTH_METERS / 2.0, -Constants.DRIVETRAIN_WHEELBASE_METERS / 2.0)
  );
  
    private final AHRS navxIMU = new AHRS(SerialPort.Port.kMXP); //Define NavX

    //Initialize Swerve Modules

    private final SwerveModule m_frontLeftModule;
    private final SwerveModule m_frontRightModule;
    private final SwerveModule m_backLeftModule;
    private final SwerveModule m_backRightModule;

    private ChassisSpeeds m_chassisSpeeds = new ChassisSpeeds(0.0, 0.0, 0.0);

    public drivingSystem() {
        ShuffleboardTab tab = Shuffleboard.getTab("Drivetrain");
        // There are 4 methods you can call to create your swerve modules.
    // The method you use depends on what motors you are using.
    //
    // Mk3SwerveModuleHelper.createFalcon500(...)
    //   Your module has two Falcon 500s on it. One for steering and one for driving.
    //
    // Mk3SwerveModuleHelper.createNeo(...)  <--- What we want!
    //   Your module has two NEOs on it. One for steering and one for driving.
    //
    // Mk3SwerveModuleHelper.createFalcon500Neo(...)
    //   Your module has a Falcon 500 and a NEO on it. The Falcon 500 is for driving and the NEO is for steering.
    //
    // Mk3SwerveModuleHelper.createNeoFalcon500(...)
    //   Your module has a NEO and a Falcon 500 on it. The NEO is for driving and the Falcon 500 is for steering.
    //
    // Similar helpers also exist for Mk4 modules using the Mk4SwerveModuleHelper class.

    
    m_frontLeftModule = Mk4iSwerveModuleHelper.createNeo(
        // This parameter is optional, but will allow you to see the current state of the module on the dashboard.
        tab.getLayout("Front Left Module", BuiltInLayouts.kList)
            .withSize(2, 4)
            .withPosition(0, 0),
        // This can either be STANDARD or FAST depending on your gear configuration (This seems to only apply to mark 3's, an MK4 equiv is L3, closest anyway)
        Mk4iSwerveModuleHelper.GearRatio.L2,
        // This is the ID of the drive motor
        Constants.FRONT_LEFT_MODULE_DRIVE_MOTOR,
        // This is the ID of the steer motor
        Constants.FRONT_LEFT_MODULE_STEER_MOTOR,
        // This is the ID of the steer encoder
        Constants.FRONT_LEFT_MODULE_STEER_ENCODER,
        // This is how much the steer encoder is offset from true zero (In our case, zero is facing straight forward)
        Constants.FRONT_LEFT_MODULE_STEER_OFFSET
    );

    // We will do the same for the other modules
    m_frontRightModule = Mk4iSwerveModuleHelper.createNeo(
            tab.getLayout("Front Right Module", BuiltInLayouts.kList)
                    .withSize(2, 4)
                    .withPosition(2, 0),
            Mk4iSwerveModuleHelper.GearRatio.L2,
            Constants.FRONT_RIGHT_MODULE_DRIVE_MOTOR,
            Constants.FRONT_RIGHT_MODULE_STEER_MOTOR,
            Constants.FRONT_RIGHT_MODULE_STEER_ENCODER,
            Constants.FRONT_RIGHT_MODULE_STEER_OFFSET
    );

    m_backLeftModule = Mk4iSwerveModuleHelper.createNeo(
            tab.getLayout("Back Left Module", BuiltInLayouts.kList)
                    .withSize(2, 4)
                    .withPosition(4, 0),
            Mk4iSwerveModuleHelper.GearRatio.L2,
            Constants.BACK_LEFT_MODULE_DRIVE_MOTOR,
            Constants.BACK_LEFT_MODULE_STEER_MOTOR,
            Constants.BACK_LEFT_MODULE_STEER_ENCODER,
            Constants.BACK_LEFT_MODULE_STEER_OFFSET
    );

    m_backRightModule = Mk4iSwerveModuleHelper.createNeo(
            tab.getLayout("Back Right Module", BuiltInLayouts.kList)
                    .withSize(2, 4)
                    .withPosition(6, 0),
            Mk4iSwerveModuleHelper.GearRatio.L2,
            Constants.BACK_RIGHT_MODULE_DRIVE_MOTOR,
            Constants.BACK_RIGHT_MODULE_STEER_MOTOR,
            Constants.BACK_RIGHT_MODULE_STEER_ENCODER,
            Constants.BACK_RIGHT_MODULE_STEER_OFFSET
    );

  }
    

    // @Override
    public void periodic() {

        SwerveModuleState[] states = m_kinematics.toSwerveModuleStates(m_chassisSpeeds);
    SwerveDriveKinematics.desaturateWheelSpeeds(states, MAX_VELOCITY_METERS_PER_SECOND);

    m_frontLeftModule.set(states[0].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[0].angle.getRadians());
    m_frontRightModule.set(states[1].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[1].angle.getRadians());
    m_backLeftModule.set(states[2].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[2].angle.getRadians());
    m_backRightModule.set(states[3].speedMetersPerSecond / MAX_VELOCITY_METERS_PER_SECOND * MAX_VOLTAGE, states[3].angle.getRadians());       
    }

    public void zeroGyroscope() {
        // FIXME Uncomment if you are using a NavX
       navxIMU.zeroYaw();
      }

    public Rotation2d getGyroscopeRotation() {
    // FIXME Uncomment if you are using a NavX
       if (navxIMU.isMagnetometerCalibrated()) {
         // We will only get valid fused headings if the magnetometer is calibrated
         return Rotation2d.fromDegrees(navxIMU.getFusedHeading());
       }
    
       // We have to invert the angle of the NavX so that rotating the robot counter-clockwise makes the angle increase.
       return Rotation2d.fromDegrees(360.0 - navxIMU.getYaw());
    
    }
    
    public void drive(ChassisSpeeds chassisSpeeds) {
        m_chassisSpeeds = chassisSpeeds;
    }
  
    public void resetOtherEncoders() {

    }

    //Circumfrence (Distance Traveled By Rotation) is C=d*Pi

    

    public void driveDistance(double setpoint) {
    }

    public void stopAllMotors() {
    }

}
