

package frc.robot.subsystems.feeder;

import static edu.wpi.first.units.Units.Newton;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.portMap;
import frc.robot.Constants.FeederConstants;
// motor place and משתנים 
public class Feeder extends SubsystemBase {  
  public enum FeederState {
        IDLE,  
      HOLDING,      
       FEEDING,
       EJECT
    }

  private final TalonFX Motor = new TalonFX(portMap.Feeder.MOTOR);
  private final DigitalInput ballSensor = new DigitalInput(portMap.Feeder.EMPTY_SENSOR_DIO);
  private StatusSignal<AngularVelocity> velocitySignal = Motor.getVelocity();
  
    private FeederState currentState = FeederState.IDLE;
    public Feeder(){
      TalonFXConfiguration config = new TalonFXConfiguration();
      config.CurrentLimits.StatorCurrentLimit = FeederConstants.STATOR_CURRENT_LIMIT;
      config.CurrentLimits.StatorCurrentLimitEnable = true;
      
      config.CurrentLimits.SupplyCurrentLimit = FeederConstants.PEAK_CURRENT_LIMIT;
      config.CurrentLimits.SupplyCurrentLimitEnable = true;
  
      config.Feedback.SensorToMechanismRatio =FeederConstants.GEAR_RATIO;
  
      Motor.getConfigurator().apply(config);  
    
      velocitySignal.setUpdateFrequency(50);
      velocitySignal = Motor.getVelocity();
  }

    
  private void setState(FeederState newFeederState){
    this.currentState = newFeederState;
  }
  public FeederState getState(){     
      return currentState;
  }
  
 public boolean hasBallAtEnd(){
   return !ballSensor.get();
 }

@Override
public void periodic() {
    switch (currentState) {
        case IDLE:
            Motor.stopMotor();
            break;

        case HOLDING: 
            Motor.stopMotor();
            break;

        case FEEDING:
            Motor.set(0.8);
            break;

        case EJECT:
         Motor.set(-0.5);
            break;
    }
 }
}