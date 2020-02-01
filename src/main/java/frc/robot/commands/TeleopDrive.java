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
import static frc.robot.Constants.*;

public class TeleopDrive extends CommandBase {

  private Drivetrain _drive;
  private XboxController _controller;

  /**
   * Creates a new TeleopDrive.
   */
  public TeleopDrive(Drivetrain drive, XboxController controller) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(drive);
    _drive = drive;
    _controller = controller;
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
      double leftPower = _controller.getY(Hand.kLeft) * currentPower;
      double rightPower = _controller.getY(Hand.kRight) * currentPower;

      _drive.setSpeed(leftPower, rightPower);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _drive.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
