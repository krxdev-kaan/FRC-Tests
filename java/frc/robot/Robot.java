/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.core.*;
import org.opencv.imgproc.*;

import edu.wpi.cscore.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.extensions.Servo;
import frc.robot.extensions.*;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "CAuto";
  private String m_kAutoSelected;
  
  
  
  private final SendableChooser<String> m_chooser = new SendableChooser<>();



  private static DriveSystem s_DriveSystem;
  


  private static Thread servoThread = new Thread(new ServoThread());
  private static Thread servoThread2 = new Thread(new ServoSecThread());
  private static Thread visionProcessingThread;



  public static Servo servo = new Servo(0);



  public static Joystick joystick;



  @Override
  public void robotInit() 
  {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    joystick = new Joystick(0);

    s_DriveSystem = new DriveSystem();

    visionProcessingThread = new Thread(() -> {
      UsbCamera cam = CameraServer.getInstance().startAutomaticCapture(0);
      cam.setFPS(30);
      cam.setResolution(640, 480);

      CvSink cvSink = CameraServer.getInstance().getVideo();
      CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

      Mat mat = new Mat();

      while(true) 
      {
        if(visionProcessingThread.isInterrupted()) break; // Check if this thread is ended

        if (cvSink.grabFrame(mat) == 0) 
        {
					outputStream.notifyError(cvSink.getError()); // Notify if we got an error
          continue;
        }
        
        Imgproc.line(mat, 
              new Point(315, 240), 
              new Point(325, 240), 
              new Scalar(255,0,0), 
              5); // Simply draw the crosshairs horizontal line

        Imgproc.line(mat, 
              new Point(320, 235), 
              new Point(320, 245), 
              new Scalar(255,0,0), 
              5); // Simply draw the crosshairs vertical line

				outputStream.putFrame(mat); // Send the processed frame back to the stream
      }
    });

    visionProcessingThread.start();
  }
  
  @Override
  public void robotPeriodic() 
  {

  }

  @Override
  public void autonomousInit() 
  {
    m_kAutoSelected = m_chooser.getSelected();
  }

  @Override
  public void autonomousPeriodic() 
  {

    switch (m_kAutoSelected) 
    {
      case kCustomAuto:
        break;

      case kDefaultAuto:
        break;

      default:
        break;
    }

  }

  @Override
  public void teleopInit() 
  {
    servoThread.start();  
    servoThread2.start();
  }

  @Override
  public void teleopPeriodic() 
  {
    double rotationSpeed = 0.60;
    double speed = (joystick.getRawAxis(3) + 1) / 2;
    double mVel = -joystick.getRawAxis(1);
    double rVel = joystick.getRawAxis(4);

    s_DriveSystem.arcadeDrive(mVel, rVel, speed, rotationSpeed);
  }

  @Override
  public void disabledInit() 
  {
    servoThread.interrupt();
    servoThread2.interrupt(); 
  }

  @Override
  public void testPeriodic() 
  {

  }
}