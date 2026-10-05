

package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.StrictFollower;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.shooterConstants;
// הגדרת משתנים ורכיבי חומרה
public class shooter extends SubsystemBase {
  public enum ShooterState {
        IDLE,        
       RAMPING,     
        AT_VELOCITY, 
        SLOW_EJECT   
    }

  private final TalonFX masterMotor = new TalonFX(shooterConstants.MASTER_MOTOR_ID);
  private final TalonFX followerMotor = new TalonFX(shooterConstants.FOLLOWER_MOTOR_ID);

  private StatusSignal<AngularVelocity> velocitySignal = masterMotor.getVelocity();
  
    private final StrictFollower follower;
  
    private ShooterState currentState = ShooterState.IDLE;
  
  
  
  
    public shooter(){
      TalonFXConfiguration config = new TalonFXConfiguration();
      config.CurrentLimits.StatorCurrentLimit = shooterConstants.STATOR_CURRENT_LIMIT;
      config.CurrentLimits.StatorCurrentLimitEnable = true;
      
      config.CurrentLimits.StatorCurrentLimit = shooterConstants.PEAK_CURRENT_LIMIT;
      config.CurrentLimits.StatorCurrentLimitEnable = true;
  
      config.Feedback.SensorToMechanismRatio =shooterConstants.Gear_Ratio;
  
      masterMotor.getConfigurator().apply(config);
      followerMotor.getConfigurator().apply(config);
  
     follower = new StrictFollower(masterMotor.getDeviceID());
     followerMotor.setControl(follower);
  
    
      velocitySignal.setUpdateFrequency(50);
      velocitySignal = masterMotor.getVelocity();
  }

  public void setVelocity(double Velocity){
    masterMotor.setControl(new VelocityVoltage(Velocity));
  }
public double getCurrentVelocity() {
        return velocitySignal.refresh().getValueAsDouble(); 
    }
  private void setState(ShooterState newShooterState){
    this.currentState = newShooterState;
  }
  public ShooterState getState(){ 
      return currentState;
  }
  
  public boolean isAtTargetVelocity() {
    double currentVel = getCurrentVelocity();
    return Math.abs(currentVel - 60) <= 2.0;
}

@Override
public void periodic() {
    switch (currentState) {
        case IDLE:
            masterMotor.stopMotor();
            break;

        case RAMPING:
            setVelocity(60.0);
            if (isAtTargetVelocity()) {
                currentState = ShooterState.AT_VELOCITY;
            }
            break;

        case AT_VELOCITY:
            setVelocity(60.0);
            if (!isAtTargetVelocity()) {
                currentState = ShooterState.RAMPING;
            }
            break;

        case SLOW_EJECT:
            setVelocity(15.0);
            break;
    }
}
}