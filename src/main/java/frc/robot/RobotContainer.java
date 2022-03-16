package frc.robot;

import frc.robot.commands.DefaultDriveCommand;
import frc.robot.subsystems.drivingSystem;
import frc.robot.subsystems.shooterSystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {

  Joystick driveStick = new Joystick(0); //Should be a logitech F310, right USB
  Joystick commandStick = new Joystick(1); //Should be a Logitech Extreme 3D pro.

  //Define Subsystems
  private final drivingSystem m_driving = new drivingSystem();

  private final shooterSystem m_shooting = new shooterSystem();

  public RobotContainer() {
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Stick Y axis -> forward and backwards movement
    // Stick X axis -> left and right movement
    // Rotate Z axis -> rotation

    

    m_driving.setDefaultCommand(new DefaultDriveCommand(
      m_driving,
            () -> modifyAxis(driveStick.getRawAxis(1)) * m_driving.MAX_VELOCITY_METERS_PER_SECOND,
            () -> modifyAxis(driveStick.getRawAxis(0)) * m_driving.MAX_VELOCITY_METERS_PER_SECOND,
            () -> -modifyAxis(driveStick.getRawAxis(4)) * m_driving.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    ));


    //Zero Gyroscope as a just in case
    new JoystickButton(driveStick, 7).whenPressed(() -> m_driving.zeroGyroscope());

    //Shoot Forward At 75%
    new JoystickButton(commandStick, 1).whenPressed(() -> m_shooting.runShooter(0.75)).whenReleased(() -> m_shooting.runShooter(0.0));


    //Shoot Backwards At 75%
    new JoystickButton(commandStick, 7).whenPressed(() -> m_shooting.runShooter(-0.75)).whenReleased(() -> m_shooting.runShooter(0.0));

    // When DPAD-UP is pressed run the collector up
    new edu.wpi.first.wpilibj2.command.button.POVButton(driveStick, 0).whenPressed(() -> m_shooting.moveShooter(true)).whenReleased(() -> m_shooting.stopShooter());

    // When DPAD-DOWN is pressed run the collector down
    new edu.wpi.first.wpilibj2.command.button.POVButton(driveStick, 270).whenPressed(() -> m_shooting.moveShooter(false)).whenReleased(() -> m_shooting.stopShooter());


    // Configure the button bindings
    configureButtonBindings();
  }


  private void configureButtonBindings() {

  }

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  private static double modifyAxis(double value) {
    // Deadband
    value = deadband(value, 0.15);

    // Square the axis
    value = (value * value * value);
    
    //Limit to 50%
    value = value * 0.5;

    return value;
  }


  

}