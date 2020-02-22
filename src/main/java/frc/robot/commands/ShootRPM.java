/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class ShootRPM extends CommandBase {

  private Shooter shooter;
  
  private ShuffleboardTab tab;

  private NetworkTableEntry 
  lowerPowerEntry,
  upperPowerEntry;

  private double
  upperKP,
  lowerKP,
  upperRPM,
  lowerRPM,
  upperPower,
  lowerPower,
  triggerThreshold;

  private XboxController controller;

  /**
   * Creates a new ShootRPM.
   */
  public ShootRPM(
    Shooter shooter,
    double upperRPM,
    double lowerRPM,
    double initUpperPower,
    double initLowerPower,
    double upperKP,
    double lowerKP,
    double triggerThreshold,
    XboxController controller
  ) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter);

    this.shooter = shooter;

    this.upperRPM = upperRPM;
    this.lowerRPM = lowerRPM;

    this.upperPower = initUpperPower;
    this.lowerPower = initLowerPower;

    this.upperKP = upperKP;
    this.lowerKP = lowerKP;

    this.triggerThreshold = triggerThreshold;

    tab = Shuffleboard.getTab("Shooter");

    this.lowerPowerEntry = tab.add("Lower Power Output", 0).getEntry();
    this.upperPowerEntry = tab.add("Upper Power Output", 0).getEntry();

    this.controller = controller;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  public double getOutput(
    double kP,
    double setRPM,
    double outputRPM
  ) {
    double error = setRPM - outputRPM;
    return error * kP;
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    upperPower = upperPower + getOutput(upperKP, upperRPM, shooter.getVelocityUpperMotor());
    lowerPower = lowerPower + getOutput(lowerKP, lowerRPM, shooter.getVelocityLowerMotor());

    shooter.setSpeed(upperPower, lowerPower);

    lowerPowerEntry.setDouble(lowerPower);
    upperPowerEntry.setDouble(upperPower);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
