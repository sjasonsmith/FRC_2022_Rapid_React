package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class liftSystem extends SubsystemBase {
    
    private TalonSRX _leftTiltMotor = new TalonSRX(Constants.leftTiltMotorCanID);

    private TalonSRX _rightTiltMotor = new TalonSRX(Constants.rightTiltMotorCanID);

    private CANSparkMax _winchMotor = new CANSparkMax(Constants.winchCanID, MotorType.kBrushless);

    public void tiltArmsUp() {
       _leftTiltMotor.set(ControlMode.PercentOutput, Constants.liftArmSpeed);
       _rightTiltMotor.set(ControlMode.PercentOutput, Constants.liftArmSpeed); 
    }

    public void tiltArmsDown() {
        _leftTiltMotor.set(ControlMode.PercentOutput, -Constants.liftArmSpeed);
        _rightTiltMotor.set(ControlMode.PercentOutput, -Constants.liftArmSpeed);
    }

    public void winchArmsUp() {
        _winchMotor.set(0.3);
    }

    public void winchArmsDown() {
        _winchMotor.set(-0.3);
    }

    public void stopArms() {
        _leftTiltMotor.set(ControlMode.PercentOutput, 0);
        _rightTiltMotor.set(ControlMode.PercentOutput, 0);    
    }


    public void stopWinchArms() {
        _winchMotor.set(0);
    }
}