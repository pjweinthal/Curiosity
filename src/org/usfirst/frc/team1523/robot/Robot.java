
package org.usfirst.frc.team1523.robot;

import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team1523.robot.commands.AutoGear;
import org.usfirst.frc.team1523.robot.commands.DriveAcrossBaseLine;
import org.usfirst.frc.team1523.robot.subsystems.Ball;
import org.usfirst.frc.team1523.robot.subsystems.Drive;
import org.usfirst.frc.team1523.robot.subsystems.Gear;
import org.usfirst.frc.team1523.robot.subsystems.Rope;
import org.usfirst.frc.team1523.vision.GripPipeline;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

public class Robot extends IterativeRobot {

	//Create global subsystem objects
	public static OI oi;
	public static Drive drive;
	public static Ball ball;
	public static Gear gear;
	public static Rope rope;

	//Create global sensor and compressor objects
	public static Gyro gyro;
	public static Compressor comp;

	//Create variables for Vison Thread
	public static VisionThread vision;
	public static double x;
	public static double distance;
	//**********************Important*********************
	public static Object imgLock = new Object(); //Creates a lock for the asynchronous thread to prevent lag in variable updates
	//*********************************************************

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		//Initialize Network Tables
		NetworkTable.initialize();
		NetworkTable.flush();

		//Create Subsystem Objects
		comp = new Compressor(0);
		comp.setClosedLoopControl(true); //Turns on compressor
		gyro = new ADXRS450_Gyro();	//Turns on Gyro
		drive = new Drive();
		ball = new Ball();
		gear = new Gear();
		rope = new Rope();
		oi = new OI();

		//Reset Hardware Values
		drive.reset();
		gyro.calibrate();

		//Create Autonomous Chooser
		chooser.addObject("Center", new AutoGear(1));
		chooser.addObject("Left Shoot", new AutoGear(0));
		chooser.addObject("Right Shoot", new AutoGear(2));
		chooser.addObject("Left No Shoot", new AutoGear(3));
		chooser.addObject("Right No Shoot", new AutoGear(4));
		chooser.addObject("Drive Across Baseline", new DriveAcrossBaseLine());
		chooser.addDefault("Nothing", null);
		SmartDashboard.putData("Auto Choice", chooser);
		
		//Start Vision Thread
		vision = new VisionThread(CameraServer.getInstance().startAutomaticCapture(0), new GripPipeline(), grip ->{
			if(grip.convexHullsOutput().size()==2){	//If you have two vision targets
				//Get the dimentsions of both
				Rect r1 = Imgproc.boundingRect(grip.convexHullsOutput().get(0));
				Rect r2 = Imgproc.boundingRect(grip.convexHullsOutput().get(1));
				synchronized(imgLock){
					//Set values
					x = ((r1.x + r1.width/2) + (r2.x + r2.width/2)) / 2;
					distance = Math.abs(r1.x - r2.x);
					System.out.println("STEP: X: " + x + " Distance: " + distance);
				}
			}else{
				//Oh no no target
				System.out.println("NO TARGET FOUND" + grip.convexHullsOutput().size());
				//Stop
				synchronized(imgLock){
					x = -1;
					distance = -1;
				}
			}
		});

		vision.start();
	}

	@Override
	public void disabledInit() {
		drive.reset();
		gyro.reset();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void autonomousInit() {
		//Auto Camera Settings
		autonomousCommand = chooser.getSelected();
		if (autonomousCommand != null) autonomousCommand.start();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	@Override
	public void teleopInit() {
		//Teleop Camera Settings
		drive.reset();
		gyro.reset();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		log();
	}

	public void log(){
		//Put important data in dashboard
		SmartDashboard.putNumber("Right Drive", drive.getRightDistance());
		SmartDashboard.putNumber("Left Drive", drive.getLeftDistance());
		SmartDashboard.putNumber("Gyro Reading", gyro.getAngle());
	}
}
