/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionClient extends SubsystemBase {

  private Solenoid visionLight;
  
  /**
   * Creates a new VisionClient.
   */
  public VisionClient(
  int visLightPort
  ) {
    visionLight = new Solenoid(visLightPort);

    on();
  }

  public void on(){
    visionLight.set(true);
  }

  public void Off(){
    visionLight.set(false);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
