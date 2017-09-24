package org.usfirst.frc.team1523.robot.subsystems;

import org.usfirst.frc.team1523.robot.RobotMap;
import org.usfirst.frc.team1523.robot.commands.DriveJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drive extends Subsystem {
	
	private CANTalon frontR;
	private CANTalon frontL;
	private CANTalon rearR;
	private CANTalon rearL;
	
	private RobotDrive drive;
	
	/**
	 * Class that handles the drive mechanisms
	 */
	public Drive(){
		//For mecanum drive one of the sides has to be inverted for it to drive straight it will not always be thr left side
		frontR = new CANTalon(RobotMap.DRIVE_TALON_FRONT_R);
		frontL = new CANTalon(RobotMap.DRIVE_TALON_FRONT_L);
		frontL.setInverted(true);	
		
		rearR = new CANTalon(RobotMap.DRIVE_TALON_REAR_R);
		rearL = new CANTalon(RobotMap.DRIVE_TALON_REAR_L);
		rearL.setInverted(true);

		drive = new RobotDrive(rearL, frontL, rearR, frontR);
	}
	
	/**
	 * Use joystick object to control mecanum drive system
	 * @param stick Joystick Object
	 */
	public void drive(Joystick stick){
		drive.mecanumDrive_Cartesian(joystickCorrection(stick.getX(), 0.1), joystickCorrection(stick.getY(), 0.1), -joystickCorrection(stick.getZ(), 0.198), 0);
	}
	
	/**
	 * Manual input of simulated joystick values
	 * @param x X value -1.0 to 1.0
	 * @param y Y value -1.0 to 1.0 inverted
	 * @param z Z value -1.0 t0 1.0
	 */
	public void drive(double x, double y, double z){
		drive.mecanumDrive_Cartesian(x, y, z, 0);
	}
	
	/**
	 * @return Calculated distance from the front right encoder in inches
	 */
	public double getRightDistance(){
		return rearR.getEncPosition()*RobotMap.DISTANCE_PER_PULSE;
	}
	
	/**
	 * @return Calculated distance from the front left encoder in inches
	 */
	public double getLeftDistance(){
		return rearL.getEncPosition()*RobotMap.DISTANCE_PER_PULSE;
	}
	
	/**
	 * Reset both encoders to 0
	 */
	public void reset(){
		rearR.setEncPosition(0);
		rearL.setEncPosition(0);
	}
	
	/**
	 * Stop motor
	 */
	public void stop(){
		drive.stopMotor();
	}
	
	/**
	 * Creates an adaptive deadband that does not jump in value
	 * @param input	raw joystick input
	 * @param value	adjusted 0
	 * @return new adjusted value
	 */
	private double deadband(double input, double value){
		if(Math.abs(input) < value) return 0; //Deadband
		
	    else if(input>0) return (input-value)/(1-value); //Positive return
		
	    else return -(Math.abs(input)-value)/(1-value); //Negative return
	}
	
	private double squared(double input){
		//Make sure to keep the sign so the direction you input is not changed by squaring it
		if(input>0){
			return input*input;
		}else{
			return -(input*input);
		}
	}
	
	private double joystickCorrection(double input, double deadband){
		//Run the deadband first then the squareing so the robot can still go full speed
		return squared(deadband(input, deadband));
	}

	/**
	 * By default drive with the joystick
	 */
    public void initDefaultCommand() {
        setDefaultCommand(new DriveJoystick());
    }
}

