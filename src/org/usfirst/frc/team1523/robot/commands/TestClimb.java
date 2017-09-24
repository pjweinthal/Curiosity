package org.usfirst.frc.team1523.robot.commands;

import org.usfirst.frc.team1523.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *DO NOT RUN UNLESS BRAKE IS RELEASED
 */
public class TestClimb extends Command {

    public TestClimb() {
    	setTimeout(0.1);
    	requires(Robot.rope);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Run backwards at half speed
    	Robot.rope.run(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rope.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
