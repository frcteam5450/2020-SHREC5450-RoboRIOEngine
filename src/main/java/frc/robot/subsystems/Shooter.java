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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

  private CANSparkMax 
  _backMotor,
  _frontMotor;

  /**
   * Creates a new Shooter.
   */
  public Shooter(
    int frontMotorPort,
    int backMotorPort,
    MotorType motorType,
    IdleMode idleMode,
    double rampRate
  ) {
    //Motor definitions
    _backMotor = new CANSparkMax(backMotorPort, motorType);
    _frontMotor = new CANSparkMax(frontMotorPort, motorType);

    setIdleMode(idleMode);
    setRampRate(rampRate);
  }

  public void setSpeed(
    double backSpeed,
    double frontSpeed
  ) {
    _backMotor.set(backSpeed);
    _frontMotor.set(frontSpeed);
  }

  public void stop(

  ) {
    setSpeed(0, 0);
  }

  public void setIdleMode(
    IdleMode mode
  ) {
    _frontMotor.setIdleMode(mode);
    _backMotor.setIdleMode(mode);
  }

  public void setRampRate(
    double rate
  ) {
    _frontMotor.setOpenLoopRampRate(rate);
    _backMotor.setOpenLoopRampRate(rate);
  }

  public void showStats() {
    SmartDashboard.putNumber("Front Shooter Current", _frontMotor.getOutputCurrent());
    SmartDashboard.putNumber("Back Shooter Current", _backMotor.getOutputCurrent());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    showStats();
  }
}
