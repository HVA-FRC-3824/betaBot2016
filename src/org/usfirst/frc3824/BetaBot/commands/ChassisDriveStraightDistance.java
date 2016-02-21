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

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3824.BetaBot.Constants;
import org.usfirst.frc3824.BetaBot.Robot;

/**
 *
 */
public class ChassisDriveStraightDistance extends Command
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	private double m_DriveDistance;
	private double m_DrivePower;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public ChassisDriveStraightDistance(double DriveDistance, double DrivePower)
	{

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		m_DriveDistance = DriveDistance;
		m_DrivePower = DrivePower;

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.chassis);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		// Set the PID up for driving straight
		Robot.chassis.configurePIDs(Constants.DRIVETRAIN_DRIVE_STRAIGHT_P, 
		                                Constants.DRIVETRAIN_DRIVE_STRAIGHT_I, 
		                                Constants.DRIVETRAIN_DRIVE_STRAIGHT_D, 
		                                Robot.chassis.getCurrentHeading(), m_DrivePower);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		SmartDashboard.putNumber("Gyro", Robot.chassis.getCurrentHeading());
		SmartDashboard.putNumber("Distance", Robot.chassis.getDistance());
		
		// Slow down when reaching the desired position
		if (Math.abs(m_DriveDistance - Robot.chassis.getDistance()) < 50.0)
			Robot.chassis.setMagnitude(0.5);			
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return Robot.chassis.getDistance() > m_DriveDistance;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		// disable the PID and stop the robot
		Robot.chassis.getAngleGyroController().disable();
		Robot.chassis.getRobotDrive().arcadeDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		// call the end method
		this.end();
	}
}
