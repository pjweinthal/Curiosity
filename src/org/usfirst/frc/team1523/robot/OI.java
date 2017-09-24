package org.usfirst.frc.team1523.robot;

import org.usfirst.frc.team1523.robot.commands.ClimbRope;
import org.usfirst.frc.team1523.robot.commands.PickupBall;
import org.usfirst.frc.team1523.robot.commands.ResetSensors;
import org.usfirst.frc.team1523.robot.commands.SetBallRelease;
import org.usfirst.frc.team1523.robot.commands.SetLowerGear;
import org.usfirst.frc.team1523.robot.commands.SetUpperGear;
import org.usfirst.frc.team1523.robot.commands.TestClimb;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick stick0;
	Joystick stick1;
	
	JoystickButton ballOutputOut;
	JoystickButton ballOutputIn;
	JoystickButton holdBallPickup;
	JoystickButton gearUp;
	JoystickButton gearDown;
	JoystickButton holdRope;
	JoystickButton downRope;
	JoystickButton gearOut;
	JoystickButton gearIn;
	
	JoystickButton resetEncoder;
	
	public OI(){
		//Create Joystick Objects
		stick0 = new Joystick(0);
		stick1 = new Joystick(1);
		
		//Assign each button object to its stick and button number in RobotMap
		ballOutputOut = new JoystickButton(stick1, RobotMap.BALL_OUTPUT_OUT_BUTTON);
		ballOutputIn = new JoystickButton(stick1, RobotMap.BALL_OUTPUT_IN_BUTTON);
		holdBallPickup = new JoystickButton(stick0, RobotMap.BALL_PICKUP_HOLD_BUTTON);
		gearUp = new JoystickButton(stick1, RobotMap.GEAR_UP_BUTTON);
		gearDown = new JoystickButton(stick1, RobotMap.GEAR_DOWN_BUTTON);
		holdRope = new JoystickButton(stick0, RobotMap.ROPE_HOLD_BUTTON);
		gearOut = new JoystickButton(stick1, RobotMap.GEAR_OUT_BUTTON);
		gearIn = new JoystickButton(stick1, RobotMap.GEAR_IN_BUTTON);
		
		//Give Each Button A Command
		holdRope.whileHeld(new ClimbRope());
		holdBallPickup.whileHeld(new PickupBall());
		ballOutputOut.whenPressed(new SetBallRelease(false));
		ballOutputIn.whenPressed(new SetBallRelease(true));
		gearUp.whenPressed(new SetLowerGear(true));
		gearDown.whenPressed(new SetLowerGear(false));
		gearOut.whenPressed(new SetUpperGear(true));
		gearIn.whenPressed(new SetUpperGear(false));
	}
	
	/**
	 * @return Joystick in port 0
	 */
	public Joystick getStick0(){
		return stick0;
	}
	
	/**
	 * @return Joystick in port 1
	 */
	public Joystick getStick1(){
		return stick1;
	}
}
