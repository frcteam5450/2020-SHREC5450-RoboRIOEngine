/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.VisionClient;
import edu.wpi.first.wpilibj.Timer;

public class VisionBasedTurn extends CommandBase {

  private Drivetrain drivetrain;
  private VisionClient client;

  private double
  angle,
  power,
  kP,
  endThreshold,
  endTime;

  private Timer timer;

  /**
   * Creates a new VisionBasedTurn.
   */
  public VisionBasedTurn(
    Drivetrain drivetrain,
    VisionClient client,
    double angle,
    double fF,
    double kP,
    double endThreshold,
    double endTime
  ) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drivetrain);
    addRequirements(client);

    this.drivetrain = drivetrain;
    this.client = client;

    this.angle = angle;
    this.power = fF;
    this.kP = kP;
    this.endThreshold = endThreshold;
    this.endTime = endTime;

    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  public double getOutput(
    double kP,
    double setAngle,
    double outputAngle
  ) {
    double error = setAngle - outputAngle;
    return error * kP;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double leftPower = power - getOutput(kP, angle, client.getAngleToTarget());
    double rightPower = power + getOutput(kP, angle, client.getAngleToTarget());

    drivetrain.setSpeed(leftPower, rightPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrain.stop();
    timer.stop();
    timer.reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double lowerThreshold = angle - (angle * endThreshold);
    double upperThreshold = angle + (angle * endThreshold);

    return (lowerThreshold < client.getAngleToTarget() && upperThreshold > client.getAngleToTarget()) || timer.get() > endTime;
  }
}
