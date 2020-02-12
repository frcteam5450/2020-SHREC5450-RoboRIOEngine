/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
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
  private final Drivetrain drive = new Drivetrain(driveLeft1, driveLeft2, driveRight1, driveRight2, driveMotorType, driveIdleMode, driveRampRate);
  private final Hopper hopper = new Hopper(hopperPort, hopperIdleMode, hopperRampRate);
  private final Shooter shooter = new Shooter(shooterFront, shooterBack, shooterMotorType, shooterIdleMode, shooterRampRate);
  private final Intake intake = new Intake(intakePort, intakeIdleMode, intakeRampRate, photoSensorPort);
  private final Compressor compressor = new Compressor(compPort, pressSwitchPort);

  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  
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
    XboxController _controller1 = new XboxController(controller1);
    drive.setDefaultCommand(new TeleopDrive(drive, _controller1));
    compressor.setDefaultCommand(new CompressorCom(compressor));
    JoystickButton aButton = new JoystickButton(_controller1, 1);
    JoystickButton bButton = new JoystickButton(_controller1, 2);
    JoystickButton rbButton = new JoystickButton(_controller1, 6);
    JoystickButton lbButton = new JoystickButton(_controller1, 5);
    rbButton.whileHeld(new ParallelCommandGroup(new Shoot(shooter, shooterFrontPower, shooteBackPower), new MoveHopper(hopper, hopperPower)));
    aButton.whileHeld(new MoveHopper(hopper, -hopperPower));
    lbButton.whenPressed(new SequentialCommandGroup(new IntakeBall(intake, intakePower), new IndexBall(hopper, hopperPower, indexIncrement, k, endThreshold)));
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
