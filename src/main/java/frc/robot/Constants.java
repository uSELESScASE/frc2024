// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static final class LaunchersConstants {
      public static final int LAUNCHER_CONTROLLER_PORT = 1; // Port number for the launchers controller
      public static final int LEFT_CIM_MOTOR = 0; 
      public static final int RIGHT_CIM_MOTOR = 1; 
  }

  public static final int CHASSIS_CONTROLLER_PORT = 2; // Port number for the chassis controller
  public static final int LF_MOTOR_CHANNEL = 1;
  public static final int RF_MOTOR_CHANNEL = 1;
  public static final int LR_MOTOR_CHANNEL = 1;
  public static final int RR_MOTOR_CHANNEL = 1;

  public static final VictorSP LF_MOTOR = new VictorSP(LF_MOTOR_CHANNEL);
  public static final VictorSP RF_MOTOR = new VictorSP(RF_MOTOR_CHANNEL);
  public static final VictorSP LR_MOTOR = new VictorSP(LR_MOTOR_CHANNEL);
  public static final VictorSP RR_MOTOR = new VictorSP(RR_MOTOR_CHANNEL);

  public static final DifferentialDrive ROBOT_DRIVER = new DifferentialDrive(LF_MOTOR, RF_MOTOR);
}
