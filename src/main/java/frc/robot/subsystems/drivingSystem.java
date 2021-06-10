package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.commands.driveJoystick;
import frc.robot.commands.resetEncoders;

public class drivingSystem extends SubsystemBase {
    
    private final CANSparkMax _leftBackCanSparkMax = new CANSparkMax((1), MotorType.kBrushless);
    private final CANSparkMax _leftFrontCanSparkMax = new CANSparkMax((4), MotorType.kBrushless);
    private final CANSparkMax _rightBackCanSparkMax = new CANSparkMax((2), MotorType.kBrushless);
    private final CANSparkMax _rightFrontCanSparkMax = new CANSparkMax((3), MotorType.kBrushless);
    private final SpeedControllerGroup m_LeftMotors = new SpeedControllerGroup(_leftBackCanSparkMax, _leftFrontCanSparkMax);
    private final SpeedControllerGroup m_RightMotors = new SpeedControllerGroup(_rightBackCanSparkMax, _rightFrontCanSparkMax);
    private final DifferentialDrive m_Drive = new DifferentialDrive(m_LeftMotors, m_RightMotors);
    CANEncoder leftBack_encoder = _leftBackCanSparkMax.getEncoder();
    CANEncoder rightBack_encoder = _rightBackCanSparkMax.getEncoder();
    double encoderInches = 0;
    int resetEncoders = 0;
    double kP, kI, kD; 
    edu.wpi.first.wpilibj.controller.PIDController  pid = new edu.wpi.first.wpilibj.controller.PIDController(kP, kI, kD);
    

    // @Override
    // public void initialize() {
    //     leftBack_encoder.setPosition(0.0);
    //     rightBack_encoder.setPosition(0.0);
    //     encoderInches = 0;
    // }


    // @Override
    public void periodic() {
       
        if (resetEncoders == 0) {
            resetEncoders();
            resetEncoders = 1;
        }

        SmartDashboard.putNumber("Left Encoder RAW", leftBack_encoder.getPosition());
    SmartDashboard.putNumber("Right Encoder RAW", rightBack_encoder.getPosition());

    kP = SmartDashboard.getNumber("P", 0);
    SmartDashboard.putNumber("Internal P", kP);

    kI = SmartDashboard.getNumber("I", 0);
    SmartDashboard.putNumber("Internal I", kI);

    kD = SmartDashboard.getNumber("D", 0);
    SmartDashboard.putNumber("Internal D", kD);

    SmartDashboard.putNumber("Left Encoder_Graph", leftBack_encoder.getPosition());
    SmartDashboard.putNumber("Right Encoder_Graph", rightBack_encoder.getPosition());

    encoderInches = ((rightBack_encoder.getPosition() * Math.PI) / 1.5) * -1;
    SmartDashboard.putNumber("Right Encoder Inches Traveled", encoderInches);



    }

    



    public void setDriveParams(double forward, double rotate, Boolean squareInput) {
        _leftBackCanSparkMax.setIdleMode(IdleMode.kBrake);
        _leftFrontCanSparkMax.setIdleMode(IdleMode.kBrake);
        _rightBackCanSparkMax.setIdleMode(IdleMode.kBrake);
        _rightFrontCanSparkMax.setIdleMode(IdleMode.kBrake);    
        
        m_Drive.arcadeDrive(forward * 1.5, ((forward < -0.1 || forward > 0.1) ? rotate*1.5 : rotate), squareInput);
        SmartDashboard.putNumber("JOY", forward);    
        
    }

    public void resetEncoders() {
        leftBack_encoder.setPosition(0.0);
        rightBack_encoder.setPosition(0.0);
        encoderInches = 0;
    }

    //Add encoder data processing, so Encoder Ticks -> Shaft Rotations -> Gearbox rotations -> Inches Traveled

    //Aprox 9.5 'ticks' is one revolution on the Wheel

    //5 Inch Wheel at one revolution is 5*pi is inches? So in one rotation it would be 15.7 inches 
    //Circumfrence (Distance Traveled By Rotation) is C=d*Pi

    

        public void driveDistance(double setpoint) {
            SmartDashboard.putBoolean("driveDistance", true);
            m_Drive.arcadeDrive(pid.calculate(encoderInches, setpoint), 0);
            
        }

        public void stopAllMotors() {
            SmartDashboard.putBoolean("driveDistance", false);
            m_Drive.arcadeDrive(0, 0);
        }

}
