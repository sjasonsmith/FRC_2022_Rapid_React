package frc.robot;

// import frc.robot.commands.driveJoystick;
// import frc.robot.commands.liftCollector;
import frc.robot.subsystems.collectorShooterSystem;
import frc.robot.subsystems.drivingSystem;
import edu.wpi.first.wpilibj.Joystick;
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

   // Driving
  
  // double _leftjoyforwardRaw = -driveStick.getRawAxis(1);
  // double _rightsidejoysideRaw = driveStick.getRawAxis(4);
  // double forward = (_leftjoyforwardRaw * 0.5);
  // double rotate = (_rightsidejoysideRaw * 0.5);
  // Boolean squareInput = true;

  //Define Subsystems
  private final collectorShooterSystem _shooter = new collectorShooterSystem();
  private final drivingSystem _driving = new drivingSystem();

  public RobotContainer() {
    configureButtonBindings();

    // _driving.setDefaultCommand(new driveJoystick());



    _driving.setDefaultCommand(new RunCommand(() -> _driving.setDriveParams((-driveStick.getRawAxis(1) * 0.5), (driveStick.getRawAxis(4) * 0.5), true), _driving));
    
  }


private void configureButtonBindings() {
  int dpadDir = driveStick.getPOV(0);
    
  
 
  
    
    
    
    
    
    // boolean 
    Boolean liftUp = (dpadDir == 0) ? true : false; //ADD DPAD UP
    Boolean liftDown = (dpadDir == 180) ? true : false; //ADD DPAD DOWN
    
    //Collector, Left Bumper
      new JoystickButton(driveStick, 5).whenPressed(() -> _shooter.collectBalls()).whenReleased(() -> _shooter.stopAllMotors());

      if (liftUp) {
        _shooter.liftCollector();
      }    
      else if (liftDown) {
        _shooter.lowerCollector();
      }
      else {
        _shooter.stopAllMotors();
      }
    
    // shootButton = new JoystickButton(driveStick, 6);



  }
}