package org.usfirst.frc.team1523.robot;

public class RobotMap {
	//--------------Talons-----------------------
	public static final int DRIVE_TALON_FRONT_R = 10;
	public static final int DRIVE_TALON_FRONT_L = 11;
	public static final int DRIVE_TALON_REAR_R = 12;
	public static final int DRIVE_TALON_REAR_L = 13;
	
	public static final int ROPE_TALON = 20;
	
	public static final int BALL_PICKUP_TALON = 30;
	
	//---------------Pistons---------------------
	public static final int BALL_DROP_A = 0;
	public static final int BALL_DROP_B = 1;
	
	public static final int GEAR_PUSH_LOWER_A = 2;
	public static final int GEAR_PUSH_LOWER_B = 3;
	public static final int GEAR_PUSH_UPPER_A = 4;
	public static final int GEAR_PUSH_UPPER_B = 5;
	
	//---------------DIO--------------------------
	public static final int PRESSURE_PLATE_INPUT = 1;
	
	//--------------Buttons---------------------------
	public static final int BALL_OUTPUT_OUT_BUTTON = 2;
	public static final int BALL_OUTPUT_IN_BUTTON = 3;
	public static final int BALL_PICKUP_HOLD_BUTTON = 2;
	public static final int GEAR_UP_BUTTON = 1;
	public static final int GEAR_DOWN_BUTTON = 4;
	public static final int ROPE_HOLD_BUTTON = 1;
	public static final int GEAR_OUT_BUTTON = 5;
	public static final int GEAR_IN_BUTTON = 6;
	
	//------------Constants--------------------------
	public static final double DISTANCE_PER_PULSE = (Math.PI*6)/(360*4);
}
