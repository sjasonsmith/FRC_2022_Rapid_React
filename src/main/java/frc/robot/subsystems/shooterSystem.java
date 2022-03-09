package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class shooterSystem extends SubsystemBase {
    
    private CANSparkMax _shooterMotorOne = new CANSparkMax(20, MotorType.kBrushless);

    private TalonSRX _shooterMotorTwo = new TalonSRX (21);




    public void runShooter(double shooterSpeed) {

        _shooterMotorOne.set(shooterSpeed);

        _shooterMotorTwo.set(ControlMode.PercentOutput, -shooterSpeed);
    
    }

}


