package org.usfirst.frc.team1523.robot.commands;

import org.usfirst.frc.team1523.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetBallRelease extends Command {

	private boolean state;
	private boolean finished=false;
	
    public SetBallRelease(boolean state) {
        requires(Robot.ball);
        this.state=state;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Send to subsystem
    	Robot.ball.set(state);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
