package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Spark;
// import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class collectorShooterSystem extends SubsystemBase
 {

    private CANSparkMax _collectVert = new CANSparkMax((13), MotorType.kBrushless);
    private CANSparkMax _shooterMotorLeft = new CANSparkMax((14), MotorType.kBrushless);
    private CANSparkMax _shooterMotorRight = new CANSparkMax((15), MotorType.kBrushless); 
    private TalonSRX _ColectorMotor = new TalonSRX(11);
    private TalonSRX _liftmotor = new TalonSRX(12);
    private DigitalInput bottomSensor = new DigitalInput(0);
    private DigitalInput topSensor = new DigitalInput(1);
    private Spark _magMotor1 = new Spark(0);
    private Spark _magMotor2 = new Spark(1);
    private final double rampSeconds = 0.15;
    // _shooterMotorLeft.setOpenLoopRampRate(rampSeconds);
    // _shooterMotorRight.setOpenLoopRampRate(rampSeconds);
    // _collectVert.setOpenLoopRampRate(rampSeconds);
    int numbOfBalls = 0;
    int bottomSensorLock = 1;
    int topSensorLock = 1;
        double collectorSpeed = 0.4;
    double shooterSpeed = 0.1;
    Boolean isGettingBall = false;

    // SmartDashboard.putBoolean("BottomSensor", bottomSensor.get());
    // SmartDashboard.putBoolean("TopSensor", topSensor.get());
    // SmartDashboard.putNumber("Amount Of Balls In", numbOfBalls);
    // SmartDashboard.putNumber("bottomSensorLock", bottomSensorLock);        
    // SmartDashboard.putBoolean("isGettingBall", isGettingBall);  
    // SmartDashboard.putNumber("Shooter Speed", shooterSpeed * 100); 

    @Override
    public void periodic() {
        //public void ballCounter(){
            //Call this every tick to count and move balls
            if (bottomSensorLock == 0 && !bottomSensor.get()) {
                numbOfBalls ++;
                bottomSensorLock = 1;
                isGettingBall = true;
            }
            else if (bottomSensorLock == 1 && bottomSensor.get()) {
                bottomSensorLock = 0;
                isGettingBall = false;
            }
        
                if (topSensorLock == 0 && topSensor.get()) {
                    numbOfBalls --;
                    topSensorLock = 1;
                }
                else if (topSensorLock == 1 && !topSensor.get()) {
                    topSensorLock = 0;
                }
    
    }


    public void liftCollector() {
        _liftmotor.set(ControlMode.PercentOutput, collectorSpeed);
    }

    public void lowerCollector() {
        _liftmotor.set(ControlMode.PercentOutput, -collectorSpeed);
    }

  public void shooterSpeedUp() {
    shooterSpeed = shooterSpeed + 0.1;
  }

  public void shooterSpeedDowm() {
    shooterSpeed = shooterSpeed - 0.1;
  }

// Memento
//   else if (isChangingSpeed == 1) {
//           isChangingSpeed = 0;
//         }




    public void shootBalls() {
        _shooterMotorLeft.set(-shooterSpeed);
        _shooterMotorRight.set(shooterSpeed);
        _magMotor2.set(0.4);
}

    public void collectBalls() {
        _ColectorMotor.set(ControlMode.PercentOutput, -0.5);
        _collectVert.set(-0.5);

        if (numbOfBalls == 0) {
            _magMotor1.set(-0.4);
        }
        else if (numbOfBalls >= 1 && topSensor.get()) {
            _magMotor1.set(-0.4);
            _magMotor2.set(0.4);
        }
        else if (numbOfBalls >= 1 && !topSensor.get()){
            _magMotor1.set(-0.4);
            _magMotor2.set(0);
        }
        else {
            _magMotor1.set(0);
            _magMotor2.set(0);
        }
    }

}
