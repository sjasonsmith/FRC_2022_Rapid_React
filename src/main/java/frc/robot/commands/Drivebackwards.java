package frc.robot.commands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivingSystem;

import java.util.function.DoubleSupplier;

public class Drivebackwards extends CommandBase {

    // From what I can see, the values need to be read globally from the defiitions below, and we define them by just calling Drivebackwards and passing
    //  the values.
    private final drivingSystem m_drivetrainSubsystem;
    private Timer movementTimer = new Timer();

    private double m_translationXSupplier;
    private double m_translationYSupplier;
    private double m_rotationSupplier;

    public Drivebackwards(drivingSystem drivetrainSubsystem) {
        // The 'script'
        
        // Reset and start timer, 'start of autonomous'
        movementTimer.reset();
        movementTimer.start();

        // Set drivetrain values
        this.m_drivetrainSubsystem = drivetrainSubsystem;
        this.m_translationXSupplier = 0;
        this.m_translationYSupplier = 0;
        this.m_rotationSupplier = 0;
        addRequirements(drivetrainSubsystem);

        // If less then 2 seconds
        if (movementTimer.get() < 2.0) {
            this.m_translationXSupplier = 0;
            this.m_translationYSupplier = 0;
            this.m_rotationSupplier = 0.5;
        }

        // 
    }
}