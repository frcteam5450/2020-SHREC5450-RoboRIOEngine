/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.Intake;
import static frc.robot.Variables.*;

public class IntakeBall extends CommandBase {

  private Intake intake;
  private double 
  speed,
  delay,
  snagCurrent,
  rumbleVal;

  private XboxController controller;
  
  private boolean wasNoBall = true;
  private Timer timer;

  /**
   * Creates a new IntakeBall.
   */
  public IntakeBall(
    Intake intake, 
    double speed, 
    double delay,
    double snagCurrent,
    double rumbleVal,
    XboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(intake);

    this.intake = intake;
    this.speed = speed;
    this.delay = delay;
    this.snagCurrent = snagCurrent;
    this.rumbleVal = rumbleVal;
    this.controller = controller;

    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.setSpeed(speed);

    if (intake.getPhotoSensor() && wasNoBall) {
      timer.start();
      wasNoBall = false;
    }

    if (intake.getCurrent() > snagCurrent) {
      controller.setRumble(RumbleType.kLeftRumble, rumbleVal);
      controller.setRumble(RumbleType.kRightRumble, rumbleVal);
    }
    else {
      controller.setRumble(RumbleType.kLeftRumble, 0);
      controller.setRumble(RumbleType.kRightRumble, 0);
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.stopIntake();
    wasNoBall = true;

    controller.setRumble(RumbleType.kLeftRumble, 0);
    controller.setRumble(RumbleType.kRightRumble, 0);

    timer.stop();
    timer.reset();

    numBalls++;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.get() > delay;
  }
}
