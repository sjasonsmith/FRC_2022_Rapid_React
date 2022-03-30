package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class liftSystem extends SubsystemBase {
    
    private CANSparkMax _leftLiftMotor = new CANSparkMax(Constants.leftLiftMotorCanID, MotorType.kBrushless);

    // private CANSparkMax _rightLiftMotor = new CANSparkMax(Constants.rightLiftMotorCanID, MotorType.kBrushless);

    public void liftArmsUp() {
       _leftLiftMotor.set(Constants.liftArmSpeed); 
    }

    public void liftArmsDown() {
        _leftLiftMotor.set(-Constants.liftArmSpeed); 
    }

    public void stopArms() {
        _leftLiftMotor.set(0); 
    }


}