package org.usfirst.frc.team1523.robot.commands;

import org.usfirst.frc.team1523.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTurn extends Command {

	private double angle=0;
	
	private boolean finished = false;
	
    public AutoTurn(double deltaAngle) {
        requires(Robot.drive);
        this.angle=deltaAngle;
        this.finished=false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//The gyro is mounted upside down so the values for left and right are inverted
    	//Turn Left
    	if(angle>0){
    		//If the gyro is not at the correct angle yet
    		if(Robot.gyro.getAngle()<angle){
    			//Turn left
    			Robot.drive.drive(0, 0, 0.3);
    		}else{
    			//Stop
    			this.finished = true;
    		}
    	//Turn Right
    	}else{
    		//If the gyro is not at the correct angle yet
    		if(Robot.gyro.getAngle()>angle){
    			//Turn right
    			Robot.drive.drive(0, 0, -0.3);
    		}else{
    			//Stop
    			this.finished=true;
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drive.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
