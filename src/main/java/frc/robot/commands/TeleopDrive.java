/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import static frc.robot.Variables.*;

public class TeleopDrive extends CommandBase {

  private Drivetrain drive;
  private XboxController 
  controller1,
  controller2;

  /**
   * Creates a new TeleopDrive.
   */
  public TeleopDrive(
    Drivetrain drive, 
    XboxController controller1,
    XboxController controller2
    ) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
    this.drive = drive;
    this.controller1 = controller1;
    this.controller2 = controller2;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    end(false);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (DriverStation.getInstance().isOperatorControl()) {
      double leftPower,
      rightPower,
      leftPower1,
      leftPower2,
      rightPower1,
      rightPower2;

      if (!driveSwapped) {
        leftPower1 = controller1.getY(Hand.kLeft);
        leftPower2 = controller2.getY(Hand.kLeft);
        rightPower1 = controller1.getY(Hand.kRight);
        rightPower2 = controller2.getY(Hand.kRight);
      }

      else {
        leftPower1 = -controller1.getY(Hand.kRight);
        leftPower2 = -controller2.getY(Hand.kRight);
        rightPower1 = -controller1.getY(Hand.kLeft);
        rightPower2 = -controller2.getY(Hand.kLeft);
      }

      if (Math.abs(leftPower1) > Math.abs(leftPower2)) leftPower = leftPower1;
      else leftPower = leftPower2;

      if (Math.abs(rightPower1) > Math.abs(rightPower2)) rightPower = rightPower1;
      else rightPower = rightPower2;

      drive.setSpeed(leftPower * driveCurrentPower, rightPower * driveCurrentPower);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
