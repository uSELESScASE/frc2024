package frc.robot.subsystems;
import frc.robot.Constants;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.filter.LinearFilter;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class IntakeEncoder extends SubsystemBase {
  public XboxController firstDriversController = new XboxController(Constants.ControllersConstants.SECOND_DRIVERS_CONTROLLER);
  private CANSparkMax liftIntakeNEO;
  private RelativeEncoder encoder;
  public XboxController secondDriversController = new XboxController(Constants.ControllersConstants.SECOND_DRIVERS_CONTROLLER);
  public PWMSparkMax intakeNEO = new PWMSparkMax(3);


  LinearFilter smoothFilter = LinearFilter.movingAverage(25);

  public IntakeEncoder() {
    liftIntakeNEO = new CANSparkMax(26, CANSparkLowLevel.MotorType.kBrushless);
    encoder = liftIntakeNEO.getEncoder();
  }

  public void setPosToIntake(){

    double tempPosition;
    tempPosition = encoder.getPosition();

    if(firstDriversController.getAButton()){
      encoder.setPosition(0);
      while(tempPosition > 0){
        liftIntakeNEO.set(0.2);
        if(encoder.getPosition() == -tempPosition){
          liftIntakeNEO.set(0);
          tempPosition = 0;
          break;
        }
      }
    }
  }

  public void setPosToShooter(){
    double tempPosition;
    tempPosition = encoder.getPosition();
    if(firstDriversController.getBButton()){
      encoder.setPosition(0);
      while(tempPosition == 0){
        liftIntakeNEO.set(0.2);
        if(encoder.getPosition() == 5){
          liftIntakeNEO.set(0);
          //shooter kodu çağrılacak
          new WaitCommand(5);
          setPosToIntake();
          break;
        }
      }
    }
  }

  public void setPosToLauncher(){
    double tempPosition;
    tempPosition = encoder.getPosition();

    if(firstDriversController.getBButton()){
      encoder.setPosition(0);

      while(tempPosition == 0){
        liftIntakeNEO.set(0.2);
        if(encoder.getPosition() == 10){
          liftIntakeNEO.set(0);
          //launcher kodu çağrılacak
          new WaitCommand(5);
          setPosToIntake();
          break;
        }
      }
    }
  }

  public void MotorTest() {
    double yAxis = firstDriversController.getLeftY();

    double pos = encoder.getPosition();
    double rpm = encoder.getPositionConversionFactor();

    if (firstDriversController.getXButton()) {
        double value = rpm + 10;
        encoder.setPositionConversionFactor(value);
    }
    if (firstDriversController.getYButton()) {
        double value = rpm + -10;
        encoder.setPositionConversionFactor(value);
    }

    System.out.println("RPM = " + rpm);
    System.out.println("Pos = " + pos);
  }


  public void IntakeLift(double degree , double throttle){
    double limiter;
    if (throttle >= 0.3){
        limiter = 0.3;
    }
    else if (throttle <= -0.3){
        limiter = -0.3; 
    }
    else {
        limiter = throttle;
    }
    
    degree *= limiter;
    double  smoothFiltereddegree = smoothFilter.calculate(degree);
    liftIntakeNEO.set(-smoothFiltereddegree);
  }

  public void RobotIntake(PWMSparkMax intakeNEO){
    Timer timer = new Timer();

    timer.start();
    do {
      intakeNEO.set(0.5);
    }
    while (timer.get() < 2);
    intakeNEO.set(0);
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
}