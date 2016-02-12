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

import org.usfirst.frc3824.BetaBot.Constants;
import org.usfirst.frc3824.BetaBot.Robot;

/**
 *
 */
public class ShooterPositionControl extends Command
{

	private double m_ActualShooterSetPoint;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	private double m_ShooterSetPoint;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public ShooterPositionControl(double ShooterSetPoint)
	{

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		m_ShooterSetPoint = ShooterSetPoint;

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooter);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		// Get the present shooter elevator set point and wheel speed
		m_ActualShooterSetPoint = Robot.shooter.GetShooterAngleSetPoint();
		double wheelSpeed = 0.0;

		// Determine if the command was called from a button
		if (m_ShooterSetPoint == -30.0)
		{ // Jog up button
			m_ActualShooterSetPoint += 5.0;
			wheelSpeed = Robot.shooter.GetShooterWheelSpeed(); // Keep current Wheel Speed when Jogging
		} 
		else if (m_ShooterSetPoint == -50.0)
		{ // Jog down button
			m_ActualShooterSetPoint -= 5.0;
			wheelSpeed = Robot.shooter.GetShooterWheelSpeed(); // Keep current Wheel Speed when Jogging
		} 
		else if (m_ShooterSetPoint == -70.0)
		{ // Set position buttons
			m_ActualShooterSetPoint = Preferences.getInstance().getDouble("Button 07",
					Constants.SHOOTER_ELEVATION_INTAKE);
			// Ball in position
			wheelSpeed = -1.0;
		} 
		else if (m_ShooterSetPoint == -80.0)
		{
			m_ActualShooterSetPoint = Preferences.getInstance().getDouble("Button 08",
					Constants.SHOOTER_ELEVATION_REST);
		} 
		else if (m_ShooterSetPoint == -90.0)
		{
			m_ActualShooterSetPoint = Preferences.getInstance().getDouble("Button 09",
					Constants.SHOOTER_ELEVATION_SHOOT_POSITION1);
		} 
		else if (m_ShooterSetPoint == -100.0)
		{
			m_ActualShooterSetPoint = Preferences.getInstance().getDouble("Button 10",
					Constants.SHOOTER_ELEVATION_SHOOT_POSITION2);
		} 
		else if (m_ShooterSetPoint == -110.0)
		{
			m_ActualShooterSetPoint = Preferences.getInstance().getDouble("Button 11",
					Constants.SHOOTER_ELEVATION_SHOOT_POSITION3);
		} 
		else if (m_ShooterSetPoint == -120.0)
		{
			m_ActualShooterSetPoint = Preferences.getInstance().getDouble("Button 12",
					Constants.SHOOTER_ELEVATION_SHOOT_POSITION4);
		} 
		else
		{
			m_ActualShooterSetPoint = m_ShooterSetPoint;
		}

		// Set the shooter PID set point
		Robot.shooter.setShooterElevationSetpoint(m_ActualShooterSetPoint);

		// Set Wheel Speed
		Robot.shooter.ShooterWheelControl(wheelSpeed);
		
		// Enable the shooter elevation PID
		Robot.shooter.setShooterElevationEnabled(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		// Determine if the shooter wheel is enabled
		if (Robot.shooter.IsShooterWheelEnabled() == true)
		{
			// Continue to put out set wheel speed
			// Set the shooter wheel curve based on the Operator Joystick
			Robot.shooter.ShooterWheelControl(Robot.shooter.GetShooterWheelSpeed(),
					Robot.oi.controllerJoystick.getTwist() * Constants.SHOOTER_WHEEL_TELEOP_CURVE_MULTIPLIER);

		} else
		{
			// Shooter is disabled so turn off the motors
			Robot.shooter.ShooterWheelControl(0.0);
		}

		// SmartDashboard.putNumber("Control Y:
		// ",Robot.oi.controllerJoystick.getY());
		SmartDashboard.putNumber("Angle Position", Robot.shooter.GetShooterElevatorAngle());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		return false;
	}

	// Called once after isFinished returns true
	protected void end()
	{
		Robot.shooter.setShooterElevationEnabled(false);
		Robot.shooter.ShooterWheelControl(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		end();
	}
}
