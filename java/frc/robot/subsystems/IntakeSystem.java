package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Globals;

public class IntakeSystem extends Subsystem {
	  // Victors
	  VictorSP intakeVictor = null;
	  VictorSP conveyorVictor = null;

    public IntakeSystem() 
    {
		  // Victors
		  intakeVictor = new VictorSP(Globals.INTAKE_MOTOR_CONTROLLER_PWM_PIN);
		  conveyorVictor = new VictorSP(Globals.CONVEYOR_MOTOR_CONTROLLER_PWN_PIN);
    }
    
    public void startIntakeMotor() 
    {
      intakeVictor.set(Globals.INTAKE_SPEED_RNG_1_U1);
    }

    public void stopIntakeMotor() 
    {
      intakeVictor.set(0.0);
    }

    public void startConveyorMotor() 
    {
      conveyorVictor.set(Globals.CONVEYOR_SPEED_RNG_1_U1);
    }

    public void stopConveyorMotor() 
    {
      conveyorVictor.set(0.0);
    }

	  @Override
    public void initDefaultCommand() 
    {
      // setDefaultCommand() when implemented
      // or we can just simply use the subsystem ? (maybe?)
	  }
}