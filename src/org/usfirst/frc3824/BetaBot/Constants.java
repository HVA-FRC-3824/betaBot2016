package org.usfirst.frc3824.BetaBot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants
{
	// Set the robot configuration (competition or practice)
	static final boolean IS_COMP_ROBOT = false;

	public static double SHOOTER_ELEVATION_ANGLE_A;
	public static double SHOOTER_ELEVATION_ANGLE_B;
	public static double SHOOTER_ELEVATION_ANGLE_C;
	public static double SHOOTER_ELEVATION_POT_A;
	public static double SHOOTER_ELEVATION_POT_B;
	public static double SHOOTER_ELEVATION_POT_C;
	
	public static double BOULER_INTAKE_RETRACTED_RIGHT;
	public static double BOULER_INTAKE_RETRACTED_LEFT;
	public static double BOULER_INTAKE_BOULDER_INTAKE_RIGHT;
	public static double BOULER_INTAKE_BOULDER_INTAKE_LEFT;
	public static double BOULER_INTAKE_BOULDER_CHEVAL_RIGHT;
	public static double BOULER_INTAKE_BOULDER_CHEVAL_LEFT;
	
	public static void InitConstants()
	{
		if (IS_COMP_ROBOT)
		{
			SmartDashboard.putString("Active Robot Values", "Thing 1");
			
			SHOOTER_ELEVATION_ANGLE_A             =  48.58;
			SHOOTER_ELEVATION_ANGLE_B             =  67.00;
			SHOOTER_ELEVATION_ANGLE_C             = -20.08;
			SHOOTER_ELEVATION_POT_A               = -0.00004;
			SHOOTER_ELEVATION_POT_B               =  0.0109;
			SHOOTER_ELEVATION_POT_C               =  0.2467;
			
			BOULER_INTAKE_RETRACTED_RIGHT         = 0.60;
			BOULER_INTAKE_RETRACTED_LEFT          = 0.36;
			BOULER_INTAKE_BOULDER_INTAKE_RIGHT    = 4.14;
			BOULER_INTAKE_BOULDER_INTAKE_LEFT     = 4.54;
			BOULER_INTAKE_BOULDER_CHEVAL_RIGHT    = 4.15;
			BOULER_INTAKE_BOULDER_CHEVAL_LEFT     = 4.60;
		}
		else // ---------- PRACTICE ROBOT ----------
		{
			SmartDashboard.putString("Active Robot Values", "Thing 2");
			
			SHOOTER_ELEVATION_ANGLE_A             = +49.75;
			SHOOTER_ELEVATION_ANGLE_B             = +65.00;
			SHOOTER_ELEVATION_ANGLE_C             = -15.00;
			SHOOTER_ELEVATION_POT_A               = -0.00004;
			SHOOTER_ELEVATION_POT_B               = +0.0114;
			SHOOTER_ELEVATION_POT_C               = +0.1949;
			
			BOULER_INTAKE_RETRACTED_RIGHT         = 0.60;
			BOULER_INTAKE_RETRACTED_LEFT          = 0.36;
			BOULER_INTAKE_BOULDER_INTAKE_RIGHT    = 4.14;
			BOULER_INTAKE_BOULDER_INTAKE_LEFT     = 4.54;
			BOULER_INTAKE_BOULDER_CHEVAL_RIGHT    = 4.15;
			BOULER_INTAKE_BOULDER_CHEVAL_LEFT     = 4.60;
		}
	}

	// ***************************************************************************************
	// Drive train turn constants
	public static double DRIVETRAIN_DRIVE_STRAIGHT_P           = 0.1;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_I           = 0.0;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_D           = 0.0;
	
	// ***************************************************************************************
	// Autonomous parameters
	// Using degrees for turn and degrees for shooter angle
	public static double	TURN_RIGHT1                           = 50.0;
	public static double	TURN_RIGHT2                           = 30.0;
	public static double   TURN_NONE                              = 0.0;
	public static double   TURN_LEFT5                             = -30.0;
	public static double   LOW_GOAL                               = 50.0;
	public static double   HIGH_GOAL                              = 80.0;
	public static double   LIDAR_DISTANCE1					      = 500.0;
	public static double   LIDAR_DISTANCE2					      = 400.0;
	public static double   LIDAR_DISTANCE3					      = 700.0;
	public static double   LIDAR_DISTANCE4					      = 700.0;
	public static double   LIDAR_DISTANCE5					      = 400.0;
	public static double   DRIVE_STRAIGHT_DISTANCE1				  = 50.0;
	public static double   DRIVE_STRAIGHT_DISTANCE2				  = 40.0;
	public static double   DRIVE_STRAIGHT_DISTANCE3				  = 0.0;
	public static double   DRIVE_STRAIGHT_DISTANCE4				  = 0.0;
	public static double   DRIVE_STRAIGHT_DISTANCE5				  = 40.0;

	// ***************************************************************************************
	// Image turn constants
	public static double IMAGE_TURN_P                          = 0.03;	// Preference name: ImageTurn_P
	public static double IMAGE_TURN_I                          = 0.0;	// Preference name: ImageTurn_I
	public static double IMAGE_TURN_D                          = 0.25;	// Preference name: ImageTurn_D
																		// Preference name: ImageTurn_MaxAbsOutput
	public static int    IMAGE_WIDTH                           = 320;	// default image width
	public static int    IMAGE_HEIGHT                          = 240;   // default image height
	public static double CAM_FOV                               = 48.0; 

	public static double DRIVETRAIN_TURN_ANGLE_P               = 0.1;
	public static double DRIVETRAIN_TURN_ANGLE_I               = 0.0;
	public static double DRIVETRAIN_TURN_ANGLE_D               = 0.0;
	
	public static double TURN_THRESHOLD                        = 3.0;
		
	// ***************************************************************************************
	// Boulder Intake constants
	public static double BOULDER_INTAKE_P                      = 1.0;
	public static double BOULDER_INTAKE_I                      = 0.0;
	public static double BOULDER_INTAKE_D                      = 0.0;

	public static double BOULDER_INTAKE_POWER                  = 0.4;
	public static double BOULDER_INTAKE_TOLERANCE              = 0.05;

	public static double BOULDER_ROLLER_SPEED                  = 0.6;
//	public static double BOULDER_INTAKE_POSITION_REST          = 1.10;
//	public static double BOULDER_INTAKE_POSITION_DOWN          = 4.25;
//	public static double BOULDER_INTAKE_POSITION_INTAKE        = 4.50;
		
	// ***************************************************************************************
	// Shooter Elevation constants
	public static double SHOOTER_ELEVATION_SETPOINT_MIN        = 10.0;
	public static double SHOOTER_ELEVATION_SETPOINT_MAX        = 60.0;
	
	public static double SHOOTER_ELEVATION_INTAKE              = 10.0;
	public static double SHOOTER_ELEVATION_REST                = 20.0;
	public static double SHOOTER_ELEVATION_SHOOT_POSITION1     = 30.0;
	public static double SHOOTER_ELEVATION_SHOOT_POSITION2     = 40.0;
	public static double SHOOTER_ELEVATION_SHOOT_POSITION3     = 50.0;
	public static double SHOOTER_ELEVATION_SHOOT_POSITION4     = 60.0;
}
