package org.usfirst.frc.team1523.robot.subsystems;

import org.usfirst.frc.team1523.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Ball extends Subsystem {

	private DoubleSolenoid piston;
	
	private CANTalon pickup;
	
	private boolean state;
	
	/**
	 * Class that handles the ball pickup and release mechanisms
	 */
    public Ball(){
    	piston = new DoubleSolenoid(RobotMap.BALL_DROP_A, RobotMap.BALL_DROP_B);
    	pickup = new CANTalon(RobotMap.BALL_PICKUP_TALON);
    }
    
    /**
     * Toggles the state of the release piston
     */
    public void toggle(){
    	if(state){
    		piston.set(DoubleSolenoid.Value.kForward);
    		state=false;
    	}else{
    		piston.set(DoubleSolenoid.Value.kReverse);
    		state=true;
    	}
    }
    
    /**
     * Sets state of release piston
     * @param setState True to push piston, False to pull
     */
    public void set(boolean setState){
    	if(setState){	//True = Forward
    		piston.set(DoubleSolenoid.Value.kForward);
    		state=false;
    	}else{			//False = Backward
    		piston.set(DoubleSolenoid.Value.kReverse);
    		state=true;
    	}
    }
    
    /**
     * Set speed of intake motor
     * @param speed Speed -1.0 to 1.0
     */
    public void set(double speed){
    	pickup.set(speed);
    }
    
    /**
     * Stop motor
     */
    public void stop(){
    	pickup.set(0);;
    }
    
    /**
     * @return Current state of pistons
     */
    public boolean getState(){
    	return state;
    }
    
	protected void initDefaultCommand() {}
}

