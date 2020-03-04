/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.Delay;
import frc.robot.commands.EncoderBasedDrive;
import frc.robot.commands.RunHopper;
import frc.robot.commands.RunIntake;
import frc.robot.commands.ShootRPM;
import frc.robot.commands.ToggleClimb;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

import static frc.robot.Variables.*;
import static frc.robot.Constants.*;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class InlinePowerPortShoot extends SequentialCommandGroup {
  /**
   * Creates a new InlinePowerPortShoot.
   */
  public InlinePowerPortShoot(
    Drivetrain drive,
    Shooter shooter,
    Climber climber,
    Hopper hopper,
    Intake intake
  ) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new ToggleClimb(climber),
<<<<<<< HEAD
      new Delay(2),
      new EncoderBasedDrive(drive, 30, -.25, .005),
=======
      new Delay(3),
      new EncoderBasedDrive(drive, 40, .25, .005),
>>>>>>> 9aace8e5e0fd1117612a6399662d4bdcceba0dd6
      new ParallelCommandGroup(
        new ShootRPM(shooter, currentShooterUpperRPM, currentShooterLowerRPM, currentShooterUpperFF, currentShooterLowerFF, currentShooterUpperKP, currentShooterLowerKP, shootBallCurrent),
        new SequentialCommandGroup(
          new Delay(2),
          new ParallelCommandGroup(
            new RunIntake(intake, intakePower),
<<<<<<< HEAD
            new RunHopper(hopper, hopperPower),
            new SequentialCommandGroup(
              new Delay(5)
            )
=======
            new RunHopper(hopper, .5)
>>>>>>> 9aace8e5e0fd1117612a6399662d4bdcceba0dd6
          )
        )
      )
    );
  }
}
