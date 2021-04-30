package frc.robot;

import frc.robot.subsystems.collectorShooterSystem;
import edu.wpi.first.wpilibj.Joystick;

/*
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer {

  Joystick driveStick = new Joystick(0);

  //Define Subsystems
  private final collectorShooterSystem _shooter = new collectorShooterSystem();


  public RobotContainer() {
    configureButtonBindings();




  }

private void configureButtonBindings() {
    // double _leftjoyforwardRaw = -_joystick1.getRawAxis(1);
    // double _rightsidejoysideRaw = _joystick1.getRawAxis(4);
    // new JoystickButton(driveStick, Button.)
}
}