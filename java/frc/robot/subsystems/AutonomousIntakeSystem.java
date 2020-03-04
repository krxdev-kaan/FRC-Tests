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

public class AutonomousIntakeSystem extends Subsystem {
    
    private static Pixy2 pixyCam;
    
    private boolean isActive = false;

    public AutonomousIntakeSystem() 
    {
        pixyCam = Pixy2.createInstance(LinkType.SPI);
        pixyCam.init();
    }

    public void startSystem() 
    {
        Pixy2Video video = pixyCam.getVideo();
    }
    
    public void startAutonomous() 
    {
        isActive = true;
    }

    public void stopAutonomous() 
    {
        isActive = false;
    }

    public void detectAndRun() 
    {
        Pixy2CCC ccc = pixyCam.getCCC();
        int width = pixyCam.getFrameWidth();
        System.out.println("frame: " + width);

        int blockCount = ccc.getBlocks();

        if(blockCount > 0) 
        {
            int indexToManipulate = 0;
            int lastestAge = 0;
            /*for(int i = 0; i < blockCount; i++) 
            {
                if(ccc.getBlockCache().get(i).getAge() > lastestAge) 
                {
                    lastestAge = ccc.getBlockCache().get(i).getAge();
                    indexToManipulate = i;
                }
            }*/
            int xValue = (ccc.getBlockCache().get(indexToManipulate).getWidth() / 2) + ccc.getBlockCache().get(indexToManipulate).getX();
            System.out.println("Debug Value: " + xValue);

            if (xValue < ((width / 2) - 10) ) //  
            {
                double turnModifier = (((width / 2) - 10) - xValue) / ((width / 2) - 10) / 10;
                Robot.s_DriveSystem.leftVictor.set(-Globals.AUTONOMOUS_INTAKE_LEAN_SPEED);
                Robot.s_DriveSystem.rightVictor.set(Globals.AUTONOMOUS_INTAKE_TURN_SPEED - turnModifier);
            } 
            else if (xValue > ((width / 2) + 10)) 
            {
                double turnModifier = (-(((width / 2) + 10) - xValue)) / ((width / 2) - 10) / 10;
                Robot.s_DriveSystem.leftVictor.set(-(Globals.AUTONOMOUS_INTAKE_TURN_SPEED - turnModifier));
                Robot.s_DriveSystem.rightVictor.set(Globals.AUTONOMOUS_INTAKE_LEAN_SPEED);
            } 
            else 
            {
                Robot.s_DriveSystem.leftVictor.set(-Globals.AUTONOMOUS_INTAKE_LEAN_SPEED);
                Robot.s_DriveSystem.rightVictor.set(Globals.AUTONOMOUS_INTAKE_LEAN_SPEED);
            }
        } 
        else 
        {
            Robot.s_DriveSystem.differentialDrive.stopMotor();
        }
    }

	@Override
    public void initDefaultCommand() 
    {
        // setDefaultCommand() when implemented
        // or we can just simply use the subsystem ? (maybe?)
	}
}