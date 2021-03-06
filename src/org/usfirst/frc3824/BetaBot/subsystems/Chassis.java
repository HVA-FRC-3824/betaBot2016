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
import org.usfirst.frc3824.BetaBot.utilities.HVAGyro;
import org.usfirst.frc3824.BetaBot.utilities.Lidar;
import org.usfirst.frc3824.BetaBot.Constants;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Robot Chassis subsystem class
 */
public class Chassis extends Subsystem
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController rightMotorA = RobotMap.chassisRightMotorA;
    private final SpeedController rightMotorB = RobotMap.chassisRightMotorB;
    private final SpeedController leftMotorA = RobotMap.chassisLeftMotorA;
    private final SpeedController leftMotorB = RobotMap.chassisLeftMotorB;
    private final RobotDrive wCDrive4 = RobotMap.chassisWCDrive4;
    private final Compressor compressor = RobotMap.chassisCompressor;
    private final Solenoid transmission = RobotMap.chassisTransmission;
    private final Encoder encoderRight = RobotMap.chassisEncoderRight;
    private final Encoder encoderLeft = RobotMap.chassisEncoderLeft;
    private final AnalogInput ultrasonicSensorRight = RobotMap.chassisUltrasonicSensorRight;
    private final AnalogInput ultrasonicSensorLeft = RobotMap.chassisUltrasonicSensorLeft;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	private final HVAGyro gyro = RobotMap.chassisGyro;
	private final Lidar lidar = RobotMap.chassisLidar;

	// Parameters used for drive while running under PID Control. The values
	// not set by the controller constructor can be set by a command directly
	private double m_magnitude;

	// Instantiate the PID controller for driving in the specified direction
	private PIDController angleGyroPID = new PIDController(Constants.DRIVETRAIN_DRIVE_STRAIGHT_P,
	                                                       Constants.DRIVETRAIN_DRIVE_STRAIGHT_I, 
	                                                       Constants.DRIVETRAIN_DRIVE_STRAIGHT_D, 
	                                                       gyro, new AnglePIDOutput());

	private PIDController angleEncoderPID_Right = new PIDController(Constants.IMAGE_ANGLE_ENCODER_P,
                                                                    Constants.IMAGE_ANGLE_ENCODER_I, 
                                                                    Constants.IMAGE_ANGLE_ENCODER_D, 
                                                                    encoderRight, 
			new EncoderPID_OutputRight());

	private PIDController angleEncoderPID_Left = new PIDController(Constants.IMAGE_ANGLE_ENCODER_P,
	                                                               Constants.IMAGE_ANGLE_ENCODER_I, 
	                                                               Constants.IMAGE_ANGLE_ENCODER_D, 
	                                                               encoderLeft,
	                                                               new EncoderPID_OutputLeft());

	// Gavin Was Here

	/**
	 * Method to set the default command for the Chassis
	 */
	public void initDefaultCommand()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new TeleopDrive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	}

	/**
	 * Method to control the drive through the specified joystick
	 */
	public void driveWithJoystick(Joystick stick)
	{
		// Drive with arcade with the Y axis for forward/backward and
		// steer with twist
		// Note: Set the sensitivity to true to decrease joystick at small input
		double twist = stick.getTwist();

		// Cube twist to decrease sensitivity
		twist = twist * twist * twist;

		// Create a dead zone for forward/backward
		double moveValue = stick.getY();
		if (moveValue < 0)
			moveValue = -1.0 * (moveValue * moveValue);
		else
			moveValue = moveValue * moveValue;

		// Drive with arcade control
		wCDrive4.arcadeDrive(moveValue, twist, false);
	}

	/**
	 * Method to control the drive in the forward/back through the specified joystick
	 * Sets the magn
	 */
	public void updateMagnitudeWithJoystick(Joystick stick)
	{
		// Drive with arcade with the Y axis for forward/backward and
		// steer with twist

		double moveValue = stick.getY();
		if (moveValue < 0)
			moveValue = moveValue * moveValue;
		else
			moveValue = -1.0 * (moveValue * moveValue);

		// Set magnitude
		setMagnitude(moveValue);
	}

	/**
	 * Method to update gyro setpoint from ultrasonic class for automatic
	 * defense crossing Call this command periodically from a command that wants
	 * to automatically drive through defenses
	 */
	public void updateGyroSetpointFromUltrasonic()
	{
		// check to see if ultrasonic sensors are in a useful range
		if (checkUltrasonicInUsefulRange())
		{
			// if in useful range:
			double degreesToNudge = Constants.AUTO_DEFENSE_DRIVE_NUDGE;
			if (getLeftUltrasonicDistance() < getRightUltrasonicDistance())
			{
				// Left Distance is less than Right
				// Robot is too far left, turn right
				setGyroPID_Heading(getCurrentHeading() + degreesToNudge);
			} else
			{
				// Left Distance is greater than Right
				// Robot is too far right, turn left
				setGyroPID_Heading(getCurrentHeading() - degreesToNudge);
			}
		}
	}

	/**
	 * returns true if Ultrasonic Sensors are within Constants.AUTO_DEFENSE_DRIVE_MAX_RANGE 
	 * @return
	 */
	public boolean checkUltrasonicInUsefulRange()
	{
		return getLeftUltrasonicDistance() < Constants.AUTO_DEFENSE_DRIVE_CLOSE_RANGE
				&& getRightUltrasonicDistance() < Constants.AUTO_DEFENSE_DRIVE_CLOSE_RANGE;
	}

	/**
	 * Method to configure the gyro based turn/drive straight PID controller
	 */
	public void configureGyroPIDs(double P, double I, double D, double minimumOutput, double maximumOutput,
			double desiredHeading, double tolerance, double power)
	{
		// update the drive power
		m_magnitude = power;

		// Reset the PID controller
		angleGyroPID.disable();
		angleGyroPID.reset();

		// Reset Encoders for when driving a given distance
		resetEncoders();

		// Set the PID gains
		angleGyroPID.setPID(P, I, D);

		// The gyro angle uses input values from 0 to 360
		angleGyroPID.setInputRange(0.0, 360.0);

		// Consider 0 and 360 to be the same point
		angleGyroPID.setContinuous(true);

		// Limit the output power when turning
		angleGyroPID.setOutputRange(minimumOutput, maximumOutput);

		// Set the PID tolerance
		angleGyroPID.setAbsoluteTolerance(tolerance);

		// Set the PID desired heading
		angleGyroPID.setSetpoint(getRelativeAngle(desiredHeading));

		// enable the PID
		angleGyroPID.enable();
	}

	/**
	 * Method to set the PID heading
	 */
	public void setGyroPID_Heading(double desiredHeading)
	{
		// Set the PID desired heading
		angleGyroPID.setSetpoint(getRelativeAngle(desiredHeading));
	}

	/**
	 * Method to get the PID target value (heading)
	 */
	public double getGyroHeadingSetpoint()
	{
		return angleGyroPID.getSetpoint();
	}

	/**
	 * Method to get the PID target value (heading)
	 */
	public double getGyroPID_Heading()
	{
		return gyro.pidGet();
	}

	/**
	 * Method to get the PID error
	 */
	public double getGyroPID_Error()
	{
		return angleGyroPID.getError();
	}

	/**
	 * Method to disable the angle PID controller
	 */
	public void disableAllPIDs()
	{
		// disable the PID
		angleGyroPID.disable();
		angleEncoderPID_Left.disable();
		angleEncoderPID_Right.disable();
	}

	/**
	 * Method to configure the encoder based turn PID controller
	 */
	public void configureEncoderPIDs(double P, double I, double D, double minimumOutput, double maximumOutput,
			double desiredEncoderValue, double tolerance)
	{
		// Reset the PID controller
		angleEncoderPID_Left.disable();
		angleEncoderPID_Right.disable();
		angleEncoderPID_Left.reset();
		angleEncoderPID_Right.reset();

		// Reset Encoder values
		resetEncoders();

		// Set the PID gains
		angleEncoderPID_Left.setPID(P, I, D);
		angleEncoderPID_Right.setPID(P, I, D);

		// Set the encoder input value range
		angleEncoderPID_Left.setInputRange(Constants.IMAGE_ANGLE_MINIMUM_INPUT, Constants.IMAGE_ANGLE_MAXIMUM_INPUT);
		angleEncoderPID_Right.setInputRange(Constants.IMAGE_ANGLE_MINIMUM_INPUT, Constants.IMAGE_ANGLE_MAXIMUM_INPUT);

		// Set the encoder output range
		angleEncoderPID_Left.setOutputRange(Constants.IMAGE_ANGLE_MINIMUM_OUTPUT, Constants.IMAGE_ANGLE_MAXIMUM_OUTPUT);
		angleEncoderPID_Right.setOutputRange(Constants.IMAGE_ANGLE_MINIMUM_OUTPUT, Constants.IMAGE_ANGLE_MAXIMUM_OUTPUT);

		// Set the PID tolerance
		angleEncoderPID_Left.setAbsoluteTolerance(tolerance);
		angleEncoderPID_Right.setAbsoluteTolerance(tolerance);

		// Set the PID desired set point
		angleEncoderPID_Left.setSetpoint(-desiredEncoderValue);
		angleEncoderPID_Right.setSetpoint(desiredEncoderValue);

		// enable the PID
		angleEncoderPID_Left.enable();
		angleEncoderPID_Right.enable();
	}

	/**
	 * Method to set the PID set point
	 */
	public void setEncoderPID_Setpoint(double desiredEncoderValue)
	{
		// Set the PID desired set point
		angleEncoderPID_Left.setSetpoint(-desiredEncoderValue);
		angleEncoderPID_Right.setSetpoint(desiredEncoderValue);
	}

	/**
	 * Method to get the PID target value (heading)
	 */
	public double getEncoderSetpoint()
	{
		// Return the Left set point
		// Note: The Right set point should just be the negative of the Left
		return angleEncoderPID_Left.getSetpoint();
	}

	/**
	 * Method to get the PID error
	 */
	public double getEncoderPID_Error()
	{
		// Return the Left PID error
		return angleEncoderPID_Left.getError();
	}

	/**
	 * Method to stop the chassis drive motors
	 */
	public void stop()
	{
		// Stop all motors
		wCDrive4.arcadeDrive(0, 0);

		// Disable PID Controller
		this.disableAllPIDs();
	}

	/**
	 * Method to shift the drive train
	 */
	public void shiftGear(boolean gearHigh)
	{
		// Control the gear shift piston
		transmission.set(gearHigh);
	}

	/**
	 * Method to get larger of the encoder distances
	 */
	public double getDistance()
	{
		// Return the maximum encoder distance in case the other is not working
		return (Math.max(encoderLeft.getDistance(), encoderRight.getDistance()));
	}

	/**
	 * Method to reset both encoders
	 */
	public void resetEncoders()
	{
		encoderLeft.reset();
		encoderRight.reset();
	}

	/**
	 * Method to return the present gyro angle
	 */
	public double getCurrentHeading()
	{
		// Return the relative gyro angle
		return (getRelativeAngle(gyro.getAngle()));
	}

	/**
	 * Method to return a relative gyro angle (between 0 and 360)
	 */
	private double getRelativeAngle(double angle)
	{
		// Adjust the angle if negative
		while (angle < 0.0)
			angle += 360.0;

		// Adjust the angle if greater than 360
		while (angle >= 360.0)
			angle -= 360.0;

		// Return the angle between 0 and 360
		return angle;
	}

	/**
	 * Method to reset the chassis gyro
	 *
	 * Note: Should only be called once just before the autonomous command starts
	 */
	public void resetGyro()
	{
		// Reset the gyro (angle goes to zero)
		gyro.reset();
	}

	/**
	 * Method to set the desired chassis speed (magnitude) for PID controlled
	 * moves. Only to be used while controlled by PID controller
	 */
	public void setMagnitude(double magnitude)
	{
		m_magnitude = magnitude;
	}

	/**
	 * Set the chassis drive motor
	 */
	public void setWheelOutput(double rightWheel, double leftWheel)
	{
		// Set the right motors for forward direction
		rightMotorA.set(rightWheel);
		rightMotorB.set(rightWheel);

		// Set the Left motors for forward direction
		// Note: The Left motors are opposite the Right motors
		leftMotorA.set(-leftWheel);
		leftMotorB.set(-leftWheel);
	}

	/**
	 * Get the Right encoder value
	 */
	public int getRightEncoderValue()
	{
		return encoderRight.get();
	}

	/**
	 * Get the Left encoder value
	 */
	public int getLeftEncoderValue()
	{
		return encoderLeft.get();
	}

	/**
	 * Return the right encoder distance
	 */
	public double getRightDistance()
	{
		return (encoderRight.getDistance());
	}

	/**
	 * Return the left encoder distance
	 */
	public double getLeftDistance()
	{
		return (encoderLeft.getDistance());
	}

	/**
	 * Class declaration for the PIDOutput
	 */
	public class AnglePIDOutput implements PIDOutput
	{
		/**
		 * Virtual function to receive the PID output and set the drive direction 
		 */
		public void pidWrite(double PIDoutput)
		{
			// Drive the robot given the speed and direction
			// Note: The Arcade drive expects a joystick which is negative forward)
			wCDrive4.arcadeDrive(-m_magnitude, PIDoutput);
		}
	}

	/**
	 * Class declaration for the PIDOutput
	 */
	public class EncoderPID_OutputRight implements PIDOutput
	{
		/**
		 * Virtual function to receive the PID output and set the drive direction 
		 */
		public void pidWrite(double PIDoutput)
		{
//			SmartDashboard.putNumber("SpeedControllerPIDOutputRight: ", PIDoutput);

			rightMotorA.set(PIDoutput);
			rightMotorB.set(PIDoutput);
		}
	}

	/**
	 * Class declaration for the PIDOutput
	 */
	public class EncoderPID_OutputLeft implements PIDOutput
	{
		/**
		 * Virtual function to receive the PID output and set the drive direction 
		 */
		public void pidWrite(double PIDoutput)
		{
//			SmartDashboard.putNumber("SpeedControllerPIDOutputLeft: ", -PIDoutput);

			leftMotorA.set(-PIDoutput);
			leftMotorB.set(-PIDoutput);
		}
	}

	
	// -------------------------------------------
	// Lidar commands for Chassis
	// -------------------------------------------
	public double getLidarDistanceCentimeters()
	{
		return lidar.getDistanceCentimeters();
	}

	/**
	 * Method to return the distance from the right ultrasonic sensor
	 */
	public double getRightUltrasonicDistance()
	{
		double distance;

		// Compute the distance based on the analog input
		distance = 108.43 * ultrasonicSensorRight.getVoltage();

		// Return the distance
		return (distance);
	}

	/**
	 * Method to return the distance from the left ultrasonic sensor
	 */
	public double getLeftUltrasonicDistance()
	{
		double distance;

		// Compute the distance based on the analog input
		distance = 108.43 * ultrasonicSensorLeft.getVoltage();

		// Return the distance
		return (distance);
	}
}
