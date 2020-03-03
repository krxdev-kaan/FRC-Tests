package frc.robot.threads;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class IntakeSystemThread implements Runnable
{
    public void run() 
    {
        if(Robot.joystick.getRawButton(1)) // "A"
        {
            Robot.s_IntakeSystem.startIntakeMotor();
        } 
        
        if(Robot.joystick.getRawButton(2)) // "B"
        {
            Robot.s_IntakeSystem.stopIntakeMotor();
        }

        //IMPLEMENT INFRA RED SCANNING SYSTEM
    }
}