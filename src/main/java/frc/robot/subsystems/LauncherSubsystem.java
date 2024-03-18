// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class LauncherSubsystem extends SubsystemBase {

  public XboxController firstDriversController = new XboxController(Constants.ControllersConstants.FIRST_DRIVERS_CONTROLLER);
  public PWMSparkMax leftCIMMotor; 
  public PWMSparkMax rightCIMMotor;


  public LauncherSubsystem() {
    leftCIMMotor = new PWMSparkMax(Constants.LaunchersConstants.LEFT_CIM_MOTOR);  // CIM motor left.
    rightCIMMotor = new PWMSparkMax(Constants.LaunchersConstants.RIGHT_CIM_MOTOR); // CIM motor right.
  }

  public void startThrowing(){
    
    Timer timer = new Timer();

    timer.start();
    do {
      leftCIMMotor.set();
      rightCIMMotor.set(1);
    }
    while (timer.get() < 4);

    leftCIMMotor.set(0);
    rightCIMMotor.set(0);
  }

  public void throwValue(){
    System.out.println("Left CIM = " + leftCIMMotor.get());
    System.out.println("Right CIM = " + rightCIMMotor.get());
  }

  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

public Command exampleMethodCommand() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'exampleMethodCommand'");
}
}
