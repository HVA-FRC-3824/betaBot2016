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

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc3824.BetaBot.Constants;
import org.usfirst.frc3824.BetaBot.Robot;

/**
 *
 */
public class AutonomousLowBar extends CommandGroup
{

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    public AutonomousLowBar() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

		// Drive over the barrier
		addSequential(new ChassisDriveStraightDistance(400, 1.0));

		// Raise the shooter while turning towards the goal
		addSequential(new ShooterPositionControl(50.0));
		addSequential(new ChassisTurnAngle(45.0, 0.0));
	
		// Line up based on camera
		addSequential(new ChassisTurnToImageTarget(Constants.TARGET_LEFT));

//		// Determine if the Robot should shoot the boulder
//		if (Robot.autoParameters.getGoal() == Constants.LOW_GOAL)
//		{
//
//		}
//		else if (Robot.autoParameters.getGoal() == Constants.HIGH_GOAL)
//		{
//		addSequential(new Delay(2.0));
		
			// Shoot into the goal
			addSequential(new ShootBoulderInGoal(50.0, 1.0));
			addSequential(new ShooterSetWheelSpeed(0.0));
//		}
	}
}
