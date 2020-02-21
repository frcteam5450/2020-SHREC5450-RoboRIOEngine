/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;

public class TriggerListener extends CommandBase {

  private SequentialCommandGroup intakeAndIndex;
  private ParallelCommandGroup shootAndRunHopper;
  private KillAllCommands kill;

  private XboxController controller;

  private double triggerThreshold;

  private boolean
  ltWasTriggered = false,
  rtWasTriggered = false;

  /**
   * Creates a new TriggerListener.
   */
  public TriggerListener(
    Shooter shooter,
    Hopper hopper,
    IntakeBall intakeBall,
    IndexBall indexBall,
    ShootRPM shootBalls,
    RunHopper runHopper,
    Delay delay,
    XboxController controller,
    double triggerThreshold
  ) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);
    this.controller = controller;
    this.triggerThreshold = triggerThreshold;

    intakeAndIndex = new SequentialCommandGroup(intakeBall, indexBall);
    shootAndRunHopper = new ParallelCommandGroup(new SequentialCommandGroup(delay, runHopper), shootBalls);
    kill = new KillAllCommands(shooter, hopper);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (controller.getTriggerAxis(Hand.kLeft) > triggerThreshold) {
      ltWasTriggered = true;
    }
    else if (ltWasTriggered) {
      ltWasTriggered = false;
      intakeAndIndex.schedule();
    }

    if (controller.getTriggerAxis(Hand.kRight) > triggerThreshold) {
      rtWasTriggered = true;
      shootAndRunHopper.schedule();
    }
    else if (rtWasTriggered) {
      rtWasTriggered = false;
      kill.schedule();
    }
  
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
