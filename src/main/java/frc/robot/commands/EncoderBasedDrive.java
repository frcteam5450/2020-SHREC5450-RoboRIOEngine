/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class EncoderBasedDrive extends CommandBase {

  private Drivetrain drive;

  private double distance, 
  fF, 
  kP, 
  initPos1,
  initPos2;

  /**
   * Creates a new distancedDrive.
   */
  public EncoderBasedDrive(
    Drivetrain drive,
    double distance,
    double fF,
    double kP
  ) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);

    this.drive = drive;

    this.distance = distance;
    this.fF = fF;
    this.kP = kP;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    drive.resetGyro();
    initPos1 = drive.getLeftEncPos();
    initPos2 = drive.getRightEncPos();
  }

  public double getOutput(
    double setVal,
    double kP,
    double outputVal
  ) {
    double error = setVal + outputVal;
    return error * kP;
  }

  // Called every distance the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double 
    leftPower,
    rightPower;

    leftPower = fF; //+ getOutput(0, kP, drive.getAngle());
    rightPower = fF;

    drive.setSpeed(leftPower, rightPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stop();
    //drive.resetGyro();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double
    leftChange = Math.abs(initPos1 - drive.getLeftEncPos()),
    rightChange = Math.abs(initPos2 - drive.getRightEncPos());
    return leftChange > distance || rightChange > distance;
  }
}
