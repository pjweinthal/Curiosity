package org.usfirst.frc.team1523.robot.commands;

import org.usfirst.frc.team1523.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PickupBall extends Command {

    public PickupBall() {
        requires(Robot.ball);
        //Always check for button hold
        setTimeout(0.1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Turn on motor
    	Robot.ball.set(1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.ball.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
