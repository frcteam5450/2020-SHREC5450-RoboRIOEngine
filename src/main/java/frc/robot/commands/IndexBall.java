/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;

public class IndexBall extends CommandBase {

  private Hopper hopper;
  private double
  maxSpeed,
  posIncrement,
  startPos,
  setPos,
  k,
  endThreshold;

  /**
   * Creates a new IndexBall.
   */
  public IndexBall(
    Hopper hopper,
    double maxSpeed,
    double posIncrement,
    double k,
    double endThreshold
  ) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(hopper);

    this.hopper = hopper;
    this.maxSpeed = maxSpeed;
    this.posIncrement = posIncrement;
    this.k = k;
    this.endThreshold = endThreshold;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startPos = hopper.getEncPos();
    setPos = startPos - posIncrement;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double error = setPos - hopper.getEncPos();
    double power = error *k;

    if (Math.abs(power) > maxSpeed) {
      if (power < 0) power = -maxSpeed;
      else power = maxSpeed;
    }

    hopper.setSpeed(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    hopper.stopHopper();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return /*(hopper.getEncPos() > setPos - endThreshold) &&*/ 
    (hopper.getEncPos() < setPos + endThreshold);
  }
}
