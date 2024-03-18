// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.IntakeEncoder;
import frc.robot.subsystems.Lift;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private XboxController firstDriverController;
  private XboxController secondDriverController;
  private Drivetrain m_drivetrain_test;
  private Lift m_lift_test;
  private LauncherSubsystem m_launcher;
  private DifferentialDrive m_front_drive;
  private DifferentialDrive m_rear_drive;
  private IntakeEncoder m_intake_encoder;
  //private VictorTest m_test;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    firstDriverController = new XboxController(Constants.ControllersConstants.FIRST_DRIVERS_CONTROLLER);
    secondDriverController = new XboxController(Constants.ControllersConstants.SECOND_DRIVERS_CONTROLLER);
    m_launcher = new LauncherSubsystem();
    m_lift_test = new Lift();
    m_intake_encoder = new IntakeEncoder();
    m_drivetrain_test = new Drivetrain();
    m_front_drive = Constants.FRONT_DRIVE;
    m_rear_drive = Constants.REAR_DRIVE;
    //m_test = new VictorTest();
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override//
  public void teleopInit() {

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double degree = firstDriverController.getRightX();
    double throttle = firstDriverController.getLeftTriggerAxis();
    double spd =  firstDriverController.getLeftY();
    double rotation = firstDriverController.getLeftX();
    double drivetrainthrottle = firstDriverController.getRightTriggerAxis();
    // encoderInst.RobotIntake(encoderInst.intakeNEO);
    m_intake_encoder.IntakeLift(degree,throttle);
    m_drivetrain_test.arcadeDrv( -spd , -rotation, drivetrainthrottle, m_front_drive);
    m_drivetrain_test.arcadeDrv( -spd , -rotation, drivetrainthrottle, m_rear_drive);
    m_lift_test.LiftRobot(m_lift_test.leftLiftCIM, m_lift_test.rightLiftCIM);

    m_launcher.throwValue();

    if (firstDriverController.getAButtonPressed()) {
      m_intake_encoder.IntakeStart();
      m_launcher.startThrowing();
    }

    m_intake_encoder.MotorTest();
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();

  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
    
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}