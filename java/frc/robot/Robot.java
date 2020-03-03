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
import frc.robot.threads.*;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
  
  public static AutonomousIntakeSystem s_AutonomousIntakeSystem;
  public static AutonomousShootingSystem s_AutonomousShootingSystem;
  public static DriveSystem s_DriveSystem;
  public static IntakeSystem s_IntakeSystem;
  public static LiftSystem s_LiftSystem;
  public static ShooterSystem s_ShooterSystem;
  


  private static Thread servoThread = new Thread(new ServoThread()); // FOR TESTING PURPOSES ONLY - SHOULD BE IGNORED
  private static Thread servoThread2 = new Thread(new ServoSecThread()); // FOR TESTING PURPOSES ONLY - SHOULD BE IGNORED
  public static Thread visionProcessingThread = new Thread(new TeleoperationShootingSystemThread());
  private static Thread driveSystemThread = new Thread(new DriveSystemThread());
  private static Thread intakeSystemThread = new Thread(new IntakeSystemThread());


  // FOR TESTING ---------------------------
  public static Servo servo = new Servo(0);
  // FOR TESTING ---------------------------


  public static Joystick joystick;



  @Override
  public void robotInit() 
  {
    joystick = new Joystick(0);

    s_DriveSystem = new DriveSystem();
  }
  
  @Override
  public void robotPeriodic() 
  {

  }

  @Override
  public void autonomousInit() 
  {
    
  }

  @Override
  public void autonomousPeriodic() 
  {

  }

  @Override
  public void teleopInit() 
  {
    
  }

  @Override
  public void teleopPeriodic() 
  {
      driveSystemThread.run();
      intakeSystemThread.run();

      while(!driveSystemThread.isInterrupted() && 
            !intakeSystemThread.isInterrupted()) 
      {

      }
  }

  @Override
  public void disabledInit() 
  {
    
  }

  @Override
  public void testPeriodic() 
  {

  }
}