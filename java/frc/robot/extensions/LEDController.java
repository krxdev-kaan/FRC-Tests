package frc.robot.extensions;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LEDController {
    protected Relay pinOut;

    public LEDController(int pin) {
        pinOut = new Relay(pin, Direction.kForward);
        pinOut.set(Value.kOff);
    }

    public void off() 
    {
        pinOut.set(Value.kOff);
    }

    public void on() 
    {
        pinOut.set(Value.kOn);
    }
}