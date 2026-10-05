
package frc.robot.subsystems.feeder;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Feeder extends SubsystemBase {
  private TalonFX FeederMotor;
  private StatusSignal<Voltage> voltageSignal;
  private StatusSignal<Current> currentSignal;
  private StatusSignal<Velocity> velocitySignal;
  
  public Feeder() {
    FeederMotor = new TalonFX(1);
    voltageSignal = FeederMotor.getMotorVoltage();
  }




  @Override
  public void periodic() {
  }
}
