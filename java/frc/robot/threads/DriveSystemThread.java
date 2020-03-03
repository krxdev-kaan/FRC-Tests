package frc.robot.threads;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSystemThread implements Runnable
{
    public void run() 
    {
        double rotationSpeed = 0.60;
        double speed = (Robot.joystick.getRawAxis(3) + 1) / 2;
        double mVel = -Robot.joystick.getRawAxis(1);
        double rVel = Robot.joystick.getRawAxis(4);
    

        Robot.s_DriveSystem.arcadeDrive(mVel, rVel, speed, rotationSpeed);
    }
}