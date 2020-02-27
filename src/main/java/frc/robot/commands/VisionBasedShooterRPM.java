/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.VisionClient;

import static frc.robot.Constants.*;
import static frc.robot.Variables.*;

public class VisionBasedShooterRPM extends CommandBase {

  public enum DistanceBracket {
    Bracket0_4FT,
    Bracket4_8FT,
    Bracket8_12FT,
    Bracket12_16FT,
    Bracket16_20FT,
    Bracket20_24FT
  };

  private Shooter shooter;
  private VisionClient client;
  private DistanceBracket defaultBracket;
  private DistanceBracket currentBracket;

  /**
   * Creates a new VisionBasedShooterRPM.
   */
  public VisionBasedShooterRPM(
    Shooter shooter,
    VisionClient client,
    DistanceBracket defaultBracket
  ) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(client);
    addRequirements(shooter);

    this.shooter = shooter;
    this.client = client;

    this.defaultBracket = defaultBracket;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double distance = client.getDistanceToTarget();

    if (distance > 48 && distance < 96) {
      currentBracket = DistanceBracket.Bracket4_8FT;
    }
    else if (distance > 96 && distance < 144) {
      currentBracket = DistanceBracket.Bracket8_12FT;
    }
    else if (distance > 144 && distance < 192) {
      currentBracket = DistanceBracket.Bracket12_16FT;
    }
    else if (distance > 192 && distance < 240) {
      currentBracket = DistanceBracket.Bracket16_20FT;
    }
    else if (distance > 240 && distance < 288) {
      currentBracket = DistanceBracket.Bracket20_24FT;
    }
    else {
      currentBracket = null;
    }

    /*switch (currentBracket) {
      case Bracket0_4FT:
        currentShooterLowerFF =shooterLowerFF1;
        currentShooterUpperFF = shooterUpperFF1;

        currentShooterLowerKP = shooterLowerKP1;
        currentShooterUpperKP = shooterUpperKP1;

        currentShooterLowerRPM = shooterLowerRPM1;
        currentShooterUpperRPM = shooterUpperRPM1;
        break;
      
      case Bracket4_8FT:
        currentShooterLowerFF =shooterLowerFF2;
        currentShooterUpperFF = shooterUpperFF2;

        currentShooterLowerKP = shooterLowerKP2;
        currentShooterUpperKP = shooterUpperKP2;

        currentShooterLowerRPM = shooterLowerRPM2;
        currentShooterUpperRPM = shooterUpperRPM2;
        break;

      case Bracket12_16FT:
        currentShooterLowerFF =shooterLowerFF4;
        currentShooterUpperFF = shooterUpperFF4;

        currentShooterLowerKP = shooterLowerKP4;
        currentShooterUpperKP = shooterUpperKP4;

        currentShooterLowerRPM = shooterLowerRPM4;
        currentShooterUpperRPM = shooterUpperRPM4;
        break;

      case Bracket16_20FT:
        currentShooterLowerFF =shooterLowerFF5;
        currentShooterUpperFF = shooterUpperFF5;

        currentShooterLowerKP = shooterLowerKP5;
        currentShooterUpperKP = shooterUpperKP5;

        currentShooterLowerRPM = shooterLowerRPM5;
        currentShooterUpperRPM = shooterUpperRPM5;
        break;

      case Bracket20_24FT:
        currentShooterLowerFF =shooterLowerFF6;
        currentShooterUpperFF = shooterUpperFF6;

        currentShooterLowerKP = shooterLowerKP6;
        currentShooterUpperKP = shooterUpperKP6;

        currentShooterLowerRPM = shooterLowerRPM6;
        currentShooterUpperRPM = shooterUpperRPM6;
        break;

      case Bracket8_12FT:  
      default:
        currentShooterLowerFF =shooterLowerFF3;
        currentShooterUpperFF = shooterUpperFF3;

        currentShooterLowerKP = shooterLowerKP3;
        currentShooterUpperKP = shooterUpperKP3;

        currentShooterLowerRPM = shooterLowerRPM3;
        currentShooterUpperRPM = shooterUpperRPM3;
        break;

    }*/
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
