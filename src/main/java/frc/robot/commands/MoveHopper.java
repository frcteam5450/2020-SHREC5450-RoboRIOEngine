/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Hopper;

public class MoveHopper extends CommandBase {

  private Hopper _hopper;
  private double _speed;

  /**
   * Creates a new MoveHopper.
   */
  public MoveHopper(Hopper hopper, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(hopper);
    _hopper = hopper;
    _speed = speed;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    end(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _hopper.setSpeed(_speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _hopper.stopHopper();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
