/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * The Drivetrain subsystem is an interface for using the drive motors and sensors on the robot.
 * This subsystem contains the basics for interaction with the drive.
 */
public class Drivetrain extends SubsystemBase {

  //Motor declarations
  private CANSparkMax
  _leftMotor1,
  _leftMotor2,
  _rightMotor1,
  _rightMotor2;

  //Encodor declaration
  private CANEncoder _enc;

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain( //Parameters for creating a new instance of Drivetrain:
    int left1, //left Motor 1's port
    int left2, //left Motor 2's port
    int right1, //right Motor 1's port
    int right2, //right Motor 2's port
    MotorType type, //type of motor, brushed or brushless
    IdleMode idleMode, //Mode when the motor is idle, brake or coast
    int encoderPort //Motor controller that the encoder is connected to
  ) {
    //Motor definitions
    _leftMotor1 = new CANSparkMax(left1, type);
    _leftMotor2 = new CANSparkMax(left2, type);
    _rightMotor1 = new CANSparkMax(right1, type);
    _rightMotor2 = new CANSparkMax(right2, type);

    _enc = new CANSparkMax(encoderPort, type).getEncoder();
  }

  /**
   * Sets the speed of an instance of Drivetrain
   * @param leftPower sets left side speed
   * @param rightPower sets right side speed
   * one of these parameters needs to be negative
   */
  public void setSpeed(
    double leftPower,
    double rightPower 
  ) {
    //Sets the motors to a speed, one of the powers will need to be negative at some point
    _leftMotor1.set(leftPower);
    _leftMotor2.set(leftPower);
    _rightMotor1.set(rightPower);
    _rightMotor2.set(rightPower);
  }

  /**
   * Sets the idlemode of a motor (idle when speed set is 0)
   * @param mode coast or brake
   */
  public void setIdleMode(
    IdleMode mode
  ) {
    _leftMotor1.setIdleMode(mode);
    _leftMotor2.setIdleMode(mode);
    _rightMotor1.setIdleMode(mode);
    _rightMotor2.setIdleMode(mode);
  }

  /**
   * Sets the speed of the drivetrain to 0.
   * IMPORTANT: If the idlemode is set to coast, the robot may not stop immediately due to momentum.
   */
  public void stop() {
    setSpeed(0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
