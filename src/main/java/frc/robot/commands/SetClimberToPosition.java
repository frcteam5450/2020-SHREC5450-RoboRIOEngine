/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class SetClimberToPosition extends CommandBase {
  
  private Climber climber;

  private double
  fF,
  setPosition,
  kP,
  endThreshold;




  /**
   * Creates a new SetClimberToPosition.
   */
  public SetClimberToPosition(
    Climber climber,
    double fF,
    double setPosition,
    double kP,
    double endThreshold 
  ) {
    this.climber = climber;
    this.fF = fF;
    this.setPosition = setPosition;
    this.kP = kP;
    this.endThreshold = endThreshold;
    addRequirements(climber);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  public double getOutput(double kP, double setPosition, double eOutput){
    double error = setPosition - eOutput;
    return error * kP;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double power = fF + getOutput(kP, setPosition, climber.getEncoder());
    climber.setSpeed(power);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    climber.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double upperLimit = setPosition + (setPosition * endThreshold);
    double lowerLimit = setPosition - (setPosition * endThreshold);
    if (upperLimit > climber.getEncoder() && lowerLimit < climber.getEncoder()){
      return true;
    }
    else {
      return false;
    }
  }
}
