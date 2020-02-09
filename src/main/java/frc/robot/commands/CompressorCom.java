/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.Compressor;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class CompressorCom extends CommandBase {
  private Compressor _compressor;
  /**
   * 
   * Creates a new CompressorCom.
   */
  public CompressorCom(Compressor compressor) {
    _compressor = compressor;
    addRequirements(compressor);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _compressor.stop();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (_compressor.pressureLow()){
    _compressor.start();
    }
    else{
      _compressor.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _compressor.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
