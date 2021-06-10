package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.collectorShooterSystem;
import frc.robot.subsystems.drivingSystem;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  // private drivingSystem driveSys = new drivingSystem();
  // private final collectorShooterSystem _shooter = new collectorShooterSystem();
  Joystick driveStick = new Joystick(0);
  int dpadDir = driveStick.getPOV(0);
  Boolean liftUp = (dpadDir == 0) ? true : false; //ADD DPAD UP
  Boolean liftDown = (dpadDir == 180) ? true : false; //ADD DPAD DOWN
  // CommandScheduler.getInstance().setDefaultCommand(collectorShooterSystem, ballCounter);

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    // driveSys.resetEncoders();
    SmartDashboard.putNumber("P", 0);
    SmartDashboard.putNumber("I", 0);
    SmartDashboard.putNumber("D", 0);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run(); 
}

  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
       // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    
    
    
    
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
  }
}

  @Override
  public void teleopPeriodic() {
    
    
   



  }

}