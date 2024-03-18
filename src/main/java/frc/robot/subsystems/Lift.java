package frc.robot.subsystems;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.XboxController;

public class Lift {

    XboxController secondDriversController = new XboxController(Constants.ControllersConstants.SECOND_DRIVERS_CONTROLLER);
    public PWMSparkMax leftLiftCIM;
    public PWMSparkMax rightLiftCIM;

    public Lift(){
       leftLiftCIM = new PWMSparkMax(Constants.LiftsConstant.LL_CIM_MOTOR); 
       rightLiftCIM = new PWMSparkMax(Constants.LiftsConstant.RL_CIM_MOTOR);
    } 
    public void LiftRobot(PWMSparkMax leftliftCIM , PWMSparkMax rightLiftCIM){
        rightLiftCIM.set(-secondDriversController.getRightY());    
        leftliftCIM.set(-secondDriversController.getLeftX());
    }
}
