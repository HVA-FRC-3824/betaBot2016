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

import org.usfirst.frc3824.BetaBot.Constants;
import org.usfirst.frc3824.BetaBot.Robot;
import org.usfirst.frc3824.BetaBot.RobotMap;
import org.usfirst.frc3824.BetaBot.commands.*;
import org.usfirst.frc3824.BetaBot.subsystems.Chassis.AnglePIDOutput;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BoulderIntake extends Subsystem
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController boulderIntakeRight = RobotMap.boulderIntakeBoulderIntakeRight;
    private final SpeedController boulderIntakeLeft = RobotMap.boulderIntakeBoulderIntakeLeft;
    private final SpeedController boulderWheel = RobotMap.boulderIntakeBoulderWheel;
    private final AnalogInput boudlerRightPosition = RobotMap.boulderIntakeBoudlerRightPosition;
    private final AnalogInput boulderLeftPosition = RobotMap.boulderIntakeBoulderLeftPosition;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	// Declare the PID Output class prototype
	// (See class at the end of the source file)
	private BoulderRightPIDOutput boulderRightOutput = new BoulderRightPIDOutput();
	private BoulderLeftPIDOutput  boulderLeftOutput  = new BoulderLeftPIDOutput();

	// Instantiate the PID controller for driving in the specified direction
	private PIDController boulderRightController;
	private PIDController boulderLeftController;	

	public BoulderIntake()
	{
		// Instantiate the PID controller for driving in the specified direction
		boulderRightController = new PIDController(Constants.BOULDER_INTAKE_P, 
		                                                                 Constants.BOULDER_INTAKE_I, 
		                                                                 Constants.BOULDER_INTAKE_D, 
		                                                                 boudlerRightPosition, boulderRightOutput);
		boulderRightController.setOutputRange(-Constants.BOULDER_INTAKE_POWER,Constants.BOULDER_INTAKE_POWER);
		boulderRightController.setInputRange(0.0, 5.0);
		boulderRightController.setAbsoluteTolerance(Constants.BOULDER_INTAKE_TOLERANCE);
		
		// Instantiate the PID controller for driving in the specified direction
		boulderLeftController = new PIDController(Constants.BOULDER_INTAKE_P, 
		                                                                Constants.BOULDER_INTAKE_I, 
		                                                                Constants.BOULDER_INTAKE_D, 
		                                                                boulderLeftPosition, boulderLeftOutput);	
		boulderLeftController.setOutputRange(-Constants.BOULDER_INTAKE_POWER, Constants.BOULDER_INTAKE_POWER);
		boulderLeftController.setInputRange(0.0, 5.0);
		boulderLeftController.setAbsoluteTolerance(Constants.BOULDER_INTAKE_TOLERANCE);
	}
	
	public void setBoulderIntakeWheelSpeed(double speed)
	{
		boulderWheel.set(speed);
	}
	
	public void initDefaultCommand()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new BoulderIntakeControl(-1, false));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	}
	
	/**
	 * Method to set the boulder intake pot PID set point
	 */
	public void SetPID_Position(double right_pot_voltage, double left_pot_voltage)
	{
		// Right Pot is inverted
		// Add an offset to the left potentiometer (positive is farther out)
		boulderRightController.setSetpoint(right_pot_voltage);
		boulderLeftController.setSetpoint(left_pot_voltage);
	}
	
	/**
	 * 
	 * Method to enable the boulder intake PID
	 */
	public void EnableBoulderIntakePID()
	{
		boulderRightController.enable();
		boulderLeftController.enable();
	}
	
	/**
	 * Method to Disable the boulder intake PID
	 */
	public void DisableBoulderIntakePID()
	{
		boulderRightController.disable();
		boulderLeftController.disable();
	}
	
	/**
	 * Class declaration for the PIDOutput
	 */
	public class BoulderRightPIDOutput implements PIDOutput
	{
		/**
		 * Virtual function to receive the PID output and set the drive direction 
		 */
		public void pidWrite(double PIDoutput)
		{				
			// Push values to the smart dashboard for debugging
			// Note: The magnitude should not change, but the direction is from the PID output
			SmartDashboard.putNumber("Right PIDoutput", PIDoutput);
			SmartDashboard.putNumber("Right Setpoint", boulderRightController.getSetpoint());
			SmartDashboard.putNumber("Right Intake Pot", boudlerRightPosition.getVoltage());

			// Drive the boulder intake motor
			boulderIntakeRight.set(PIDoutput);	
		}
	}
	
	/**
	 * Class declaration for the PIDOutput
	 */
	public class BoulderLeftPIDOutput implements PIDOutput
	{
		/**
		 * Virtual function to receive the PID output and set the drive direction 
		 */
		public void pidWrite(double PIDoutput)
		{	
			// Push values to the smart dashboard for debugging
			// Note: The magnitude should not change, but the direction is from the PID output
			SmartDashboard.putNumber("Left PIDoutput", PIDoutput);
			SmartDashboard.putNumber("Left Setpoint", boulderLeftController.getSetpoint());
			SmartDashboard.putNumber("Left Intake Pot", boulderLeftPosition.getVoltage());

			// Drive the boulder intake motor
			boulderIntakeLeft.set(-PIDoutput);
		}
	}
}
