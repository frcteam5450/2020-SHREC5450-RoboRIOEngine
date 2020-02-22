/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.*;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.*;
import frc.robot.customtriggers.*;
import frc.robot.customtriggers.XboxControllerDPad.DPadDirection;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem(0, null, 0, 0);
  public static final Drivetrain drive = new Drivetrain(driveLeft1, driveLeft2, driveRight1, driveRight2, driveMotorType, driveIdleMode, driveRampRate);
  public static final Hopper hopper = new Hopper(hopperPort, hopperIdleMode, hopperRampRate);
  public static final Shooter shooter = new Shooter(shooterLower, shooterUpper, shooterMotorType, shooterIdleMode, shooterRampRate);
  public static final Intake intake = new Intake(intakePort, intakeIdleMode, intakeRampRate, photoSensorPort);
  public static final Compressor compressor = new Compressor(compPort, pressSwitchPort);
  public static final Climber climber = new Climber(climberForwardPort, climberReversePort, climberStartPos, 7, 8, MotorType.kBrushless, IdleMode.kBrake, 0);
  public static final ControlPanelSpindle spindle = new ControlPanelSpindle(spindleMotorPort, spindleNeutralMode, spindleRampRate);
  public static final VisionClient client = new VisionClient(visLightPort);

  //Controller declarations/definitions
  static XboxController
  driveController = new XboxController(controllerPort1),
  mechController = new XboxController(controllerPort2);
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /**
     * Controller and Button Declarations and Definitions
     */

    //Drive Controller Button Declarations/definitions
    JoystickButton 
    aButton1 = new JoystickButton(driveController, aButton),
    bButton1 = new JoystickButton(driveController, bButton),
    xButton1 = new JoystickButton(driveController, xButton),
    yButton1 = new JoystickButton(driveController, yButton),
    lbButton1 = new JoystickButton(driveController, lbButton),
    rbButton1 = new JoystickButton(driveController, rbButton),
    selectButton1 = new JoystickButton(driveController, selectButton),
    startButton1 = new JoystickButton(driveController, startButton),

    //Mechanism Controller Button Declarations/definitions
    aButton2 = new JoystickButton(mechController, aButton),
    bButton2 = new JoystickButton(mechController, bButton),
    xButton2 = new JoystickButton(mechController, xButton),
    yButton2 = new JoystickButton(mechController, yButton),
    lbButton2 = new JoystickButton(mechController, lbButton),
    rbButton2 = new JoystickButton(mechController, rbButton),
    selectButton2 = new JoystickButton(mechController, selectButton),
    startButton2 = new JoystickButton(mechController, startButton);

    XboxControllerTrigger 
    triggerLeft2 = new XboxControllerTrigger(mechController, triggerThreshold, Hand.kLeft),
    triggerRight2 = new XboxControllerTrigger(mechController, triggerThreshold, Hand.kRight);

    XboxControllerDPad
    dpadUp1 = new XboxControllerDPad(driveController, DPadDirection.Up);

    //Default Commands, run in background
    drive.setDefaultCommand(new TeleopDrive(drive, driveController, mechController));

    /**
     * Drive Controller Commands
     */

    //aButton1.whenPressed(new AutoAlign()) DNE
    bButton1.whenPressed(new SwapDrive());
    xButton1.whileHeld(new RunSpindle(spindleSpeed, spindle)); //Freely spins ControlPanelSpindle
    //yButton1.whenPressed(new IncrememntSpindle()); DNE
    //rbButton1.whenPressed(new ToggleShooterSpeedControl()); DNE
    selectButton1.whenPressed(new KillAllCommands(drive, hopper, shooter, intake, climber, spindle, client)); //interrupts all commands by requiring every subsystem
    dpadUp1.whenActive(new ToggleDrivePower(driveFinePower, driveFastPower));

    /**
     * Mechanism Controller Commands
     */

    //aButton2.whenPressed(new LiftSlidesToPosition()); DNE
    //bButton2.whenPressed(new ClimbToPosition()); DNE
    xButton2.whileHeld(new RunHopper(hopper, hopperPower)); //Runs hopper manually
    bButton2.whileHeld(new RunIntake(intake, -intakePower)); //Runs intake backwards
    yButton2.whenPressed(new ToggleClimb(climber)); //Toggles Climber assembly up/down
    lbButton2.whileHeld(new RunClimber(climber, climberSpeed)); //Lifts slides up
    rbButton2.whileHeld(new RunClimber(climber, -climberSpeed)); //Climbs
    startButton2.whileHeld(new RunHopper(hopper, -hopperPower)); //Runs hopper manually - but Backwards!
    selectButton2.whenPressed(new KillAllCommands(drive, hopper, shooter, intake, climber, spindle, client));

    triggerLeft2.whenActive(
      new SequentialCommandGroup(
        new IntakeBall(intake, intakePower, intakeBallIndexDelay),
        new IndexBall(hopper, hopperFF, indexIncrement, k, endThreshold)
      ));

    triggerRight2.whileActiveContinuous(
      new ParallelCommandGroup(
        new ShootRPM(shooter, shooterUpperRPM, shooterLowerRPM, shooterUpperFF, shooterLowerFF, shooterUpperKP, shooterLowerKP, triggerThreshold, mechController),
        new SequentialCommandGroup(
          new Delay(shooterDelay),
          new RunHopper(hopper, hopperPower)
        )
      ));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
