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
import frc.robot.subsystems.VisionClient;

public class VisionBasedDrive extends CommandBase {

  private Drivetrain drive;
  private VisionClient client;

  private Timer timer;

  private double
  power,
  fF,
  distanceKP,
  angleKP,
  setDistance,
  maxTime;

  /**
   * Creates a new VisionBasedDrive.
   */
  public VisionBasedDrive(
    Drivetrain drive,
    VisionClient client,
    double fF,
    double distanceKP,
    double angleKP,
    double setDistance,
    double maxTime
  ) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
    addRequirements(client);

    this.drive = drive;
    this.client = client;
    this.fF = fF;
    this.distanceKP = distanceKP;
    this.angleKP = angleKP;
    this.setDistance = setDistance;
    this.maxTime = maxTime;

    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
  }

  public double getOutput(
    double kP,
    double set,
    double output
  ) {
    double error = output - set;
    return error * kP;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    power = fF + getOutput(distanceKP, setDistance, client.getDistanceToTarget());

    double
    leftPower = power,
    rightPower = power;
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
