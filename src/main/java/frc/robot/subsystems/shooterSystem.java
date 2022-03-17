package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class shooterSystem extends SubsystemBase {
    
    private CANSparkMax _collectorPower = new CANSparkMax(Constants.collectorPowerCanID, MotorType.kBrushless);

    private CANSparkMax _shooterPower = new CANSparkMax(Constants.shooterPowerCanID, MotorType.kBrushless);

    private CANSparkMax _shooterAssist = new CANSparkMax(Constants.shooterAssistCanID, MotorType.kBrushless);

    private TalonSRX _collectorMovement = new TalonSRX (Constants.collectorMovementCanID);

    private double startTime;

    // Define the I2C Port
    private final I2C.Port i2cPort = I2C.Port.kOnboard;

    // Define Color Sensor, it is present on he I2C port.
    private final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
    private final ColorMatch m_colorMatcher = new ColorMatch();

   /**
   * Note: Any example colors should be calibrated as the user needs, these
   * are here as a basic example. ===CALIBARATE LATER===
   */
    private final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
    private final Color kRedTarget = new Color(0.561, 0.232, 0.114);

    Color detectedColor = m_colorSensor.getColor();

    String colorString;
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    public void runShooter(double shooterSpeed) {

        // Experimenting with timing.
        // // The time is 0MS
        // startTime = System.currentTimeMillis(); 

        // boolean isTimeSet = true;

        // while (isTimeSet) {

            _shooterPower.set(-shooterSpeed);

            // // IF NOW (5MS) - startTime (0MS) is greater than 0.5 then run the shooter
            // if (System.currentTimeMillis() - startTime > (1.5 * 1000)) {
                _shooterAssist.set(Constants.shooterAssistSpeed);
        //     }
        //     else {
        //         _shooterAssist.set(0.0);
        //     }

        // }
    }

    public void stopShooter() {
        _shooterPower.set(0.0);
        _shooterAssist.set(0.0);
    }
    

    // public void spitOutBall() {

    //     // The time is 0MS
    //     startTime = System.currentTimeMillis(); 

    //     // IF NOW (5MS) - startTime (0MS) is greater than 0.5 then run the shooter
    //     if (System.currentTimeMillis() - startTime < 0.5) {
    //         runShooter(0.2);
    //     }
    //     else {
    //         runShooter(0.0);
    //     }
        
    // }


    //Should be an autonomous background task, if ball color is not our team colors spit it out!
    // public void auto_readNspit(String teamColor) {

    //     if (match.color == kBlueTarget) {
    //         colorString = "Blue";
    //       } else if (match.color == kRedTarget) {
    //         colorString = "Red";
    //       } else {
    //         colorString = "Unknown";
    //       }


    //     if (teamColor == colorString) {
    //         return;
    //     }
    //     else if (teamColor != colorString) {
    //         spitOutBall();
    //     }
    //     else {
    //         // Should not enter here, just return for now
    //         return;
    //     }
    // }

    // Move the collector up (true) / down (false)

    public void moveCollector(boolean direction) {
        
        if (direction) {
            _collectorMovement.set(ControlMode.PercentOutput, Constants.collectorMovementSpeed);
        }
        else if (!direction) {
            _collectorMovement.set(ControlMode.PercentOutput, -Constants.collectorMovementSpeed);
        }
        else {
            _collectorMovement.set(ControlMode.PercentOutput, 0.0);
        }
    }

    public void stopCollectorMovement() {
        _collectorMovement.set(ControlMode.PercentOutput, 0.0);
    }

    // Collect forward (true) / reject collect (false)
    public void collect(boolean direction) {


        if (direction) {
            _collectorPower.set(Constants.collectorSpeed);
        }
        else if (!direction) {
            _collectorPower.set(-Constants.collectorSpeed);
        }
        else {
            _collectorPower.set(0.0);
        }
    }

    public void stopCollectorPower() {
        _collectorPower.set(0.0);
    }

}


