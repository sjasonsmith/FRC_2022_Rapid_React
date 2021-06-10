package frc.robot;

import frc.robot.subsystems.collectorShooterSystem;
import frc.robot.subsystems.drivingSystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.Button;
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
  private final drivingSystem _driving = new drivingSystem();

  public RobotContainer() {
    configureButtonBindings();

    // _driving.setDefaultCommand(new driveJoystick());



    _driving.setDefaultCommand(new RunCommand(() -> _driving.setDriveParams((-driveStick.getRawAxis(1) * 0.5), (driveStick.getRawAxis(4) * 0.5), true), _driving));
    
  }


private void configureButtonBindings() {

      new JoystickButton(driveStick, 5).whileHeld(() -> _shooter.collectBalls()).whenReleased(() -> _shooter.stopAllMotors());

      new JoystickButton(driveStick, 3).whileHeld(() -> _driving.driveDistance(10)).whenReleased(() -> _driving.stopAllMotors());


      // Lift And Lower Collector
      new edu.wpi.first.wpilibj2.command.button.POVButton(driveStick, 0).whenPressed(() -> _shooter.liftCollector()).whenReleased(() -> _shooter.stopLiftMotors());
    
      new edu.wpi.first.wpilibj2.command.button.POVButton(driveStick, 180).whenPressed(() -> _shooter.lowerCollector()).whenReleased(() -> _shooter.stopLiftMotors());


      // shootButton = new JoystickButton(driveStick, 6);
      // Shooter

      new edu.wpi.first.wpilibj2.command.button.POVButton(driveStick, 90).whenPressed(() -> _shooter.shooterSpeedUp());

      new edu.wpi.first.wpilibj2.command.button.POVButton(driveStick, 270).whenPressed(() -> _shooter.shooterSpeedDowm());

      new JoystickButton(driveStick, 6).whileHeld(() -> _shooter.shootBalls()).whenReleased(() -> _shooter.stopShooterMotors());


  }
}