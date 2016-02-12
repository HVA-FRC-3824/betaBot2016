// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3824.BetaBot.subsystems;

import org.usfirst.frc3824.BetaBot.RobotMap;
import org.usfirst.frc3824.BetaBot.commands.*;
import org.usfirst.frc3824.BetaBot.Constants;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem
{
	private boolean m_enabled = true;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final AnalogPotentiometer analogPotentiometer = RobotMap.shooterAnalogPotentiometer;
    private final SpeedController speedController = RobotMap.shooterSpeedController;
    private final CANTalon wheelRightA = RobotMap.shooterWheelRightA;
    private final CANTalon wheelRightB = RobotMap.shooterWheelRightB;
    private final CANTalon wheelLeftA = RobotMap.shooterWheelLeftA;
    private final CANTalon wheelLeftB = RobotMap.shooterWheelLeftB;
    private final Solenoid ballShooterPiston = RobotMap.shooterBallShooterPiston;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    private PIDController shooterAngleController;
    
	double m_PresentShooterAngle;
	double m_MinShooterHeight;
	double m_MaxShooterHeight;
    
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		shooterAngleController = new PIDController(10.0, 0, 0, analogPotentiometer, speedController);
		shooterAngleController.setAbsoluteTolerance(0.02);
		shooterAngleController.setContinuous(false);
		LiveWindow.addActuator("Shooter", "Angle PID", shooterAngleController);
		
		m_MinShooterHeight = Preferences.getInstance().getDouble("Min Shooter Height", Constants.SHOOTER_ELEVATION_SETPOINT_MIN);
		m_MaxShooterHeight = Preferences.getInstance().getDouble("Max Shooter Height", Constants.SHOOTER_ELEVATION_SETPOINT_MAX);
	}

	/**
	 * Method to enable or disable the shooter wheels. Note: The actual control
	 * of the shooter wheels is in the default ShooterControl command.
	 */
	public void ShooterWheelEnableDisable(boolean enable)
	{
		// Remember the requested state of the shooter wheels
		m_enabled = enable;
		SmartDashboard.putBoolean("Shooter Enabled", enable);
	}

	/**
	 * Method to determine if the shooter wheels are enabled
	 */
	public boolean IsShooterWheelEnabled()
	{
		// Return the state of the shooter wheels
		return (m_enabled);
	}

	/**
	 * Method to enable the shooter with the specified speed. The method can
	 * also disable the shooter with a speed of zero.
	 */
	public void ShooterWheelControl(double speed)
	{
		ShooterWheelControl(speed, 0);
	}

	/**
	 * Method to enable the shooter with the specified speed and curve Curve is
	 * a percent of the speed parameter to be added to the right and subtracted
	 * from the left Positve curves right, Negative curves left
	 */
	public void ShooterWheelControl(double speed, double curvePercent)
	{
		// Set the shooter wheel motor speeds
		// Note: The multiplier is to ensure maximum speed is reached
		// The multiplier also reduces the minimum speed

		double curvePart = speed * curvePercent;

		if (speed < 0)
		{
			wheelRightA.set(speed * Constants.SHOOTER_WHEEL_MIN_MULTIPLIER + curvePart);
			wheelRightB.set(speed * Constants.SHOOTER_WHEEL_MIN_MULTIPLIER + curvePart);
			wheelLeftA.set(speed * Constants.SHOOTER_WHEEL_MIN_MULTIPLIER - curvePart);
			wheelLeftB.set(speed * Constants.SHOOTER_WHEEL_MIN_MULTIPLIER - curvePart);
		}
		else
		{
			wheelRightA.set(speed * Constants.SHOOTER_WHEEL_MAX_MULTIPLIER + curvePart);
			wheelRightB.set(speed * Constants.SHOOTER_WHEEL_MAX_MULTIPLIER - curvePart);
			wheelLeftA.set(speed * Constants.SHOOTER_WHEEL_MAX_MULTIPLIER + curvePart);
			wheelLeftB.set(speed * Constants.SHOOTER_WHEEL_MAX_MULTIPLIER - curvePart);
		}
	}

	/**
	 * Method to shoot the ball
	 */
	public void ShooterShootBallControl(boolean state)
	{
		// Control the gear shift piston
		ballShooterPiston.set(state);
	}
	
	/**
	 * Routine to enable or disable the shooter elevation PID
	 */
	public void setShooterElevationEnabled(boolean state)
	{
		// Determine if the shooter elevation PID should be enabled or disabled
		if (state == true)
		{
			shooterAngleController.enable();
		}
		else
		{
			shooterAngleController.disable();
		}
	}
	
	/**
	 * Routine to set the shooter elevation setpoint in degrees
	 */
	public void setShooterElevationSetpoint(double setpointDegrees)
	{
		// Ensure the set point is not below the specified lowest level
		if (setpointDegrees < m_MinShooterHeight)
		{
			setpointDegrees = m_MinShooterHeight;
		}
		
		// Ensure the set point is not above the specified highest level
		if (setpointDegrees > m_MaxShooterHeight)
		{
			setpointDegrees = m_MaxShooterHeight;
		}
		
		// Remember the setpoint
		m_PresentShooterAngle = setpointDegrees;
				
		// Convert degrees to Potentiometer value
		// Potentiometer Set Point = -0.00004(X^2) +0.011X+0.1956
		double setPoint = -0.00004* (setpointDegrees * setpointDegrees) +0.011 * setpointDegrees +0.1956;
		
		SmartDashboard.putNumber("Angle Setpoint", setpointDegrees);
		
		// Set the shooter elevation set point
		shooterAngleController.setSetpoint(setPoint);
	}

	/**
	 * 
	 */
	public double GetShooterElevatorAngle()
	{
		double angle;
		
		// Get the shooter elevator potentiometer position
		angle = analogPotentiometer.get();
		
		// Angle = 58.01 X^2 + 64.95 X - 15.5
		angle = 58.01 * (angle * angle) + (64.95 * angle) - 15.5;
		
		// Return the shooter elevator angle in degrees
		return(angle);
	}

	/**
	 * Method to get the present shooter angle set point
	 */
	public double GetShooterAngleSetPoint()
	{
	   return(m_PresentShooterAngle);
	}	

}
