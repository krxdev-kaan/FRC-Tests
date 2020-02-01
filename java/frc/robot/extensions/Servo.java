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
        pinOut.setPWMRate(50);
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
        
        float anglePerc0100 = degreesToPerctange(angle);

        lastAngle = angle;
        setDutyCyclePerc(anglePerc0100);
    }

    public float getAngle() 
    {
        return lastAngle;
    }

    protected float degreesToPerctange(int degree) 
    {
        float binaryRangedPerc = degree / 180; //exceptional binary range
        float perc0100 = binaryRangedPerc * 100;

        return perc0100;
    }

    protected float convertToBinaryRange(float perc) 
    {
        float result = perc / 100;
        
        return result;
    }

    protected float convertToServoFrequencyRange(float perc) 
    {
        int SERVO_MIN_FREQ = 2;

        float binaryRangedPerc = convertToBinaryRange(perc);
        float servoRangedPerc010 = binaryRangedPerc * 10;
        float servoRangedPerc212 = servoRangedPerc010 + SERVO_MIN_FREQ;

        return servoRangedPerc212;
    }

    /*protected void writeCycle(float angle) 
    {
        lastAngle = angle;
        pinOut.updateDutyCycle(angle);
    }*/
    
    protected void setDutyCyclePerc(float perc) 
    {
        float servoFreqRangedPerc = convertToServoFrequencyRange(perc);

        pinOut.updateDutyCycle(servoFreqRangedPerc);
    }
}