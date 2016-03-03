// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3824.BetaBot.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc3824.BetaBot.Constants;
import org.usfirst.frc3824.BetaBot.Robot;

/**
 *
 */
public class ChassisTurnAngle extends Command
{
	private Timer m_OnTargetTimer;
	private Timer m_WatchdogTimer;
	
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	private double m_TurnDegrees;
	private double m_DrivePower;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public ChassisTurnAngle(double TurnDegrees, double DrivePower)
	{
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		m_TurnDegrees = TurnDegrees;
		m_DrivePower  = DrivePower;

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.chassis);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        
        // Create the timer instance
        m_OnTargetTimer = new Timer(); 
        m_WatchdogTimer = new Timer();
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
//		System.out.println("Chassis turn angle initialize");
		
		// Set the target turn angle based on the present gyro value and the
		// desired turn degrees
		double desiredHeading = Robot.chassis.getCurrentHeading() + m_TurnDegrees;

		// Set the PID up for driving straight
//		Robot.chassis.configurePIDs(Preferences.getInstance().getDouble("Turn P", Constants.DRIVETRAIN_TURN_ANGLE_P), 
//		                    		Preferences.getInstance().getDouble("Turn I", Constants.DRIVETRAIN_TURN_ANGLE_I),
//		                    		Preferences.getInstance().getDouble("Turn D", Constants.DRIVETRAIN_TURN_ANGLE_D), 
//	                                desiredHeading, Constants.TURN_THRESHOLD, m_DrivePower);
	
		Robot.chassis.configureGyroPIDs(Constants.TURN_ANGLE_P, 
		                    		Constants.TURN_ANGLE_I,
		                    		Constants.TURN_ANGLE_D, 
	                                desiredHeading, Constants.TURN_THRESHOLD, m_DrivePower);

		// Reset and start the on target timer
		m_OnTargetTimer.reset();
		m_OnTargetTimer.start();
		
		// Reset and start the on watch dog timer
		m_WatchdogTimer.reset();
		m_WatchdogTimer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		// Ensure the command ends after the watchdog time even if not on target
		if (m_WatchdogTimer.get() > 1.5)
			return(true);
		
		// return PIDcontroller.OnTarget();
		if (Math.abs(Robot.chassis.getGyroHeadingSetpoint() - 
		             Robot.chassis.getGyroPID_Heading()) < Constants.TURN_THRESHOLD)
		{
			// Ensure hold position for time out time
			if (m_OnTargetTimer.get() > 0.1)
			{
				return (true);
			}
		}
		else
		{
			// Reset the timer since the move did not complete
			m_OnTargetTimer.reset();
		}
		
		// Not at proper angle
		return (false);
	}

	// Called once after isFinished returns true
	protected void end()
	{
//		System.out.println("Chassis turn angle end");
		
		// disable the PID controller
		Robot.chassis.disableAllPIDs();
		Robot.chassis.stop();
		m_OnTargetTimer.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		this.end();
	}
}
