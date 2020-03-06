/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AngleBasedTurn;
import frc.robot.commands.Delay;
import frc.robot.commands.EncoderBasedDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.VisionClient;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoTrenchRunVisionBased extends SequentialCommandGroup {
  /**
   * Creates a new AutoTrenchRunVisionBased.
   */
  public AutoTrenchRunVisionBased(
    Drivetrain drive,
    Hopper hopper,
    VisionClient client,
    Shooter shooter
  ) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(
      new Delay(1),
      new EncoderBasedDrive(drive, 50, -.25, .005),
      new AngleBasedTurn(drive, .007, 88, .05),
      new EncoderBasedDrive(drive, 50, .25, .005)
    );
  }
}
