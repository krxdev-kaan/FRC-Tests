package frc.robot.threads;

import org.opencv.core.*;
import org.opencv.imgproc.*;

import frc.robot.Robot;
import edu.wpi.cscore.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleoperationShootingSystemThread implements Runnable
{
    public void run() 
    {
      UsbCamera cam = CameraServer.getInstance().startAutomaticCapture(0);
      cam.setFPS(30);
      cam.setResolution(640, 480);

      CvSink cvSink = CameraServer.getInstance().getVideo();
      CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

      Mat mat = new Mat();

      while(true) 
      {
        if(Robot.visionProcessingThread.isInterrupted()) break; // Check if this thread is ended

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
    }
}