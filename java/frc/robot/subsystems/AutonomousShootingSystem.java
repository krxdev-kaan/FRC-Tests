package frc.robot.subsystems;

import io.github.pseudoresonance.pixy2api.*;
import io.github.pseudoresonance.pixy2api.Pixy2.LinkType;
import io.github.pseudoresonance.pixy2api.links.*;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Globals;
import frc.robot.Robot;

public class AutonomousShootingSystem extends Subsystem {
    
    public AutonomousShootingSystem() 
    {
        
    }

	@Override
    public void initDefaultCommand() 
    {
        // setDefaultCommand() when implemented
        // or we can just simply use the subsystem ? (maybe?)
	}
}