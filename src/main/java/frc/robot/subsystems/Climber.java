/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.IntegerDeserializer;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

  public enum ClimberPosition {
    kUp,
    kDown
  }

  private DoubleSolenoid pistons;
  private CANSparkMax motor1;
  private CANSparkMax motor2;
  private double rampRate;
  private IdleMode mode;
  private MotorType type;

  private ShuffleboardTab tab;

  private NetworkTableEntry
  motor1CurrentEntry,
  motor2CurrentEntry,
  magEncPosEntry;

  /**
   * Creates a new Climber.
   */
  public Climber(
    int forwardSolenoid,
    int reverseSolenoid,
    ClimberPosition startPos,
    int motor1Port,
    int motor2Port,
    MotorType type,
    IdleMode mode,
    double rampRate
  ) {
    pistons = new DoubleSolenoid(forwardSolenoid, reverseSolenoid);
    if (startPos == ClimberPosition.kUp) {
      climberUp();
    }
    else {
      climberDown();
    }
    motor1 = new CANSparkMax(motor1Port, type);
    motor2 = new CANSparkMax(motor2Port, type);
    this.mode = mode;
    this.rampRate = rampRate;
    this.type = type;

    tab = Shuffleboard.getTab("Climber");

    motor1CurrentEntry = tab.add("Motor 1 Current", 0).getEntry();
    motor2CurrentEntry = tab.add("Motor 2 Current", 0).getEntry();
    magEncPosEntry = tab.add("Climber Position", 0).getEntry();
  }



  public void setSpeed(
    double motorPower
  ) {
    motor1.set(motorPower);
    motor2.set(motorPower);
  }

  public double getEncoder(){
    return motor1.getAlternateEncoder().getPosition();
  }

  public void showStats(){
    motor1CurrentEntry.setDouble(motor1.getOutputCurrent());
    motor2CurrentEntry.setDouble(motor2.getOutputCurrent());
    magEncPosEntry.setDouble(getEncoder());
  }

  public void climberUp() {
    pistons.set(Value.kForward);
  }

  public void climberDown() {
    pistons.set(Value.kReverse);
  }

  public ClimberPosition getClimberState() {
    if (pistons.get() == Value.kForward) {
      return ClimberPosition.kUp;
    }
    else {
      return ClimberPosition.kDown;
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
