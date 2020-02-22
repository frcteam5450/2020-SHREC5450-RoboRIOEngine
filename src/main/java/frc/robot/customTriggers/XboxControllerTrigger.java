/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.customTriggers;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * Add your docs here.
 */
public class XboxControllerTrigger extends Trigger {

    private double triggerThreshold;
    private XboxController controller;
    private Hand hand;

    public XboxControllerTrigger(
        XboxController controller,
        double triggerThreshold,
        Hand hand
    ) {
        this.triggerThreshold = triggerThreshold;
        this.controller = controller;
        this.hand = hand;
    }

    @Override
    public boolean get() {
        return controller.getTriggerAxis(hand) > triggerThreshold;
    }
}
