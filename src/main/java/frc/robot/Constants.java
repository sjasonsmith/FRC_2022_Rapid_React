// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // Swerve Drive
    //Copy and pasted from https://github.com/SwerveDriveSpecialties/swerve-template/blob/dacd55949122576615ad8df77131aae2feb29d2c/src/main/java/frc/robot/Constants.java

        /**
     * The left-to-right distance between the drivetrain wheels
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.6223; // FIXME Measure and set trackwidth
    /**
     * The front-to-back distance between the drivetrain wheels.
     *
     * Should be measured from center to center.
     */
    public static final double DRIVETRAIN_WHEELBASE_METERS = 0.6096; // FIXME Measure and set wheelbase

    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 1; // FIXME Set front left module drive motor ID
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 2; // FIXME Set front left module steer motor ID
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 12; // FIXME Set front left steer encoder ID

    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(174.8); // FIXME Measure and set front left steer offset

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 7; // FIXME Set front right drive motor ID
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 8; // FIXME Set front right steer motor ID
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 13; // FIXME Set front right steer encoder ID

    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(222.0); // FIXME Measure and set front right steer offset

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 3; // FIXME Set back left drive motor ID
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 4; // FIXME Set back left steer motor ID
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 11; // FIXME Set back left steer encoder ID

    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(151.1); // FIXME Measure and set back left steer offset

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 5; // FIXME Set back right drive motor ID
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 6; // FIXME Set back right steer motor ID
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 10; // FIXME Set back right steer encoder ID
    
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(206.7); // FIXME Measure and set back right steer offset
 
    // CAN ID's

    public static final int collectorPowerCanID = 26;
    public static final int shooterPowerCanID = 27;
    public static final int shooterAssistCanID = 28;
    public static final int collectorMovementCanID = 25;
    public static final int leftTiltMotorCanID = 56;
    public static final int rightTiltMotorCanID = 55;
    public static final int winchCanID = 55;

    // Speeds
    public static final double shooterAssistSpeed = -0.75;
    public static final double collectorMovementSpeed = 0.25;
    public static final double collectorSpeed = 0.95;
    public static final double liftArmSpeed = 0.40;
}
