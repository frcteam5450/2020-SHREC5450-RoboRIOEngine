/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private TalonSRX _motor;

  /**
   * Creates a new Intake.
   */
  public Intake(
    int motorPort,
    NeutralMode neutralMode,
    double rampRate
  ) {
    _motor = new TalonSRX(motorPort);

    setNeutralMode(neutralMode);
    setRampRate(rampRate);
  }

  public void setSpeed(
    double speed
  ) {
    _motor.set(ControlMode.PercentOutput, speed);
  }

  public void stopIntake() {
    setSpeed(0);
  }

  public void setNeutralMode(
    NeutralMode mode
  ) {
    _motor.setNeutralMode(mode);
  }

  public void setRampRate(
    double rate
  ) {
    _motor.configOpenloopRamp(rate);
  }

  public void showStats() {
    SmartDashboard.putNumber("Intake Motor Current", _motor.getStatorCurrent());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    showStats();
  }
}
