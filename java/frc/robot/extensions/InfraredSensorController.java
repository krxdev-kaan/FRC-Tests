package frc.robot.extensions;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class InfraredSensorController {
    protected DigitalInput port;


    public InfraredSensorController(int pin) 
    {
       port = new DigitalInput(pin);
    }

    public boolean isObjectInRange() 
    {
        int response = port.get() ? 1 : 0;
        return response == 1 ? false : true;
    }
}