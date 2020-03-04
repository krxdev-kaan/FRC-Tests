package frc.robot.extensions;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** 
 * THIS THREAD WILL NEVER BE USED DURING THE MATCH
 * ONLY FOR TESTING
 * CAN BE IGNORED
*/
public class ServoSecThread implements Runnable
{
    public void run() 
    {
        while (true)
        {
            if (Robot.joystick.getPOV() == 90) 
            {
                Robot.servo.steerRight(2);
            }
            else if(Robot.joystick.getPOV() == 270) 
            {
                Robot.servo.steerLeft(2);
            }
            else 
            {
                //WAIT
            }
        }
    }
}