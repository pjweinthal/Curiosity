package org.usfirst.frc.team1523.robot.commands;
import org.usfirst.frc.team1523.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {

	private double distance;
	private double speed;
	
	private boolean finished=false;
	
    public AutoDrive(double speed, double distance) {
    	requires(Robot.drive);
        this.distance=distance;
        this.speed=speed;
        this.finished=false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drive.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Drive Backwards
    	if(this.distance<0){
    		//If the encoders have not reached the right distance
    		if(Robot.drive.getLeftDistance()<-this.distance){
    			//Drive backwards
        		Robot.drive.drive(0, speed, 0);
        	}else{
        		//Stop
        		this.finished=true;
        	}
    	//Drive Forwards
    	}else{
    		//If the encoders have not reached the right distance
    		if(Robot.drive.getRightDistance()<this.distance){
    			//Drive forwards
        		Robot.drive.drive(0, -speed, 0);
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
    	Robot.drive.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
