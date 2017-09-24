package org.usfirst.frc.team1523.robot.commands;

import org.usfirst.frc.team1523.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Vision extends Command {

	private final int DEAD_ZONE=3;
	private double THROW_DISTANCE=58;
	private double distance;
	private double x;
//	private double y1;
	//private double y2;

	public Vision() {
		requires(Robot.drive);
		requires(Robot.gear);
	}

	protected void execute() {
		synchronized(Robot.imgLock){
			distance = Robot.distance;
			x = Robot.x;
		}
		if(x<0) Robot.drive.stop();
		else if(x>80+DEAD_ZONE){									//If Robot is too far to the right
			Robot.drive.drive(0.40, 0, 0);							//Move to the left
		}else if(x<80-DEAD_ZONE){									//If Robot is too far to the left
			Robot.drive.drive(-0.40, 0, 0);							//Move to the right
		}else if(distance<THROW_DISTANCE){
			Robot.drive.drive(0, -0.2, 0);							//If the distance to the target is below the threshold drive forward slowly
		}else{
			Robot.drive.stop();										//If the distance to the target is above the threshold stop and move to the next command
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return distance>THROW_DISTANCE;
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
