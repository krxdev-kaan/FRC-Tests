package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Globals;

public class DriveSystem extends Subsystem {
	// Talons
	VictorSP leftVictor = null;
	VictorSP rightVictor = null;

	DifferentialDrive differentialDrive = null;

    public DriveSystem() 
    {
		// Victors
		leftVictor = new VictorSP(Globals.LEFT_WHEELS_CONTROLLER_PWM_PIN);
		rightVictor = new VictorSP(Globals.RIGHT_WHEELS_CONTROLLER_PWM_PIN);

		differentialDrive = new DifferentialDrive(leftVictor, rightVictor);
	}

    public void arcadeDrive(double moveSpeed, double rotateSpeed, double speedModifier, double rotationModifier) 
    {
        moveSpeed *= speedModifier;
        rotateSpeed *= rotationModifier;
		differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
	}

	@Override
    public void initDefaultCommand() 
    {
        // setDefaultCommand() when implemented
        // or we can just simply use the subsystem ? (maybe?)
	}
}