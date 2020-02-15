/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Compressor extends SubsystemBase {
  private Relay _relay;
  private DigitalInput _input;

  private ShuffleboardTab tab;

  private NetworkTableEntry 
  pressureLowEntry,
  relayOnEntry;

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

    tab = Shuffleboard.getTab("Compressor");

    pressureLowEntry = tab.add("Pressure Low?", false).getEntry();
    relayOnEntry = tab.add("Relay On?", false).getEntry();
  }
  
  public void start(){
    _relay.set(Value.kForward);
  }

  public void stop(){
    _relay.set(Value.kOff);
  }
  
  public boolean pressureLow(){
    return !_input.get();
  }

  public void showStats(){
    pressureLowEntry.setBoolean(pressureLow());
    if (_relay.get() == Value.kForward) relayOnEntry.setBoolean(true);
    else relayOnEntry.setBoolean(false);
  }

  @Override
  public void periodic() {
    showStats();
    // This method will be called once per scheduler run
  }
}
