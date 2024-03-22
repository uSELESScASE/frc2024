package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;

public class DrivetrainSubsystem {
    private final DifferentialDrive frontDrive = Constants.FRONT_DRIVE;
    private final DifferentialDrive rearDrive = Constants.REAR_DRIVE;
    
    public void arcadeDrv(double spd, double rotation, DifferentialDrive drive){
        drive.arcadeDrive(spd, rotation, true);
    }

    public void driveBoth(double spd, double rotation){
        arcadeDrv(spd,rotation,frontDrive);
        arcadeDrv(spd,rotation,rearDrive);
    }
}