package frc.robot.extensions;

import edu.wpi.first.wpilibj.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Servo {
    protected int MIN_PULSE_ANGLE = 544;
    protected int MAX_PULSE_ANGLE = 2400;

    protected float lastAngle;

    protected DigitalOutput pinOut;

    public Servo(int pin) 
    {
        pinOut = new DigitalOutput(pin);
        pinOut.setPWMRate(12000);
        pinOut.enablePWM(0);
    }

    public void setAngle(int angle) 
    {
        if (angle < 0) 
        {
            angle = 0;
        }
        else if (angle > 180) 
        {
            angle = 180;
        }

        float angleInZO = angle / 180;
        float returnerWidth = MAX_PULSE_ANGLE - MIN_PULSE_ANGLE;
        float resultInReturnerWidth = angleInZO * returnerWidth;
        float result = MIN_PULSE_ANGLE + resultInReturnerWidth;

        writeCycle(result / 2400);
    }

    public float getAngle() 
    {
        return ((lastAngle * 2400 - MIN_PULSE_ANGLE) / 1856) * 180;
    }

    protected void writeCycle(float angle) 
    {
        lastAngle = angle;
        pinOut.updateDutyCycle(angle);
    }
}