/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Compressor extends SubsystemBase {
  private Relay _relay;
  private DigitalInput _input;
  /**
   * Creates a new Compressor.
   */
  public Compressor(
    int relay,
    int input
  ) {
     _relay = new Relay(relay);
    _input = new DigitalInput(input);

    stop();
  }
  
  public void start(){
    _relay.set(Value.kForward);
  }

  public void stop(){
    _relay.set(Value.kOff);
  }
  
  public boolean pressureLow(){
    return _input.get();
  }

  public void showStats(){
    SmartDashboard.putBoolean("Pressure Low", pressureLow());
    //SmartDashboard.putString("On?", _relay.get());
  }

  @Override
  public void periodic() {
    showStats();
    // This method will be called once per scheduler run
  }
}
