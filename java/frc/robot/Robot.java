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
  private static DriveSystemThread driveSystemThread = new DriveSystemThread();
  private static IntakeSystemThread intakeSystemThread = new IntakeSystemThread();
  private static LiftSystemThread liftSystemThread = new LiftSystemThread();


  // FOR TESTING ---------------------------
  public static Servo servo = new Servo(0);
  // FOR TESTING ---------------------------


  public static Joystick joystick;
  public static LEDController led = new LEDController(0);



  @Override
  public void robotInit() 
  {
    joystick = new Joystick(0);

    s_DriveSystem = new DriveSystem();
    s_IntakeSystem = new IntakeSystem();
    //s_AutonomousIntakeSystem = new AutonomousIntakeSystem();
    //s_AutonomousShootingSystem = new AutonomousShootingSystem();
    s_LiftSystem = new LiftSystem();
    //s_ShooterSystem = new ShooterSystem();
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
    /*double rotationSpeed = 0.60;
    double speed = (joystick.getRawAxis(3) + 1) / 2;
    double mVel = -joystick.getRawAxis(1);
    double rVel = joystick.getRawAxis(4);


    s_DriveSystem.arcadeDrive(mVel, rVel, speed, rotationSpeed);*/


    driveSystemThread.run();
    intakeSystemThread.run();
    liftSystemThread.run();
    
    if(joystick.getRawButton(3)) 
    {
      led.on();
    }
    if(joystick.getRawButton(4)) 
    {
      led.off();
    }


    /*if(joystick.getRawButton(1)) // "A"
    {
      s_IntakeSystem.startIntakeMotor();
    } 
        
    if(joystick.getRawButton(2)) // "B"
    {
      s_IntakeSystem.stopIntakeMotor();
    }*/
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