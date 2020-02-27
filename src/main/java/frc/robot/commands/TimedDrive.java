/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TimedDrive extends CommandBase {

  private Drivetrain drive;

  private Timer timer;

  private double
  time,
  fF,
  kP,
  power;

  /**
   * Creates a new TimedDrive.
   */
  public TimedDrive(
    Drivetrain drive,
    double time,
    double fF,
    double kP
  ) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);

    this.drive = drive;

    this.time = time;
    this.fF = fF;
    this.kP = kP;

    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.resetGyro();
    timer.start();
  }

  public double getOutput(
    double setVal,
    double kP,
    double outputVal
  ) {
    double error = setVal - outputVal;
    return error * kP;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double 
    leftPower,
    rightPower;

    leftPower = fF + getOutput(0, kP, drive.getAngle());
    rightPower = fF;

    drive.setSpeed(leftPower, rightPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stop();
    timer.stop();
    timer.reset();
    drive.resetGyro();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return time < timer.get();
  }
}
