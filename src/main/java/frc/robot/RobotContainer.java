package frc.robot;

import frc.robot.commands.liftCollector;
import frc.robot.subsystems.collectorShooterSystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

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
  private JoystickButton collectorButton;
  private JoystickButton shootButton;


  public RobotContainer() {
    configureButtonBindings();

    collectorButton.whenPressed(new liftCollector());

  }

private void configureButtonBindings() {
  int dpadDir = driveStick.getPOV(0);
    double _leftjoyforwardRaw = -driveStick.getRawAxis(1);
    double _rightsidejoysideRaw = driveStick.getRawAxis(4);
    // boolean 
    Boolean liftUp = (dpadDir == 0) ? true : false; //ADD DPAD UP
    Boolean liftDown = (dpadDir == 180) ? true : false; //ADD DPAD DOWN
    // JoystickButton liftUp = new JoystickButton(driveStick, driveStick.getPOV(0));
    // JoystickButton liftDown = new JoystickButton(driveStick, dpadDir == 180);
    collectorButton = new JoystickButton(driveStick, 5);
    shootButton = new JoystickButton(driveStick, 6);

  }
}