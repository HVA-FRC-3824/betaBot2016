package org.usfirst.frc3824.BetaBot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants
{
	// Set the robot configuration (competition or practice)
	// Thing 1 - true
	// Thing 2 - false
	static final boolean IS_COMP_ROBOT = false;

	public static double SHOOTER_ELEVATION_ANGLE_A;
	public static double SHOOTER_ELEVATION_ANGLE_B;
	public static double SHOOTER_ELEVATION_ANGLE_C;
	public static double SHOOTER_ELEVATION_POT_A;
	public static double SHOOTER_ELEVATION_POT_B;
	public static double SHOOTER_ELEVATION_POT_C;
	
	public static double BOUDLER_INTAKE_RETRACTED_RIGHT;
	public static double BOUDLER_INTAKE_RETRACTED_LEFT;
	public static double BOUDLER_INTAKE_EXTENDED_RIGHT;
	public static double BOUDLER_INTAKE_EXTENDED_LEFT;
	
	public static void InitConstants()
	{
		if (IS_COMP_ROBOT)  // Thing 1
		{
			SmartDashboard.putString("Active Robot Values", "Thing 1");
			
			SHOOTER_ELEVATION_ANGLE_A             =  48.58;
			SHOOTER_ELEVATION_ANGLE_B             =  67.00;
			SHOOTER_ELEVATION_ANGLE_C             = -20.08;
			
			SHOOTER_ELEVATION_POT_A               = -0.00004;
			SHOOTER_ELEVATION_POT_B               =  0.0109;
			SHOOTER_ELEVATION_POT_C               =  0.2467;
			
			BOUDLER_INTAKE_RETRACTED_RIGHT         = 0.60;
			BOUDLER_INTAKE_RETRACTED_LEFT          = 0.36;
			
			BOUDLER_INTAKE_EXTENDED_RIGHT          = 4.14;
			BOUDLER_INTAKE_EXTENDED_LEFT           = 4.54;
		}
		else // Thing 2
		{
			SmartDashboard.putString("Active Robot Values", "Thing 2");
			
			SHOOTER_ELEVATION_ANGLE_A             = +49.75;
			SHOOTER_ELEVATION_ANGLE_B             = +65.00;
			SHOOTER_ELEVATION_ANGLE_C             = -15.00;
			
			SHOOTER_ELEVATION_POT_A               = -0.00004;
			SHOOTER_ELEVATION_POT_B               = +0.0114;
			SHOOTER_ELEVATION_POT_C               = +0.1949;
			
			BOUDLER_INTAKE_RETRACTED_RIGHT         = 0.234;
			BOUDLER_INTAKE_RETRACTED_LEFT          = 0.078;
		
			BOUDLER_INTAKE_EXTENDED_RIGHT          = 3.76;
			BOUDLER_INTAKE_EXTENDED_LEFT           = 3.84;
		}
	}

	// ***************************************************************************************
	// Autonomous parameters
	public static int TURN_RIGHT                               = 0;
	public static int TURN_NONE                                = 1;
	public static int TURN_LEFT                                = 2;
	
	public static int NO_GOAL                                  = 0;
	public static int LOW_GOAL                                 = 1;
	public static int HIGH_GOAL                                = 2;

	// ***************************************************************************************
	// Drive train turn constants
	public static double DRIVETRAIN_DRIVE_STRAIGHT_P           = 0.1;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_I           = 0.0;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_D           = 0.0;

	// ***************************************************************************************
	// Chassis turn constants
	
	public static double TURN_ANGLE_P                          = 0.05;
	public static double TURN_ANGLE_I                          = 0.0;
	public static double TURN_ANGLE_D                          = 0.0;
	
	public static double TURN_THRESHOLD                        = 3.0;

	// ***************************************************************************************
	// Image turn constants
	public static double IMAGE_TURN_P                          = 0.08;	// Preference name: ImageTurn_P
	public static double IMAGE_TURN_I                          = 0.008;	// Preference name: ImageTurn_I
	public static double IMAGE_TURN_D                          = 0.01;	// Preference name: ImageTurn_D
																		// Preference name: ImageTurn_MaxAbsOutput
	
	public static int    IMAGE_ON_TARGET_X_POSITION            = 160;   // X location of the "onTarget" position
	public static int    IMAGE_WIDTH                           = 320;	// default image width
	public static int    IMAGE_HEIGHT                          = 240;   // default image height
	public static double CAM_FOV                               = 48.0; 
	
	public static int    TARGET_LEFT                           = 0;
	public static int    TARGET_CENTER                         = 1;
	public static int    TARGET_RIGHT                          = 2;

	// ***************************************************************************************
	// Boulder Intake constants
	public static double BOULDER_INTAKE_P                      = 1.0;
	public static double BOULDER_INTAKE_I                      = 0.0;
	public static double BOULDER_INTAKE_D                      = 0.0;

	public static double BOULDER_INTAKE_EXTEND_POWER           =  0.6;
	public static double BOULDER_INTAKE_RETRACT_POWER          = -0.5;
	public static double BOULDER_INTAKE_TOLERANCE              =  0.05;

	public static double BOULDER_ROLLER_SPEED                  = 0.7;
		
	public static double BOULDER_INTAKE_HOME_BUTTON            = -1700.0;  // Button 17
	public static double BOULDER_INTAKE_BOULDER_BUTTON         = -1800.0;  // Button 18
    public static double BOULDER_INTAKE_MAX_EXTEND_BUTTON      = -1400.0;  // Button 14
    public static double BOULDER_INTAKE_MANUFAL_BUTTON         =  -600.0;  // Button  6
	
	// ***************************************************************************************
	// Shooter Elevation constants
	public static double SHOOTER_ELEVATION_SETPOINT_MIN        = 10.0;
	public static double SHOOTER_ELEVATION_SETPOINT_MAX        = 60.0;
	
	public static double SHOOTER_ELEVATION_BOULDER_INTAKE      = -7.0;
	public static double SHOOTER_ELEVATION_HOME                = -4.7;
	public static double SHOOTER_ELEVATION_POSITION1           = 43.5;
	public static double SHOOTER_ELEVATION_POSITION2           = 18.0;
	public static double SHOOTER_ELEVATION_POSITION3           = 65.0;
	public static double SHOOTER_ELEVATION_POSITION4           = 69.0;
	public static double SHOOTER_ELEVATION_POSITION5           = 45.0;
	
	public static double SHOOTER_JOG_UP_BUTTON                 = -1100.0;  // Button 11
	public static double SHOOTER_JOG_DOWN_BUTTON               = -1000.0;  // Button 10
	public static double SHOOTER_HOME_BUTTON                   = -1700.0;  // Button 17
	public static double SHOOTER_BOULDER_INTAKE_BUTTON         = -1800.0;  // Button 18
	public static double SHOOTER_SHOOT_1_BUTTON                =  -200.0;  // Button  2
	public static double SHOOTER_SHOOT_2_BUTTON                =  -300.0;  // Button  3
	public static double SHOOTER_SHOOT_3_BUTTON                =  -400.0;  // Button  4
	public static double SHOOTER_SHOOT_4_BUTTON                =  -500.0;  // Button  5
	public static double SHOOTER_SHOOT_5_BUTTON                =  -100.0;  // Button  1
}
