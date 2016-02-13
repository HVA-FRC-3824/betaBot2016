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
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc3824.BetaBot.Robot;
import org.usfirst.frc3824.BetaBot.Constants;


/**
 *
 */
public class BoulderIntakeControl extends Command
{

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	private double m_IntakePosition;
	private boolean m_roll;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public BoulderIntakeControl(double IntakePosition, boolean roll)
	{

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		m_IntakePosition = IntakePosition;
		m_roll = roll;

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.boulderIntake);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		if (m_IntakePosition == -1) {
			// Rest Position
			m_IntakePosition = Preferences.getInstance().getDouble("Roller Rest", Constants.BOULDER_INTAKE_POSITION_REST);
		} else if (m_IntakePosition == -5) {
			// Far down
			m_IntakePosition = Preferences.getInstance().getDouble("Roller Ground", Constants.BOULDER_INTAKE_POSITION_DOWN);
		} else if (m_IntakePosition == -4) {
			//Intake Position
			m_IntakePosition = Preferences.getInstance().getDouble("Roller Intake", Constants.BOULDER_INTAKE_POSITION_INTAKE);			
		}
		SmartDashboard.putNumber("SETPOINT", m_IntakePosition);
		
		Robot.boulderIntake.getRightController().setSetpoint(m_IntakePosition);
		Robot.boulderIntake.getLeftController().setSetpoint(m_IntakePosition);

		// enable the PID
		Robot.boulderIntake.getRightController().enable();
		Robot.boulderIntake.getLeftController().enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		if (m_roll == true)
		{
			Robot.boulderIntake.setBoulderIntakeWheelSpeed(Constants.BOULDER_ROLLER_SPEED);
		} else {
			Robot.boulderIntake.setBoulderIntakeWheelSpeed(0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.boulderIntake.setBoulderIntakeWheelSpeed(0);
		// disable the PID
		Robot.boulderIntake.getRightController().disable();
		Robot.boulderIntake.getLeftController().disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		this.end();
	}
}
