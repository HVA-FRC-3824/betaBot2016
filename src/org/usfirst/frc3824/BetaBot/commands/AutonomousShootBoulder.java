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
public class AutonomousShootBoulder extends CommandGroup {


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
    public AutonomousShootBoulder() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PARAMETERS
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=COMMAND_DECLARATIONS
 
    	addSequential(new ChassisDriveStraight(1.5, 0.5, 0.0));
//      addSequential(new ChassisDriveTargetLIDAR(100));
    	addSequential(new ChassisDriveTargetLIDAR(Robot.autoParameters.getLIDARDistance()));
      	addSequential(new ChassisTurnAngle(Robot.autoParameters.getTurnAngle(), 0.5));
      	addSequential(new ChassisDriveStraightDistance(Robot.autoParameters.getDriveStraightDistance()));
      
      	if (Robot.autoParameters.getShootAngle() == Constants.HIGH_GOAL)
      	{
      		addSequential(new ChassisTurnToImageTarget());
      		addSequential(new ShootBoulderInGoal(Robot.autoParameters.getShootAngle(), 1.0));
      	}
      	else
      	{
      		addSequential(new ShootBoulderInGoal(Robot.autoParameters.getShootAngle(), 1.0));
      	}
    } 
}
