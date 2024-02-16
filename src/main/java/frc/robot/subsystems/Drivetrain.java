package frc.robot.subsystems;


import edu.wpi.first.units.Per;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import frc.robot.Constants;;
public class Drivetrain {

    private static Drivetrain drivetrain = new Drivetrain();

    private VictorSP leftFrontMotor;
    private VictorSP leftRearMotor;
    private VictorSP rightFrontMotor;
    private VictorSP rightRearMotor;

    public static DifferentialDrive driveTrain;

    public Drivetrain(){
        leftFrontMotor = Constants.LF_MOTOR;
        leftRearMotor = Constants.LR_MOTOR;
        rightFrontMotor = Constants.RF_MOTOR;
        rightRearMotor = Constants.RR_MOTOR;

        driveTrain = Constants.ROBOT_DRIVER;
    }

    public void driveHalt(){
        driveTrain.tankDrive(0, 0);
    }

    public static void executeTankDrive(double speed){
        driveTrain.tankDrive(speed, -speed);
    }

}
