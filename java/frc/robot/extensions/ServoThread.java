package frc.robot.extensions;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ServoThread implements Runnable
{
    public void run() 
    {
        while (true) 
        {
            if (Robot.joystick.getPOV() == 90) 
            {
                //WAIT
            }
            else if(Robot.joystick.getPOV() == 270) 
            {
                //WAIT
            }
            else 
            {
                Robot.servo.stopMotor();
            }
        }
    }
}