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

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3824.BetaBot.Robot;
import org.usfirst.frc3824.BetaBot.RobotMap;

/**
 *
 */
public class JiggleBoulder extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	
	private Timer m_Timer;
	private double outtakeTime  = 0.6;
	private double intakeTime   = 1.4;
	private double delayTimeOne = 0.8;
	private double delayTimeTwo = 1.6;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public JiggleBoulder() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooter);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	m_Timer.start();
    	
    	if (m_Timer.get() <= outtakeTime)
    	{
    		Robot.shooter.ShooterRearWheelControl(1);
    	}
    	
    	if (m_Timer.get() < delayTimeOne && m_Timer.get() > outtakeTime)
    	{
    		Robot.shooter.ShooterAllWheelControl(0);
    	}
    	
    	if (m_Timer.get() <= intakeTime && m_Timer.get() >= delayTimeOne)
    	{
    		Robot.shooter.ShooterAllWheelControl(-1);
    	}
    	
    	if (m_Timer.get() < delayTimeTwo && m_Timer.get() > intakeTime)
    	{
    		Robot.shooter.ShooterAllWheelControl(0);
    	}
    	
    	m_Timer.stop();
    	m_Timer.reset();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	Robot.shooter.ShooterAllWheelControl(0);
    	m_Timer.stop();
    }
}
