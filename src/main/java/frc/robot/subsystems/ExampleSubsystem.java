/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * This is an example subsystem. It'll show you the basic structure of a subsystem class,
 * with comments throughout. A class is basically an object type definition. For example, I can create
 * a drivetrain class, which would act as a template for code that could run a drivetrain.
 * Notice that nothing is defined explicitly in this class. In other words, nothing is defined as a known value.
 * Instead, everything is defined implicitly; not off a known value
 * This example subsystem will include a motor and a sensor.
 * -Evan
 */

//The package defines the folder this class is in
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//Here's all our imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

//This is the class declaration and definition
public class ExampleSubsystem extends /** This class builds off another class, SubsystemBase */ SubsystemBase {

  //These are fields - objects or variables we'll use throughout the class. We keep them private so they can't be used by other classes
  private TalonSRX _someMotor;
  private DigitalInput _someInput;

  /**
   * Creates a new ExampleSubsystem, this is a Constructor.
   */
  public ExampleSubsystem(
    int motorPort, //Port the motor is on
    NeutralMode motorNeutralMode, //Neutral Mode of the motor, brake or coast
    double rampRate, //ramp up rate of motor, time to go from 0 to 100%
    int inputPort //Port digitalInput is on
  ) {
    _someMotor = new TalonSRX(motorPort);
    _someInput = new DigitalInput(inputPort);

    setNeutralMode(motorNeutralMode);
    setRampRate(rampRate);
  }

  /**
   * Sets the speed of the motor
   * @param speed
   */
  public void setSpeed(
    double speed
  ) {
    _someMotor.set(ControlMode.PercentOutput/**We'll use percent output as our set mode */, speed);
  }

  /**
   * Sets the neutral mode to either coast or brake
   * @param mode
   */
  public void setNeutralMode(
    NeutralMode mode
    ) {
      _someMotor.setNeutralMode(mode);
    }
  
  /**
   * Sets ramp rate of motor, time to go from 0 to 100% power
   * @param rate
   */
  public void setRampRate(
    double rate
  ) {
    _someMotor.configOpenloopRamp(rate);
  }

  /**
   * Shows basic statistics for the subsystem, like output current
   */
  public void showStats() {
    SmartDashboard.putNumber("Current Pull from ExampleMotor", _someMotor.getStatorCurrent());
    SmartDashboard.putBoolean("Example DI Val", getSomeInput());
  }

  /**
   * Gets a value from the digitalinput
   * @return value of input
   */
  public boolean getSomeInput() {
    return _someInput.get();
  }

  /**
   * This is a method that runs periodically whenver the robot is on. Use it for diagnostic data, like encoder position(s) or
   * output current of motors
   */
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    showStats();
  }
}
