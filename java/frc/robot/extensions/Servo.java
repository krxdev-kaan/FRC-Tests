package frc.robot.extensions;

import edu.wpi.first.wpilibj.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** 
 * THIS SYSTEM WILL NEVER BE USED DURING THE MATCH
 * ONLY FOR TESTING
 * CAN BE IGNORED
*/
public class Servo {
    protected int MIN_PULSE_WIDTH = 544;
    protected int MAX_PULSE_WIDTH = 2400;

    protected float lastAngle;

    protected DigitalOutput pinOut;

    protected boolean shouldStopMotor = false;

    public Servo(int pin) {
        pinOut = new DigitalOutput(pin);
        pinOut.setPWMRate(50);
        pinOut.enablePWM(0.02);
    }

    public void setAngle(int angle) {
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

    public void setAngle(int angle, int delay) {
        if (angle < 0) 
        {
            angle = 0;
        } 
        else if (angle > 180) 
        {
            angle = 180;
        }

        float delayInSecondsFromMillis = delay / Math.abs(getAngle() - angle);

        if (angle > (int)getAngle()) 
        {
            for (int i = (int)getAngle(); i <= angle; i++) {
                if (i < 0) 
                {
                    i = 0;
                } 
                else if (i > 180) 
                {
                    i = 180;
                }

                float anglePerc0100 = degreesToPerctange(i);
                setDutyCyclePerc(anglePerc0100);

                long a = (int) delayInSecondsFromMillis;

                try 
                {
                    Thread.sleep(a);
                } catch (InterruptedException e) {}
            }
        } 
        else 
        {
            for (int i = (int)getAngle(); i >= angle; i--) 
            {
                if (i < 0) 
                {
                    i = 0;
                } 
                else if (i > 180) 
                {
                    i = 180;
                }

                float anglePerc0100 = degreesToPerctange(i);
                setDutyCyclePerc(anglePerc0100);

                long a = (int)delayInSecondsFromMillis;

                try 
                {
                    Thread.sleep(a);
                } catch (InterruptedException e) {}
            }
        }

        lastAngle = angle;
    }

    public float getAngle() 
    {
        return lastAngle;
    }

    public void steerRight(int speed) 
    {
        shouldStopMotor = false;

        float baseSeconds = 10;
        float processedSeconds = baseSeconds / speed;
        float processedMillisF = processedSeconds;
        int processedMillis = (int)processedMillisF;

        for (int i = (int)getAngle(); i >= 0; i--) 
        {
            if (shouldStopMotor) 
            {
                shouldStopMotor = false;
                break;
            }

            setAngle(i);

            long a = (int)processedMillis;

            try 
            {
                Thread.sleep(a);
            } catch (InterruptedException e) {}
        }
    }

    public void steerLeft(int speed) 
    {
        shouldStopMotor = false;

        float baseSeconds = 10;
        float processedSeconds = baseSeconds / speed;
        float processedMillisF = processedSeconds;
        int processedMillis = (int)processedMillisF;

        for (int i = (int)getAngle(); i <= 180; i++)
        {
            if (shouldStopMotor) 
            {
                shouldStopMotor = false;
                break;
            }

            setAngle(i);
            
            long a = (int)processedMillis;

            try 
            {
                Thread.sleep(a);
            } catch (InterruptedException e) {}
        }
    }

    public void stopMotor() 
    {
        shouldStopMotor = true;
    }
    
    protected void disableCycles() 
    {
        //pinOut.disablePWM();
        pinOut.updateDutyCycle(0.000);
    }

    protected float degreesToPerctange(float degree) 
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
        float SERVO_MIN_FREQ = 0.02f;

        float binaryRangedPerc = convertToBinaryRange(perc);
        float servoRangedPerc010 = binaryRangedPerc / 10;
        float servoRangedPerc212 = servoRangedPerc010 + SERVO_MIN_FREQ;

        return servoRangedPerc212;
    }
    
    protected void setDutyCyclePerc(float perc) 
    {
        float servoFreqRangedPerc = convertToServoFrequencyRange(perc);

        pinOut.updateDutyCycle(servoFreqRangedPerc);
    }

    protected void manuelDutyCycleUpdate(float p) 
    {
        pinOut.updateDutyCycle(p);
    }
}