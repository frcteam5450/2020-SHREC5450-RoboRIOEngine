/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.customtriggers;

import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.VisionClient;

/**
 * Add your docs here.
 */
public class VisionViable extends Trigger{

    private VisionClient client;

    public VisionViable (
        VisionClient client
    ) {
        this.client = client;
    }

    @Override
    public boolean get() {
        return client.isViable() && client.getDistanceToTarget() != -1 && client.getAngleToTarget() != -100;
    }
}
