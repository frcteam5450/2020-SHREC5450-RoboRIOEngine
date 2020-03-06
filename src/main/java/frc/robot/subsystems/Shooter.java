/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  private CANSparkMax 
  upperMotor,
  lowerMotor;

  private ShuffleboardTab tab;
  private NetworkTableEntry
  upperMotorCurrent,
  lowerMotorCurrent,
  upperMotorVelocity,
  lowerMotorVelocity,
  upperMotorOutput,
  lowerMotorOutput;

  /**
   * Creates a new Shooter.
   */
  public Shooter(
    int lowerMotorPort,
    int upperMotorPort,
    MotorType motorType,
    IdleMode idleMode,
    double rampRate
  ) {
    //Motor definitions
    this.upperMotor = new CANSparkMax(upperMotorPort, motorType);
    this.lowerMotor = new CANSparkMax(lowerMotorPort, motorType);

    setIdleMode(idleMode);
    setRampRate(rampRate);
    tab = Shuffleboard.getTab("Shooter");

    upperMotorCurrent = tab.add("Upper Motor Current", 0).getEntry();
    lowerMotorCurrent = tab.add("Lower Motor Current", 0).getEntry();
    upperMotorVelocity = tab.add("Upper Motor RPM", 0).getEntry();
    lowerMotorVelocity = tab.add("Lower Motor RPM", 0).getEntry();
    upperMotorOutput = tab.add("Upper Motor Output", 0).getEntry();
    lowerMotorOutput = tab.add("Lower Motor Output", 0).getEntry();
  }

  public void setSpeed(
    double upperSpeed,
    double lowerSpeed
  ) {
    upperMotor.set(upperSpeed);
    lowerMotor.set(lowerSpeed);
  }

  public void stop(

  ) {
    setSpeed(0, 0);
  }

  public void setIdleMode(
    IdleMode mode
  ) {
    lowerMotor.setIdleMode(mode);
    upperMotor.setIdleMode(mode);
  }

  public void setRampRate(
    double rate
  ) {
    lowerMotor.setOpenLoopRampRate(rate);
    upperMotor.setOpenLoopRampRate(rate);
  }

  public void showStats() {
    upperMotorCurrent.setDouble(getUpperMotorCurrent());
    lowerMotorCurrent.setDouble(getLowerMotorCurrent());
    upperMotorVelocity.setDouble(getVelocityUpperMotor());    
    lowerMotorVelocity.setDouble(getVelocityLowerMotor());
    upperMotorOutput.setDouble(upperMotor.get());
    lowerMotorOutput.setDouble(lowerMotor.get());
  }

  public double getUpperMotorCurrent() {
    return upperMotor.getOutputCurrent();
  }

  public double getLowerMotorCurrent() {
    return lowerMotor.getOutputCurrent();
  }

  public double getVelocityUpperMotor() {
    return upperMotor.getEncoder().getVelocity();
  }

  public double getVelocityLowerMotor() {
    return lowerMotor.getEncoder().getVelocity();
  }

  @Override
  public void periodic() {
    //  method will be called once per scheduler run
    showStats();
  }
}
