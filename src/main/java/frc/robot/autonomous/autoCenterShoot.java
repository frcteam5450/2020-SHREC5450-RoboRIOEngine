/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.*;
import frc.robot.subsystems.*;

import static frc.robot.Constants.*;
import static frc.robot.Variables.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class autoCenterShoot extends SequentialCommandGroup {
  /**
   * Creates a new autoCenterShoot.
   */
  public autoCenterShoot(
    Drivetrain drive,
    Intake intake,
    Climber climber,
    Hopper hopper,
    Shooter shooter
  ) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new Delay(2),
      new EncoderBasedDrive(drive, 30, -.25, .005),
      new AngleBasedTurn(drive, 45, .007, .05),
      new ParallelCommandGroup(
        new ShootRPM(shooter, 4000, 2000, currentShooterUpperFF, currentShooterLowerFF, currentShooterUpperKP, currentShooterLowerKP, shootBallCurrent),
        new SequentialCommandGroup(
          new Delay(2),
          new ParallelCommandGroup(
            new RunIntake(intake, intakePower),
            new RunHopper(hopper, hopperPower),
            new SequentialCommandGroup(
              new Delay(5)
            )
          )
        )
      )
    );
  }
}
