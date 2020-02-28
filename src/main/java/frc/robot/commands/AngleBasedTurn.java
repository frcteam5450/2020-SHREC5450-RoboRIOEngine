/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AngleBasedTurn extends CommandBase {

  private Drivetrain drive;

  private double
  kP,
  angle,
  power,
  endThreshold;

  /**
   * Creates a new AngleBasedTurn.
   */
  public AngleBasedTurn(
    Drivetrain drive,
    double kP,
    double angle,
    double endThreshold
  ) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);

    this.drive = drive;

    this.kP = kP;
    this.angle = angle;
    this.endThreshold = endThreshold;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.resetGyro();
  }

  public double getOutput(
    double setAngle,
    double kP,
    double outputAngle
  ) {
    double error = setAngle - outputAngle;
    return error * kP;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    power = getOutput(angle, kP, drive.getAngle());

    drive.setSpeed(-power, power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stop();
    drive.resetGyro();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double 
    lowerBound = angle - (endThreshold * angle),
    upperBound = angle + (endThreshold * angle);

    return lowerBound < drive.getAngle() && upperBound > drive.getAngle();
  }
}
