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

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private TalonSRX motor;
  private DigitalInput photoSensor;

  private ShuffleboardTab tab;

  private NetworkTableEntry
  currentEntry;

  /**
   * Creates a new Intake.
   */
  public Intake(
    int motorPort,
    NeutralMode neutralMode,
    double rampRate,
    int photoSensorPort
  ) {
    motor = new TalonSRX(motorPort);

    setNeutralMode(neutralMode);
    setRampRate(rampRate);

    photoSensor = new DigitalInput(photoSensorPort);

    tab = Shuffleboard.getTab("Intake");

    currentEntry = tab.add("Motor Current", 0).getEntry();
  }

  public void setSpeed(
    double speed
  ) {
    motor.set(ControlMode.PercentOutput, speed);
  }

  public void stopIntake() {
    setSpeed(0);
  }

  public void setNeutralMode(
    NeutralMode mode
  ) {
    motor.setNeutralMode(mode);
  }

  public void setRampRate(
    double rate
  ) {
    motor.configOpenloopRamp(rate);
  }

  public void showStats() {
    currentEntry.setDouble(getCurrent());
  }

  public double getCurrent() {
    return motor.getStatorCurrent();
  }

  public boolean getPhotoSensor() {
    return photoSensor.get();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    showStats();
  }
}
