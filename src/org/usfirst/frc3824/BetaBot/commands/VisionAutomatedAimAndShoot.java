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
import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc3824.BetaBot.Constants;
import org.usfirst.frc3824.BetaBot.Robot;

/**
 *
 */
public class VisionAutomatedAimAndShoot extends Command
{
	private Timer m_timer;
	private boolean m_shooterPositionOut = false;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public VisionAutomatedAimAndShoot()
	{
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.chassis);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

		// Initialize the timer
		m_timer = new Timer();
	}

	// Called just before this Command runs the first time
	protected void initialize()
	{
		// Initialize the encoder based turn PID with the present encoder value of zero
		Robot.chassis.configureEncoderPIDs(Constants.IMAGE_ANGLE_ENCODER_P,
		                                   Constants.IMAGE_ANGLE_ENCODER_I,
		                                   Constants.IMAGE_ANGLE_ENCODER_D,
		                                   Constants.IMAGE_ANGLE_MINIMUM_OUTPUT,
		                                   Constants.IMAGE_ANGLE_MAXIMUM_OUTPUT,
		                                   0, 0.0);

		// reset and start the timer
		m_timer.reset();
		m_timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute()
	{
		// If we haven't shot yet,
		if (!m_shooterPositionOut)
		{
			// Shoot if the robot is properly aligned
			if (robot_aligned() == true)
			{
				Robot.shooter.ShooterShootBallControl(true);
				m_shooterPositionOut = true;
				
				// Reset the timer for holding shooter position out
				m_timer.reset();
			} 
			
			// Determine if a new image should be processed
			if (m_timer.get() > 1.0)  // 0.12)
			{
				// Adjust the robot angle and shooter height
				determine_shooter_height();
				determine_robot_turn_angle();
				
				// Reset the timer for the next image processing
				m_timer.reset();
			}
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished()
	{
		// We are done if the shooter is out and has been out for half a second
		return((m_shooterPositionOut == true) && (m_timer.get() > 0.5));
	}

	// Called once after isFinished returns true
	protected void end()
	{
		// Bring shooter piston back in
		Robot.shooter.ShooterShootBallControl(false);

		// Disable both gyro and the two encoder drive PIDs 
		Robot.chassis.disableAllPIDs();
		
		// Stop the timer
		m_timer.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted()
	{
		this.end();
	}

	/**
	 * Method to track the Y position of the target and set the shooter
	 * position angle to the target
	 */
	private void determine_shooter_height()
	{
		// Get present shooter angle
		double shooterAngle = Robot.shooter.GetShooterAngleSetPoint();

		// Ensure target has been detected
		if (Robot.targets.getTargetPixel_Y() > 0)
		{
			// Calculate desired shooter angle
			// Note: The image (0,0) pixel is top left corner
			// If pixelYOffset is positive, then target is too low
			int pixelYOffset = targetShooterPositionY() - Robot.targets.getTargetPixel_Y();
			SmartDashboard.putNumber("Auto Pixel Y Offset", pixelYOffset);
			
			// Assume the offset is positive
			int isPixelYOffsetPositive = 1;
			
			// Determine if the offset is negative
			if (pixelYOffset < 0)
			{
				// Make the offset positive and set the is positive variable to -1
				isPixelYOffsetPositive = -1;
				pixelYOffset *= -1;
			}
			
			// Adjust shooter angle based on distance from target
			if (pixelYOffset > Constants.IMAGE_LARGE_PIXEL_OFFSET_Y)
				shooterAngle += isPixelYOffsetPositive * Constants.IMAGE_LARGE_STEP_ANGLE_Y;

			else if (pixelYOffset > Constants.IMAGE_MEDIUM_PIXEL_OFFSET_Y)
				shooterAngle += isPixelYOffsetPositive * Constants.IMAGE_MEDIUM_STEP_ANGLE_Y;

			else if (pixelYOffset > Constants.IMAGE_SMALL_PIXEL_OFFSET_Y)
				shooterAngle += isPixelYOffsetPositive * Constants.IMAGE_SMALL_STEP_ANGLE_Y;

			SmartDashboard.putNumber("shooterAngle Setpoint", shooterAngle);
			
			// Update the shooter set point
			Robot.shooter.setShooterElevationSetpoint(shooterAngle);
		}
	}

	/**
	 * Method to track the X position of the target and set the desired encoder
	 * positions to turn the robot towards the target
	 */
	private void determine_robot_turn_angle()
	{
		// Get present encoder position
		// Note: Both encoders have the same set point except the right is the negative
		// of the left encoder 
		double encoderPosition = Robot.chassis.getEncoderSetpoint();

		// Ensure target has been detected
		if (Robot.targets.getTargetPixel_X() > 0)
		{
			// Calculate the delta pixels from the target
			int pixelXOffset = targetShooterPositionX() - Robot.targets.getTargetPixel_X();
			SmartDashboard.putNumber("Auto Pixel X Offset", pixelXOffset);
			
			// Assume the offset is positive
			int isPixelXOffsetPositive = 1;
			
			// Determine if the offset is negative
			if (pixelXOffset < 0)
			{
				// Make the offset positive and set the is positive variable to -1
				isPixelXOffsetPositive = -1;
				pixelXOffset *= -1;
			}

			// Adjust wheel encoders based on distance from target
			if (pixelXOffset > Constants.IMAGE_LARGE_PIXEL_OFFSET_X)
				encoderPosition += isPixelXOffsetPositive * Constants.IMAGE_LARGE_STEP_ANGLE_X;

			else if (pixelXOffset > Constants.IMAGE_MEDIUM_PIXEL_OFFSET_X)
				encoderPosition += isPixelXOffsetPositive * Constants.IMAGE_MEDIUM_STEP_ANGLE_X;

			else if (pixelXOffset > Constants.IMAGE_SMALL_PIXEL_OFFSET_X)
				encoderPosition += isPixelXOffsetPositive * Constants.IMAGE_SMALL_STEP_ANGLE_X;

			SmartDashboard.putNumber("Auto X Encoder", encoderPosition);

			// Update the chassis encoder set points
			Robot.chassis.setEncoderPID_Setpoint(encoderPosition);
		}
	}

	/**
	 * Method to determine when the robot chassis and shooter are aligned to 
	 * the target
	 */
	private boolean robot_aligned()
	{
//		// Still aligning robot
//		if (targetShooterPositionX() - Robot.targets.getTargetPixel_X() < Constants.IMAGE_LARGE_PIXEL_OFFSET_X
//				&& targetShooterPositionY() - Robot.targets.getTargetPixel_Y() < Constants.IMAGE_LARGE_PIXEL_OFFSET_Y)
//		{
//			return true;
//		} else
//		{
//			return false;
//		}
		
		return false;
	}

	/**
	 * Method to return the desired target X position based on the distance
	 * and angle to the target
	 */
	int targetShooterPositionX()
	{
		return Constants.IMAGE_WIDTH / 2;
	}

	/**
	 * Method to return the desired targetY position based on the distance
	 * and angle to the target
	 */
	int targetShooterPositionY()
	{
		return Constants.IMAGE_HEIGHT / 2;
	}
}
