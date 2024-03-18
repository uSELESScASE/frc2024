package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.units.Per;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import frc.robot.Constants;

public class Drivetrain {

    private static Drivetrain drivetrain = new Drivetrain();

    private WPI_VictorSPX leftFrontMotor;
    private WPI_VictorSPX leftRearMotor;
    private WPI_VictorSPX rightFrontMotor;
    private WPI_VictorSPX rightRearMotor;
    public static DifferentialDrive driveTrain;

    public Drivetrain(){
        leftFrontMotor = Constants.LF_MOTOR;
        leftRearMotor = Constants.LR_MOTOR;
        rightFrontMotor = Constants.RF_MOTOR;
        rightRearMotor = Constants.RR_MOTOR;
    }

    public void driveHalt(){
        driveTrain.tankDrive(0, 0);
    }
    
    public void arcadeDrv(double spd, double rotation, double drivethrottle, DifferentialDrive drive){
        spd *= drivethrottle;
        rotation *= drivethrottle;
        drive.arcadeDrive(spd, rotation, true);
    }

    public static void executeTankDrive(double leftSpeed, double rightSpeed, DifferentialDrive drive){
        drive.tankDrive(leftSpeed,rightSpeed);
    }

}