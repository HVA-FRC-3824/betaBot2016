package org.usfirst.frc3824.BetaBot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants
{
	// Set the robot configuration (competition or practice)
	static final boolean IS_COMP_ROBOT = false;

	public static void InitConstants()
	{
		if (IS_COMP_ROBOT)
		{
			SmartDashboard.putString("Active Robot Values", "COMPETITION");
		}
		else // ---------- PRACTICE ROBOT ----------
		{
			SmartDashboard.putString("Active Robot Values", "PRACTICE");
		}
	}

	// ***************************************************************************************
	// Drive train turn constants
	public static double DRIVETRAIN_DRIVE_STRAIGHT_P           = 0.1;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_I           = 0.0;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_D           = 0.0;
	
	// ***************************************************************************************
	// Autonomous parameters
	public static int	TURN_RIGHT                             = 0;
	public static int   TURN_NONE                              = 1;
	public static int   TURN_LEFT                              = 2;
	public static int   LOW_GOAL                               = 0;
	public static int   HIGH_GOAL                              = 1;

	// ***************************************************************************************
	// Image turn constants
	public static double IMAGE_TURN_P                          = 2.0;	// Preference name: ImageTurn_P
	public static double IMAGE_TURN_I                          = 0.0;	// Preference name: ImageTurn_I
	public static double IMAGE_TURN_D                          = 0.6;	// Preference name: ImageTurn_D
																		// Preference name: ImageTurn_MaxAbsOutput

	public static double DRIVETRAIN_TURN_ANGLE_P               = 0.1;
	public static double DRIVETRAIN_TURN_ANGLE_I               = 0.0;
	public static double DRIVETRAIN_TURN_ANGLE_D               = 0.0;
	
	public static double TURN_THRESHOLD                        = 3.0;
		
	// ***************************************************************************************
	// Boulder Intake constants
	public static double BOULDER_INTAKE_RIGHT_P                = 2.0;
	public static double BOULDER_INTAKE_RIGHT_I                = 0.0;
	public static double BOULDER_INTAKE_RIGHT_D                = 1.0;
	
	public static double BOULDER_INTAKE_LEFT_P                 = 0.5;
	public static double BOULDER_INTAKE_LEFT_I                 = 0.0;
	public static double BOULDER_INTAKE_LEFT_D                 = 0.0;
	
	public static double BOULDER_INTAKE_POWER                  = 0.7;
	public static double BOULDER_INTAKE_TOLERANCE              = 0.05;

	public static double BOULDER_POSITION_VELOCITY             = 0.2;
	public static double BOULDER_VELOCITY_OUTPUT               = 0.2;
	
	public static double BOULDER_ROLLER_SPEED                  = 0.7;
	public static double BOULDER_INTAKE_POSITION_REST          = 1.10;
	public static double BOULDER_INTAKE_POSITION_DOWN          = 4.25;
	public static double BOULDER_INTAKE_POSITION_INTAKE        = 4.50;
		
	// ***************************************************************************************
	// Shooter Elevation constants
	public static double SHOOTER_ELEVATION_SETPOINT_MIN        = 0.05;
	public static double SHOOTER_ELEVATION_SETPOINT_MAX        = 0.88;

	public static double SHOOTER_ELEVATION_ANGLE_A             = +56.9;
	public static double SHOOTER_ELEVATION_ANGLE_B             = +63.0;
	public static double SHOOTER_ELEVATION_ANGLE_C             = -7.82;
	public static double SHOOTER_ELEVATION_POT_A               = -0.00004;
	public static double SHOOTER_ELEVATION_POT_B               = +0.0118;
	public static double SHOOTER_ELEVATION_POT_C               = +0.1123;
	
	public static double SHOOTER_ELEVATION_INTAKE              = 45.0;
	public static double SHOOTER_ELEVATION_REST                = 45.0;
	public static double SHOOTER_ELEVATION_SHOOT_POSITION1     = 45.0;
	public static double SHOOTER_ELEVATION_SHOOT_POSITION2     = 45.0;
	public static double SHOOTER_ELEVATION_SHOOT_POSITION3     = 45.0;
	public static double SHOOTER_ELEVATION_SHOOT_POSITION4     = 45.0;
	
	// ***************************************************************************************
	// Shooter Wheel Speed constants
	public static double SHOOTER_WHEEL_MIN_MULTIPLIER          = 0.8;
	public static double SHOOTER_WHEEL_MAX_MULTIPLIER          = 1.5;
	public static double SHOOTER_WHEEL_TELEOP_CURVE_MULTIPLIER = 0.5;
}
