/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.subsystems.Climber.ClimberPosition;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    /**
     * Drive Motor Constants
     * Ports, modes, speeds, ramp up rates
     */

    //Drive motor ports
    public static final int
    driveLeft1 = 1,
    driveLeft2 = 2,
    driveRight1 = 3,
    driveRight2 = 4;

    //Drive motor Modes/types
    public static final IdleMode driveIdleMode = IdleMode.kBrake;
    public static final MotorType driveMotorType = MotorType.kBrushless;

    //Drive motor Speeds and rampUp rates
    public static final double
    driveInitialPower = 0.35,
    driveRampRate = 1;

    public static final boolean driveSwappedInitially = false;

    /**
     * Shooter Constants
     * Ports, modes, speeds, ramp rates
     */

    //Shooter motor ports
    public static final int
    shooterLower = 5,
    shooterUpper = 6;

    //Shooter modes/types
    public static final MotorType shooterMotorType = MotorType.kBrushless;
    public static final IdleMode shooterIdleMode = IdleMode.kBrake;

    //doubles
    public static double
    shooterUpperFF = 0.5,
    shooterLowerFF = 0.15,

    shooterLowerKP = 0.000004,
    shooterUpperKP = 0.000005,

    shooterLowerRPM = 3000,
    shooterUpperRPM = 3500,

    shooterRampRate = 0;
    

    /**
     * Hopper Constants
     * ports, modes, speeds, ramps
     */

    //Hopper Port
    public static final int hopperPort = 7;

    //Hopper modes
    public static final NeutralMode hopperIdleMode = NeutralMode.Brake;

    //Hopper speeds and ramps
    public static final double
    hopperFF = 0.5,
    hopperPower = 0.75,
    hopperRampRate = 0.25;

    //Hopper set positions
    public static final double
    conversionFactor = 507,
    indexIncrement = 12 * conversionFactor,
    k = 0.0005,
    endThreshold = 70;

    /**
     * Intake Constants
     */

    //Intake port
    public static final int 
    intakePort = 8,
    photoSensorPort = 1;

    //Intake mode
    public static NeutralMode 
    intakeIdleMode = NeutralMode.Brake;

    //Intake speeds and ramps and delays
    public static final double
    intakePower = -0.6,
    intakeRampRate = 0,
    intakeBallIndexDelay = 0.25;

    /**
     * Compressor Constants
     * ports
     */

    //Compressor ports
    public static final int
    compPort = 0,
    pressSwitchPort = 0;

    /**
     * Climber Constants
     */
    public static final int
    climberForwardPort = 0,
    climberReversePort = 1;

    public static final ClimberPosition
    climberStartPos = ClimberPosition.kUp;

    public static final double
    climberSpeed = 0.35;

    /**
     * SPINDLE CONSTANTS
     */

    public static final int 
    spindleMotorPort = 1;

    public static NeutralMode
    spindleNeutralMode = NeutralMode.Brake;

    public static final double 
    spindleRampRate = 0,
    spindleSpeed = .5;

    /**
     * VISION CONSTANTS
     */

     public static int 
     visLightPort = 2;

     /**
     * Controller Constants
     * ports, buttons
     */

    //Controller ports
    public static final int
    controllerPort1 = 0,
    controllerPort2 = 1,

    aButton = 1,
    bButton = 2,
    xButton = 3,
    yButton = 4,
    lbButton = 5,
    rbButton = 6,
    selectButton = 7,
    startButton = 8;

    public static final double
    triggerThreshold = 0.1;
    
}
