/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.*;
import static frc.robot.Variables.*;

import frc.robot.autonomous.*;
import frc.robot.commands.*;
import frc.robot.customtriggers.*;
import frc.robot.customtriggers.XboxControllerDPad.DPadDirection;
import frc.robot.subsystems.*;

import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
  private static final Drivetrain drive = new Drivetrain(driveLeft1, driveLeft2, driveRight1, driveRight2, driveMotorType, driveIdleMode, driveRampRate, driveEncConversionFactor);
  private static final Hopper hopper = new Hopper(hopperPort, hopperIdleMode, hopperRampRate);
  private static final Shooter shooter = new Shooter(shooterLower, shooterUpper, shooterMotorType, shooterIdleMode, shooterRampRate);
  private static final Intake intake = new Intake(intakePort, intakeIdleMode, intakeRampRate, photoSensorPort);
  private static final Compressor compressor = new Compressor(compPort, pressSwitchPort);
  private static final Climber climber = new Climber(climberForwardPort, climberReversePort, forwardBrake, reverseBrake, climberStartPos, 7, 8, MotorType.kBrushless, IdleMode.kBrake, 0);
  private static final ControlPanelSpindle spindle = new ControlPanelSpindle(spindleMotorPort, spindleNeutralMode, spindleRampRate);
  private static final VisionClient client = new VisionClient(visLightPort);

  //Controller declarations/definitions
  static XboxController
  driveController = new XboxController(controllerPort1),
  mechController = new XboxController(controllerPort2);

  //Command Selector
  private static final Command 
  defaultAuto = null,
  autoInlineShoot = new InlinePowerPortShoot(drive, shooter, climber, hopper, intake),
  autoCenterShoot = new autoCenterShoot(drive, intake, climber, hopper, shooter),
  autoMoveOffLine = new EncoderBasedDrive(drive, 30, -.25, .005);

  private final SendableChooser<Command> chooser = new SendableChooser<>();
  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    chooser.setDefaultOption("Default Auto - Does Nothing", defaultAuto);
    chooser.addOption("Inline With Power Port, Shoot", autoInlineShoot);
    chooser.addOption("Centered in Field, Shoot", autoCenterShoot);
    chooser.addOption("Move off Line", autoMoveOffLine);
    SmartDashboard.putData("Auto choices", chooser);
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
    startButton1 = new JoystickButton(driveController, startButton);

    XboxControllerTrigger 
    triggerLeft1 = new XboxControllerTrigger(driveController, triggerThreshold, Hand.kLeft),
    triggerRight1 = new XboxControllerTrigger(driveController, triggerThreshold, Hand.kRight);

    XboxControllerDPad
    dPadUp1 = new XboxControllerDPad(driveController, DPadDirection.Up),
    dPadRight1 = new XboxControllerDPad(driveController, DPadDirection.Right),
    dPadDown1 = new XboxControllerDPad(driveController, DPadDirection.Down),
    dPadLeft1 = new XboxControllerDPad(driveController, DPadDirection.Left);

    //Mechanism Controller Button Declarations/definitions
    JoystickButton
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
    dPadUp2 = new XboxControllerDPad(mechController, DPadDirection.Up),
    dPadRight2 = new XboxControllerDPad(mechController, DPadDirection.Right),
    dPadDown2 = new XboxControllerDPad(mechController, DPadDirection.Down),
    dPadLeft2 = new XboxControllerDPad(mechController, DPadDirection.Left);

    //Custom triggers
    VisionViable visionViable = new VisionViable(client);

    //Default Commands, run in background
    drive.setDefaultCommand(new TeleopDrive(drive, driveController, mechController));
    new TriggerContinuously().whileActiveContinuous(new RunContinuously());

    /**
     * Drive Controller Commands
     */

    aButton1.and(visionViable).whenActive(new VisionBasedTurn(drive, client, driveSetAngle, driveAngleFF, driveAngleKP, endThreshold));
    bButton1.whenPressed(new SwapDrive());
    xButton1.whileHeld(new RunSpindle(spindleSpeed, spindle)); //Freely spins ControlPanelSpindle
    //yButton1.whenPressed(new IncrememntSpindle()); DNE
    //rbButton1.whenPressed(new ToggleShooterSpeedControl()); DNE
    selectButton1.whenPressed(new InterruptSubsystems(drive, hopper, shooter, intake, climber, spindle, client)); //interrupts all commands by requiring every subsystem
    dPadUp1.whenActive(new ToggleDrivePower(driveFinePower, driveFastPower));
    dPadDown1.whenActive(new ToggleVisionLight(client));

    /**
     * Mechanism Controller Commands
     */

    //aButton2.whenPressed(new LiftSlidesToPosition()); DNE
    //bButton2.whenPressed(new ClimbToPosition()); DNE
    xButton2.whileHeld(new RunHopper(hopper, hopperPower)); //Runs hopper manually
    bButton2.whileHeld(new RunIntake(intake, -intakePower)); //Runs intake backwards
    yButton2.whenPressed(new ToggleClimb(climber)); //Toggles Climber assembly up/down
    lbButton2.whileHeld(new RunClimber(climber, climberSpeed)); //Lifts slides up
    rbButton2.whileHeld(new RunClimber(climber, -.25)); //Climbs
    startButton2.whileHeld(new RunHopper(hopper, -hopperPower)); //Runs hopper manually - but Backwards!
    selectButton2.whenPressed(new InterruptSubsystems(drive, hopper, shooter, intake, climber, spindle, client));

    triggerLeft2.whileActiveContinuous(
      new SequentialCommandGroup(
        new IntakeBall(intake, intakePower, intakeBallIndexDelay, snagCurrent, rumbleVal, mechController),
        new IndexBall(hopper, hopperFF, indexIncrement, k, endThreshold, bindCurrent, rumbleVal, mechController)
      ));

    triggerRight2.whileActiveContinuous(
      new ParallelCommandGroup(
        //new ShootRPM(shooter, currentShooterUpperRPM, currentShooterLowerRPM, currentShooterUpperFF, currentShooterLowerFF, currentShooterUpperKP, currentShooterLowerKP, shootBallCurrent),
        new Shoot(shooter, shooterLowerFF1, shooterUpperFF1),
        new SequentialCommandGroup(
          new Delay(shooterDelay),
          new RunHopper(hopper, hopperPower)
        ),
        new RunIntake(intake, intakePower)
      ));
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new SequentialCommandGroup(new Delay(2), new VisionBasedTurn(drive, client, driveSetAngle, driveAngleFF, driveAngleKP, endThreshold));
    //return chooser.getSelected();
  }
}
