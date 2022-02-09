package frc.robot.subsystems;

import com.ctre.phoenix.sensors.WPI_CANCoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class drivingSystem extends SubsystemBase {
    
    private final CANSparkMax _frontLeftDrive = new CANSparkMax((1), MotorType.kBrushless);
    private final CANSparkMax _frontLeftSteer = new CANSparkMax((2), MotorType.kBrushless);
    private final CANSparkMax _backLeftDrive = new CANSparkMax((3), MotorType.kBrushless);
    private final CANSparkMax _backLeftSteer = new CANSparkMax((4), MotorType.kBrushless);
    private final CANSparkMax _backRightDrive = new CANSparkMax((5), MotorType.kBrushless);
    private final CANSparkMax _backRightSteer = new CANSparkMax((6), MotorType.kBrushless);
    private final CANSparkMax _frontRightDrive = new CANSparkMax((7), MotorType.kBrushless);
    private final CANSparkMax _frontRightSteer = new CANSparkMax((8), MotorType.kBrushless);

    private WPI_CANCoder _frontLeftCanCoder = new WPI_CANCoder(0); //Front Left CanCoder At CAN Address 0
    private WPI_CANCoder _backLeftCanCoder = new WPI_CANCoder(0); //Back Left CanCoder At CAN Address 0
    private WPI_CANCoder _backRightCanCoder = new WPI_CANCoder(0); //Back Right CanCoder At CAN Address 0
    private WPI_CANCoder _frontRightCanCoder = new WPI_CANCoder(0); //Front Right CanCoder At CAN Address 0 
    
    // double encoderInches = 0;

    public double _frontLeftEncoderRaw = _frontLeftCanCoder.getPosition(); //Get the (NOT ABSOLUTE) Position of the CanCoder
    public double _frontRightEncoderRaw = _frontRightCanCoder.getPosition(); //Get the (NOT ABSOLUTE) Position of the CanCoder
    public double _backLeftEncoderRaw = _backLeftCanCoder.getPosition(); //Get the (NOT ABSOLUTE) Position of the CanCoder
    public double _backRightEncoderRaw = _backRightCanCoder.getPosition(); //Get the (NOT ABSOLUTE) Position of the CanCoder

    // @Override
    public void periodic() {

    }

    public void resetCanCoders() {
        _frontLeftCanCoder.setPosition(0.0);
        _frontRightCanCoder.setPosition(0.0);
        _backRightCanCoder.setPosition(0.0);
        _backLeftCanCoder.setPosition(0.0);
    }

    public void resetOtherEncoders() {

    }

    //Circumfrence (Distance Traveled By Rotation) is C=d*Pi

    

    public void driveDistance(double setpoint) {
    }

    public void stopAllMotors() {
    }

}
