package frc.robot;

import frc.robot.commands.DefaultDriveCommand;
import frc.robot.subsystems.drivingSystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {

  Joystick driveStick = new Joystick(0);

  //Define Subsystems
  private final drivingSystem m_driving = new drivingSystem();

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