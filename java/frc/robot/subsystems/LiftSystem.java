package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Globals;

public class LiftSystem extends Subsystem {
	// Talons
	VictorSP liftVictor = null;

    public LiftSystem() 
    {
		// Victors
		liftVictor = new VictorSP(Globals.LIFT_MOTOR_CONTROLLER_PWM_PIN);
    }
    
    // IMPLEMENT FUNCTIONS AFTER STRATEGY IS DISCUSSED

	@Override
    public void initDefaultCommand() 
    {
        // setDefaultCommand() when implemented
        // or we can just simply use the subsystem ? (maybe?)
	}
}