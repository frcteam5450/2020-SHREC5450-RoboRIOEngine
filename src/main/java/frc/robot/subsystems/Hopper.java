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
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Hopper extends SubsystemBase {

  //Motor Declaration
  private TalonSRX _motor;

  /**
   * Creates a new Hopper.
   */
  public Hopper(
    int motorPort, //Which port the motor is on
    NeutralMode neutralMode, //idle mode of the motor
    double rampRate //Ramp up rate of the motor
  ) {
    //motor definition
    _motor = new TalonSRX(motorPort);

    //sets the idle mode of the motor, coast or brake
    setNeutralMode(neutralMode);

    //sets time in seconds from 0 to 1 in motor power
    setRampRate(rampRate);
  }

  public void setSpeed(
    double power
  ) {
    _motor.set(ControlMode.PercentOutput, power);
  }

  public void stopHopper(

  ) {
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
