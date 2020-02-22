/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.customTriggers;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * Add your docs here.
 */
public class XboxControllerDPad extends Trigger {

    private XboxController controller;

    public enum DPadDirection {
        Up,
        Right,
        Down,
        Left
    };

    private DPadDirection direction;

    public XboxControllerDPad(
        XboxController controller,
        DPadDirection direction
    ) {
        this.controller = controller;
        this.direction = direction;
    }

    @Override
    public boolean get() {
        double dPadDirection;

        switch (direction) {
            case Up:
                dPadDirection = 0;
                break;

            case Right:
                dPadDirection = 90;
                break;
            
            case Down:
                dPadDirection = 180;
                break;

            case Left:
                dPadDirection = 270;
                break;
            
            default:
                dPadDirection = -1;
        }

        return dPadDirection == controller.getPOV();
    }
}
