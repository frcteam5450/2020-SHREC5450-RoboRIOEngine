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

  private Hopper _hopper;
  private double
  _maxSpeed,
  _posIncrement,
  _startPos,
  _setPos,
  _k,
  _endThreshold;

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

    _hopper = hopper;
    _maxSpeed = maxSpeed;
    _posIncrement = posIncrement;
    _k = k;
    _endThreshold = endThreshold;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _startPos = _hopper.getEncPos();
    _setPos = _startPos - _posIncrement;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double error = _setPos - _hopper.getEncPos();
    double power = error *_k;

    if (Math.abs(power) > _maxSpeed) {
      if (power < 0) power = -_maxSpeed;
      else power = _maxSpeed;
    }

    _hopper.setSpeed(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _hopper.stopHopper();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return /*(_hopper.getEncPos() > _setPos - _endThreshold) &&*/ 
    (_hopper.getEncPos() < _setPos + _endThreshold);
  }
}
