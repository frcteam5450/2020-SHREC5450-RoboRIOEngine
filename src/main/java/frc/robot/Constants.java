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
    driveFinePower = 0.3,
    driveFastPower = 0.6,
    driveInitialPower = driveFinePower,
    driveRampRate = 1,
    driveSetAngle = -10,
    driveEncConversionFactor = 1.76,
    driveAngleFF = 0,
    driveAngleThreshold = 0.1,
    driveAngleKP = 0.01;

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
    shooterUpperFF1 = 0.61, //0.59 good at edge
    shooterLowerFF1 = 0.64, //.56 good at edge

    shooterLowerKP1 = 0.000007,
    shooterUpperKP1 = 0.000007,

    shooterLowerRPM1 = 3350, //Good trenchrun
    shooterUpperRPM1 = 3050,

    /*shooterUpperFF2 = 0.25,
    shooterLowerFF2 = 0.25,

    shooterLowerKP2 = 0.0000035,
    shooterUpperKP2 = 0.0000035,

    shooterLowerRPM2 = 2500, //good outreach values
    shooterUpperRPM2 = 1000,

    shooterUpperFF3 = 0.25,
    shooterLowerFF3 = 0.25,

    shooterLowerKP3 = 0.0000035,
    shooterUpperKP3 = 0.0000035,

    shooterLowerRPM3 = 2500, //good outreach values
    shooterUpperRPM3 = 1000,

    shooterUpperFF4 = 0.25,
    shooterLowerFF4 = 0.25,

    shooterLowerKP4 = 0.0000035,
    shooterUpperKP4 = 0.0000035,

    shooterLowerRPM4 = 2500, //good outreach values
    shooterUpperRPM4 = 1000,

    shooterUpperFF5 = 0.25,
    shooterLowerFF5 = 0.25,

    shooterLowerKP5 = 0.0000035,
    shooterUpperKP5 = 0.0000035,

    shooterLowerRPM5 = 2500, //good outreach values
    shooterUpperRPM5 = 1000,

    shooterUpperFF6 = 0.25,
    shooterLowerFF6 = 0.25,

    shooterLowerKP6 = 0.0000035,
    shooterUpperKP6 = 0.0000035,

    shooterLowerRPM6 = 2500, //good outreach values
    shooterUpperRPM6 = 1000,*/

    shooterRampRate = 0,

    shooterDelay = 1,

    shootBallCurrent = 20;
    

    /**
     * Hopper Constants
     * ports, modes, speeds, ramps
     */

    //Hopper Port
    public static final int hopperPort = 15;

    //Hopper modes
    public static final NeutralMode hopperIdleMode = NeutralMode.Brake;

    //Hopper speeds and ramps
    public static final double
    hopperFF = 0.25,
    hopperPower = 0.5,
    hopperRampRate = 0.5,
    bindCurrent = 20;

    //Hopper set positions
    public static final double
    conversionFactor = 507,
    indexIncrement = 13 * conversionFactor,
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
    intakeBallIndexDelay = 0.35,
    snagCurrent = 20;

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

    public static final int
    reverseBrake = 7,
    forwardBrake = 6;

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
    triggerThreshold = 0.1,
    rumbleVal = 0.5;
    
}
