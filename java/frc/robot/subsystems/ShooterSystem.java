package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Globals;

public class ShooterSystem extends Subsystem {
	  // Victors
	  VictorSP shooterVictor = null;

    public ShooterSystem() 
    {
		  // Victors
		  shooterVictor = new VictorSP(Globals.SHOOTER_MOTOR_CONTROLLER_PWM_PIN);
    }
    
    public void fireUpShooter() 
    {
      shooterVictor.set(Globals.SHOOTER_SPEED_RNG_1_U1);
    }

    public void stopShooter() 
    {
      shooterVictor.set(0.0);
    }

	  @Override
    public void initDefaultCommand() 
    {
      // setDefaultCommand() when implemented
      // or we can just simply use the subsystem ? (maybe?)
	  }
}