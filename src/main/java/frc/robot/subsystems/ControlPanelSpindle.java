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
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlPanelSpindle extends SubsystemBase {
  private TalonSRX motor;
  private double rampRate;
  private NeutralMode mode;

  private ShuffleboardTab tab;

  private NetworkTableEntry
  motorCurrent;
  /**
   * Creates a new ControlPanelSpindle.
   */
  public ControlPanelSpindle(
    int motorPort,
    NeutralMode mode,
    double rampRate
  ) {
    this.mode = mode;
    this.rampRate = rampRate;

    tab = Shuffleboard.getTab("Spindle");

    motorCurrent = tab.add("Spindle Current", 0).getEntry();

  }

  public void setSpeed(
    double motorPower
  ){
    motor.set(ControlMode.PercentOutput, motorPower);
  }

  public double getEnconder(){
    return motor.getSelectedSensorPosition();
  }

  public void showStats(){
    motorCurrent.setDouble(motor.getStatorCurrent());
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
