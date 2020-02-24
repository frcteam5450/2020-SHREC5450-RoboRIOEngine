/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.*;

/**
 * Variables for the robot, values that change depending on the situation. These will be their initial values.
 */
public class Variables {
    /**
     * Drive variables
     */
    public static double driveCurrentPower = driveInitialPower;
    public static boolean driveSwapped = driveSwappedInitially;

    /**
     * Hopper Variables
     */
    public static double numBalls = 0;

    /**
     * Shooter Variables
     */
    public static double
    currentShooterUpperFF = shooterUpperFF1,
    currentShooterLowerFF = shooterLowerFF1,

    currentShooterLowerKP = shooterLowerKP1,
    currentShooterUpperKP = shooterUpperKP1,

    currentShooterLowerRPM = shooterLowerRPM1,
    currentShooterUpperRPM = shooterUpperRPM1;
}
