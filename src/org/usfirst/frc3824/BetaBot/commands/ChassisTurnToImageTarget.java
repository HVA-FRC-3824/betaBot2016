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

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3824.BetaBot.Constants;
import org.usfirst.frc3824.BetaBot.Robot;
import org.usfirst.frc3824.BetaBot.subsystems.Chassis.AnglePIDOutput;

/**
 *
 */
public class ChassisTurnToImageTarget extends Command
{

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	private int m_onTargetCount;
    private Timer m_Timer;  
    private int m_state;
    private boolean m_finished;

	public ChassisTurnToImageTarget()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
		
		m_Timer = new Timer();
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		double heading = Robot.chassis.getCurrentHeading();
		double targetOffset = Robot.targets.getTargetOffsetFromCenterAngle();
		
		// Set the PID up for driving straight
		Robot.chassis.configurePIDs(Preferences.getInstance().getDouble("ImageTurn_P", Constants.DRIVETRAIN_DRIVE_STRAIGHT_P), 
									Preferences.getInstance().getDouble("ImageTurn_I", Constants.DRIVETRAIN_DRIVE_STRAIGHT_I), 
									Preferences.getInstance().getDouble("ImageTurn_D", Constants.DRIVETRAIN_DRIVE_STRAIGHT_D), 
									heading + targetOffset, 0);	

		SmartDashboard.putNumber("ImageTurn Angle SetPoint", heading + targetOffset);
		SmartDashboard.putNumber("ImageTurn Target Offset", targetOffset);

		m_finished = false;
		m_state = 1;

		m_onTargetCount = 0;
		m_Timer.reset();
		m_Timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		double offsetAngle = Robot.targets.getTargetOffsetFromCenterAngle();
		double error = Math.abs(Robot.chassis.getAngleGyroController().getError());
		
		if(m_state==1 && (error < 7.0))
		{
			if(m_Timer.get() > 1.0)
			{
				m_Timer.reset();
				Robot.chassis.getAngleGyroController().setSetpoint(Robot.chassis.getCurrentHeading() + offsetAngle);
				if(error < 2.5)
				{
					m_state = 4;
					m_finished = true;
				}
				else if(error < 4.5)
				{
					m_state = 3;
				}
				else
				{
					m_state++;
				}
			}
		}
		else if(m_state==2 && (error < 4.0))
		{
			if(m_Timer.get() > 1.0)
			{
				m_Timer.reset();
				Robot.chassis.getAngleGyroController().setSetpoint(Robot.chassis.getCurrentHeading() + offsetAngle);
				if(error < 2.5)
				{
					m_state = 4;
					m_finished = true;
				}
				else
				{
					m_state = 3;
				}
			}
		}
		else if(m_state==3 && (error < 1.5))
		{
			if(m_Timer.get() > 1.0)
			{
				m_state = 4;
				m_finished = true;
			}
		}
		else
		{
			m_Timer.reset();
		}
				
		SmartDashboard.putNumber("ImageTurn Current Angle", Robot.chassis.getCurrentHeading());
		SmartDashboard.putNumber("ImageTurn PID Error", Robot.chassis.getAngleGyroController().getError());
		SmartDashboard.putNumber("ImageTurn OnTarget Count", m_onTargetCount);
		SmartDashboard.putNumber("ImageTurn OffsetFromCenterAngle", offsetAngle);
		SmartDashboard.putNumber("ImageTurn State", m_state);
		SmartDashboard.putNumber("ImageTurn Timer", m_Timer.get());

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return m_finished; 
	}

	// Called once after isFinished returns true
	protected void end()
	{
		// disable the PID and stop the robot
		Robot.chassis.getAngleGyroController().reset();
		Robot.chassis.getAngleGyroController().disable();
		Robot.chassis.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		// call the end method
		this.end();
	}
}
