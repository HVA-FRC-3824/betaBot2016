package org.usfirst.frc3824.BetaBot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants
{
	// Set the robot configuration (competition or practice)
	// Thing 1 - true  (Competition)
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
			
			SHOOTER_ELEVATION_ANGLE_A       =  66.893;
			SHOOTER_ELEVATION_ANGLE_B       =  46.043;
			SHOOTER_ELEVATION_ANGLE_C       = -17.92;
			
			SHOOTER_ELEVATION_POT_A         = -0.00005;
			SHOOTER_ELEVATION_POT_B         =  0.0115;
			SHOOTER_ELEVATION_POT_C         =  0.2713;
			
			BOUDLER_INTAKE_RETRACTED_RIGHT  = 0.524;
			BOUDLER_INTAKE_RETRACTED_LEFT   = 0.021;
			
			BOUDLER_INTAKE_EXTENDED_RIGHT   = 4.214;
			BOUDLER_INTAKE_EXTENDED_LEFT    = 4.474;
		}
		else // Thing 2
		{
			SmartDashboard.putString("Active Robot Values", "Thing 2");
			
			SHOOTER_ELEVATION_ANGLE_A       = +49.75;
			SHOOTER_ELEVATION_ANGLE_B       = +65.00;
			SHOOTER_ELEVATION_ANGLE_C       = -15.00;
			
			SHOOTER_ELEVATION_POT_A         = -0.00004;
			SHOOTER_ELEVATION_POT_B         = +0.0114;
			SHOOTER_ELEVATION_POT_C         = +0.1949;
			
			BOUDLER_INTAKE_RETRACTED_RIGHT  = 0.234;
			BOUDLER_INTAKE_RETRACTED_LEFT   = 0.078;
		
			BOUDLER_INTAKE_EXTENDED_RIGHT   = 3.76;
			BOUDLER_INTAKE_EXTENDED_LEFT    = 3.84;
		}
	}

	// ***************************************************************************************
	// Autonomous parameters
	public static final int DEFENSE_DO_NOTHING                = 0;
	public static final int DEFENSE_LOW_BAR                   = 1;
	public static final int DEFENSE_ROUGH_TERRIAN             = 2;
	public static final int DEFENSE_RAMPARTS                  = 3;
	public static final int DEFENSE_ROCK_WALL                 = 4;
	public static final int DEFENSE_MOAT                      = 5;
	public static final int DEFENSE_CHEVAL_DE_FRISE           = 6;
	public static final int DEFENSE_PORTCULLIS                = 7;
	
	public static int STARTING_POSITION_SPY                   = 1;
	public static int STARTING_POSITION_2                     = 2;
	public static int STARTING_POSITION_3                     = 3;
	public static int STARTING_POSITION_4                     = 4;
	public static int STARTING_POSITION_5                     = 5;
	public static int STARTING_POSITION_6                     = 6;
	
	public static int NO_GOAL                                 = 0;
	public static int LOW_GOAL                                = 1;
	public static int HIGH_GOAL                               = 2;

	// ***************************************************************************************
	// Drive train turn constants
	public static double DRIVETRAIN_DRIVE_STRAIGHT_P          =  0.1;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_I          =  0.0;
	public static double DRIVETRAIN_DRIVE_STRAIGHT_D          =  0.0;
	
	public static double DRIVETRAIN_DRIVE_MINIMUM_OUTPUT      = -1.0;
	public static double DRIVETRAIN_DRIVE_MAXIMUM_OUTPUT      =  1.0;	

	// ***************************************************************************************
	// Chassis turn constants
	
	public static double TURN_ANGLE_P                         = 0.05;
	public static double TURN_ANGLE_I                         = 0.0;
	public static double TURN_ANGLE_D                         = 0.0;
	
	public static double TURN_ANGLE_MINIMUM_OUTPUT            = -0.9;
	public static double TURN_ANGLE_MAXIMUM_OUTPUT            =  0.9;	

	public static double TURN_THRESHOLD                       = 5.0;

	// ***************************************************************************************
	// Image turn constants
	public static double IMAGE_TURN_P                         = 0.07;
	public static double IMAGE_TURN_I                         = 0.004;
	public static double IMAGE_TURN_D                         = 0.008;

	public static double IMAGE_TURN_MINIMUM_OUTPUT            = -0.4;
	public static double IMAGE_TURN_MAXIMUM_OUTPUT            =  0.4;
	
	public static int    IMAGE_WIDTH                          = 320;  // default image width
	public static int    IMAGE_HEIGHT                         = 240;  // default image height
	public static double CAM_FOV                              = 48.0; 
	
	public static int    TARGET_LEFT                          = 0;
	public static int    TARGET_CENTER                        = 1;
	public static int    TARGET_RIGHT                         = 2;
	
	// ***************************************************************************************
	// Shooter Elevation constants
	public static double SHOOTER_ELEVATION_SETPOINT_MIN       = 10.0;
	public static double SHOOTER_ELEVATION_SETPOINT_MAX       = 60.0;
	
	public static double SHOOTER_ELEVATION_BOULDER_INTAKE     = -7.0;
	public static double SHOOTER_ELEVATION_HOME               = -4.7;
	public static double SHOOTER_ELEVATION_POSITION1          =  43.5;
	public static double SHOOTER_ELEVATION_POSITION2          =  18.0;
	public static double SHOOTER_ELEVATION_POSITION3          =  65.0;
	public static double SHOOTER_ELEVATION_POSITION4          =  69.0;
	public static double SHOOTER_ELEVATION_POSITION5          =  45.0;
	
	public static double SHOOTER_JOG_UP_BUTTON                = -1100.0;  // Button 11
	public static double SHOOTER_JOG_DOWN_BUTTON              = -1000.0;  // Button 10
	public static double SHOOTER_BOULDER_INTAKE_BUTTON        = -1800.0;  // Button 18
	public static double SHOOTER_HOME_BUTTON                  =  -200.0;  // Button  2
	public static double SHOOTER_SHOOT_1_BUTTON               = -1700.0;  // Button 17
	public static double SHOOTER_SHOOT_2_BUTTON               =  -300.0;  // Button  3
	public static double SHOOTER_SHOOT_3_BUTTON               =  -400.0;  // Button  4
	public static double SHOOTER_SHOOT_4_BUTTON               =  -500.0;  // Button  5
	public static double SHOOTER_SHOOT_5_BUTTON               =  -100.0;  // Button  1

	// ***************************************************************************************
	// Chassis Turn Jog constants
	public static double JOG_TURN_WATCHDOG_TIME               =  1.0;
	public static double JOG_TURN_WHEEL_POWER                 =  0.3;
	public static double JOG_TURN_ENCODER_TURN_VALUE          =   15;    // integer	
	
	// ***************************************************************************************
	// Autonomous constants
	public static double AUTONOMOUS_LIDAR_RANGE_SHOOTER_ANGLE =  40.0;
	public static double AUTONOMOUS_LIDAR_DISTANCE_TO_TARGET  = 240.0;
	
	// ***************************************************************************************
	// Automated aim and shoot constants
	public static double IMAGE_SHOOTER_WHEEL_SPINUP_TIME      = 1.5;
	
	public static int IMAGE_LARGE_PIXEL_OFFSET_Y 			  = 40;
	public static int IMAGE_MEDIUM_PIXEL_OFFSET_Y 			  = 20;
	public static int IMAGE_SMALL_PIXEL_OFFSET_Y 			  =  0;
	
	public static double IMAGE_LARGE_STEP_ANGLE_Y             = 2.0;
	public static double IMAGE_MEDIUM_STEP_ANGLE_Y            = 1.0;
	public static double IMAGE_SMALL_STEP_ANGLE_Y             = 0.1;

	public static int IMAGE_LARGE_PIXEL_OFFSET_X 			  = 40;
	public static int IMAGE_MEDIUM_PIXEL_OFFSET_X 			  = 20;
	public static int IMAGE_SMALL_PIXEL_OFFSET_X 			  =  0;
	
	public static double IMAGE_LARGE_STEP_ANGLE_X             = 1.50;  // mm
	public static double IMAGE_MEDIUM_STEP_ANGLE_X            = 0.50;  // mm
	public static double IMAGE_SMALL_STEP_ANGLE_X             = 0.15;  // mm

	public static double IMAGE_ANGLE_ENCODER_P                = 0.3;
	public static double IMAGE_ANGLE_ENCODER_I                = 0.0005;
	public static double IMAGE_ANGLE_ENCODER_D                = 0.0;
	
	public static double IMAGE_ANGLE_MINIMUM_INPUT            = -1000.0;
	public static double IMAGE_ANGLE_MAXIMUM_INPUT            =  1000.0;	

	public static double IMAGE_ANGLE_MINIMUM_OUTPUT           = -0.4;
	public static double IMAGE_ANGLE_MAXIMUM_OUTPUT           =  0.4;	
	
	public static int IMAGE_TURN_TO_TARGET_X                  = 20;
	public static int IMAGE_TURN_TO_TARGET_Y                  = 50;

	public static double IMAGE_SEARCH_MIN_SHOOTER_POSITION    = 35;
	public static double IMAGE_SEARCH_MAX_SHOOTER_POSITION    = 65;
	
	public static int IMAGE_ON_TARGET_X_FAR                   =   1;
	public static int IMAGE_ON_TARGET_Y_FAR                   =   2;

	public static double IMAGE_DISTANCE_MEDIUM                = 275;
	public static int IMAGE_ON_TARGET_X_MEDIUM                =   2;
	public static int IMAGE_ON_TARGET_Y_MEDIUM                =   4;

	public static double IMAGE_DISTANCE_CLOSE                 = 200;
	public static int IMAGE_ON_TARGET_X_CLOSE                 =   3;
	public static int IMAGE_ON_TARGET_Y_CLOSE                 =   6;
	
	public static double DISTANCE_A                           =  0.0839;  //  0.0838;   // x^2
	public static double DISTANCE_B                           = -16.762;  // -16.665;   // x
	public static double DISTANCE_C                           =  967.84;  //  952.25;   // offset
	
	public static double IMAGE_Y_A                            =  0.0001;    //0.00005; //   0.0001;    // x^2
	public static double IMAGE_Y_B                            =  0.0534;    //0.1887;  //   0.0534;    // x
	public static double IMAGE_Y_C                            =  172.81;    //141.11;  //   172.81;    // offset
	
	public static int    IMAGE_ON_TARGET_X_POSITION           = 144;        // X location of the "onTarget" position
	public static int    IMAGE_ON_TARGET_Y_OFFSET             =   0;
}
