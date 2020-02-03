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

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //Drive motor ports and modes
    public static final int
    driveLeft1 = 1,
    driveLeft2 = 2,
    driveRight1 = 3,
    driveRight2 = 4;
    public static final IdleMode driveIdleMode = IdleMode.kBrake;
    public static final MotorType driveMotorType = MotorType.kBrushless;

    //Shooter motor ports and modes
    public static final int
    shooterFront = 6,
    shooterBack = 5;
    public static final MotorType shooterMotorType = MotorType.kBrushless;
    public static final IdleMode shooterIdleMode = IdleMode.kBrake;

    //Talon motor ports and modes
    public static final int
    hopperPort = 1;
    public static NeutralMode hopperIdleMode = NeutralMode.Brake;

    //Controller ports
    public static final int
    controller1 = 0,
    controller2 = 1;

    //Motor power multipliers
    public static final double
    driveCurrentPower = .5,
    shooteBackPower = 1,
    shooterFrontPower = 1;
    //ramp up rates
    public static final double
    driveRampRate = 0.75,
    hopperRampRate = 0.25,
    shooterRampRate = 0;
}
