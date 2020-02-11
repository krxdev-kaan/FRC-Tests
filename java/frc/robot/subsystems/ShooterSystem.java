package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Globals;

public class ShooterSystem extends Subsystem {
	// Talons
	VictorSP shooterVictor = null;

    public ShooterSystem() 
    {
		// Victors
		shooterVictor = new VictorSP(Globals.SHOOTER_MOTOR_CONTROLLER_PWM_PIN);
    }
    
    // IMPLEMENT FUNCTIONS AFTER STRATEGY IS DISCUSSED

	@Override
    public void initDefaultCommand() 
    {
        // setDefaultCommand() when implemented
        // or we can just simply use the subsystem ? (maybe?)
	}
}