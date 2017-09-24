package org.usfirst.frc.team1523.robot.subsystems;
import org.usfirst.frc.team1523.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gear extends Subsystem {

	private DoubleSolenoid upper;
	private DoubleSolenoid lower;

	private boolean stateUpper=true;
	private boolean stateLower=true;

	private DigitalInput pressurePlate;

	/**
	 * Handles the gear carrying and placement
	 */
	public Gear(){
		upper = new DoubleSolenoid(RobotMap.GEAR_PUSH_LOWER_A, RobotMap.GEAR_PUSH_LOWER_B);
		lower = new DoubleSolenoid(RobotMap.GEAR_PUSH_UPPER_A, RobotMap.GEAR_PUSH_UPPER_B);
		pressurePlate = new DigitalInput(RobotMap.PRESSURE_PLATE_INPUT);
	}

	/**
	 * Toggle upper piston
	 */
	public void toggleUpper(){
		if(stateUpper){
			upper.set(DoubleSolenoid.Value.kForward);
			stateUpper=false;
		}else{
			upper.set(DoubleSolenoid.Value.kReverse);
			stateUpper=true;
		}
	}

	/**
	 * Set state of upper piston
	 * @param setState new state of the piston
	 */
	public void setUpper(boolean setState){
		if(setState){
			upper.set(DoubleSolenoid.Value.kForward);
			stateUpper = true;
		}else{
			if(stateLower){
				upper.set(DoubleSolenoid.Value.kReverse);
				stateUpper=false;
			}
		}
	}

	/**
	 * @return Current state of upper piston
	 */
	public boolean getUpperState(){
		return stateUpper;
	}

	/**
	 * Toggle lower piston
	 */
	public void toggleLower(){
		if(stateUpper){
			lower.set(DoubleSolenoid.Value.kForward);
			stateLower=false;
		}else{
			upper.set(DoubleSolenoid.Value.kReverse);
			stateLower=true;
		}
	}

	/**
	 * Set state of lower piston
	 * @param setState New state of lower piston
	 */
	public void setLower(boolean setState){
		if(setState){
			lower.set(DoubleSolenoid.Value.kForward);
			stateLower = true;
		}else{
			if(stateUpper){
				lower.set(DoubleSolenoid.Value.kReverse);
				stateLower=false;
			}
		}
	}

	/**
	 * @return Current state of lower piston
	 */
	public boolean getLowerState(){
		return stateLower;
	}

	public boolean getPressurePlate(){
		return pressurePlate.get();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new MySpecialCommand());
	}
}

