/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.extensions.Servo;

public class Robot extends TimedRobot {

  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "CAuto";
  private String m_kAutoSelected;
  
  
  
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  
  
  
  private static VictorSP frontLeftMotor;
  private static VictorSP frontRightMotor;
  private static VictorSP rearLeftMotor;
  private static VictorSP rearRightMotor;

  private static Servo servo = new Servo(0);

  private static Joystick joystick;

  private static DifferentialDrive driver;



  @Override
  public void robotInit() 
  {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    joystick = new Joystick(0);

    frontRightMotor = new VictorSP(0);
    frontLeftMotor = new VictorSP(9);
    rearLeftMotor = new VictorSP(2);
    rearRightMotor = new VictorSP(3);

    driver = new DifferentialDrive(frontLeftMotor, frontRightMotor);

    UsbCamera cam = CameraServer.getInstance().startAutomaticCapture(0);
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
  public void teleopPeriodic() 
  {
    double speed = (joystick.getRawAxis(3) + 1) / 2;
    double mVel = -joystick.getRawAxis(1) * speed;
    double rVel = joystick.getRawAxis(4) * 0.60;

    driver.arcadeDrive(mVel, rVel);

    if (joystick.getPOV() == 90) 
    {
      servo.setAngle(0);
    }
    
    if(joystick.getPOV() == 270) 
    {
      servo.setAngle(180);
    }

    SmartDashboard.putNumber("Moving Speed: ", mVel);
    SmartDashboard.putNumber("Rotation Speed: ", speed);
    SmartDashboard.putNumber("FRONT RIGHT MOTOR: ", frontRightMotor.getSpeed());
    SmartDashboard.putNumber("FRONT LEFT MOTOR: ", frontLeftMotor.getSpeed());
  }

  @Override
  public void testPeriodic() 
  {

  }
}
