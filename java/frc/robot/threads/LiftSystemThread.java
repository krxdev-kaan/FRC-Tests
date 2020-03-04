package frc.robot.threads;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LiftSystemThread implements Runnable
{
    public void run() 
    {
        if(Robot.joystick.getRawButton(6)) 
        {
            Robot.s_LiftSystem.ascendLiftSystem();
        }
        else 
        {
            Robot.s_LiftSystem.stopLiftSystem();
        }

        if(Robot.joystick.getRawButton(5)) 
        {
            Robot.s_LiftSystem.descendLiftSystem();
        }
    }
}