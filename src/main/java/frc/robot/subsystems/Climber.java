/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

  enum ClimberPosition {
    kUp,
    kDown
  }

  private DoubleSolenoid _pistons;

  /**
   * Creates a new Climber.
   */
  public Climber(
    int forwardSolenoid,
    int reverseSolenoid,
    ClimberPosition startPos
  ) {
    _pistons = new DoubleSolenoid(forwardSolenoid, reverseSolenoid);
  }

  public void climberUp() {
    _pistons.set(Value.kForward);
  }

  public void climberDown() {
    _pistons.set(Value.kReverse);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
