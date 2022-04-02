package frc.robot;

import frc.robot.commands.DefaultDriveCommand;
import frc.robot.subsystems.drivingSystem;
import frc.robot.subsystems.liftSystem;
import frc.robot.subsystems.shooterSystem;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {

  Joystick driveStick = new Joystick(0); //Should be a logitech F310, right USB
  Joystick liftJoyStick = new Joystick(1); //Should be a Logitech Extreme 3D pro.

  //Define Subsystems
  public final drivingSystem m_driving = new drivingSystem();

  private final shooterSystem m_shooting = new shooterSystem();

  private final liftSystem m_lifting = new liftSystem();

  public RobotContainer() {
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Stick Y axis -> forward and backwards movement
    // Stick X axis -> left and right movement
    // Rotate Z axis -> rotation

    // Apply ramp rates by setting them in software, so see input and change the output, 
    // motor controller does not know the difference.

    // Make a ramp rate object and seconds to full rate is 0.75.
    SlewRateLimiter rampRateApplier = new SlewRateLimiter(0.75);

    m_driving.setDefaultCommand(new DefaultDriveCommand(
      m_driving,
            () -> rampRateApplier.calculate(modifyAxis(driveStick.getRawAxis(1)) * m_driving.MAX_VELOCITY_METERS_PER_SECOND),
            () -> rampRateApplier.calculate(modifyAxis(driveStick.getRawAxis(0)) * m_driving.MAX_VELOCITY_METERS_PER_SECOND),
            () -> rampRateApplier.calculate(modifyAxis(driveStick.getRawAxis(4)) * m_driving.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND)
    ));
    // Configure the button bindings
    configureButtonBindings();
  }


  private void configureButtonBindings() {
    //Zero Gyroscope as a just in case
    new JoystickButton(driveStick, 7).whenPressed(() -> m_driving.zeroGyroscope());

    //Shoot Forward At 75%
    new JoystickButton(driveStick, 7).whenPressed(() -> m_driving.zeroGyroscope());
    new JoystickButton(driveStick, 6).whileHeld(() -> m_shooting.runShooter(0.99)).whenReleased(() -> m_shooting.stopSystem());
    new JoystickButton(driveStick, 5).whenPressed(() -> m_shooting.runShooterAssist()).whenReleased(() -> m_shooting.stopAssist());

    // When DPAD-UP is pressed run the collector up
    new JoystickButton(driveStick, 4).whenPressed(() -> m_shooting.moveCollector(false)).whenReleased(() -> m_shooting.stopCollectorMovement());
    // ==VERIFY BUTTON MAPPINGS!!!==
    // When DPAD-DOWN is pressed run the collector down
    new JoystickButton(driveStick, 3).whenPressed(() -> m_shooting.moveCollector(true)).whenReleased(() -> m_shooting.stopCollectorMovement());

    // Collect
    new JoystickButton(driveStick, 1).whenPressed(() -> m_shooting.collect(true)).whenReleased(() -> m_shooting.stopCollectorPower());
    
    // Collect, but backwards...

    new JoystickButton(driveStick, 2).whenPressed(() -> m_shooting.collect(false)).whenReleased(() -> m_shooting.stopCollectorPower());


    // Lift Arms!

    // new POVButton(driveStick, 0).whenPressed(() -> m_lifting.liftArmsUp()).whenReleased(() -> m_lifting.stopArms());
    // new POVButton(driveStick, 180).whenPressed(() -> m_lifting.liftArmsDown()).whenReleased(() -> m_lifting.stopArms());
    new POVButton(driveStick, 90).whileHeld(() -> m_lifting.tiltArmsUp()).whenReleased(() -> m_lifting.stopArms());
    // Tilt Arms Down
    new POVButton(driveStick, 270).whileHeld(() -> m_lifting.tiltArmsDown()).whenReleased(() -> m_lifting.stopArms());
    // Winch Arms Up
    new POVButton(driveStick, 0).whileHeld(() -> m_lifting.winchArmsUp()).whenReleased(() -> m_lifting.stopWinchArms());
    // Winch Arms Down
    new POVButton(driveStick, 180).whileHeld(() -> m_lifting.winchArmsDown()).whenReleased(() -> m_lifting.stopWinchArms());
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