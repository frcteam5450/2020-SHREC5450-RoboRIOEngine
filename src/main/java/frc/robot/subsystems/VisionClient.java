/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionClient extends SubsystemBase {

  private Solenoid visionLight;

  private ShuffleboardTab tab;

  private NetworkTableEntry
  distanceEntry,
  angleEntry;
  
  /**
   * Creates a new VisionClient.
   */
  public VisionClient(
  int visLightPort
  ) {
    visionLight = new Solenoid(visLightPort);

    //on();

    tab = Shuffleboard.getTab("Vision");
    distanceEntry = tab.add("Distance To Target", 0).getEntry();
    angleEntry = tab.add("Angle to Target", 0).getEntry();
  }

  public void on(){
    visionLight.set(true);
  }

  public void Off(){
    visionLight.set(false);
  }

  public double getDistanceToTarget() {
    return distanceEntry.getDouble(-1);
  }

  public double getAngleToTarget() {
    return angleEntry.getDouble(-1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
