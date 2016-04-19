// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3824.BetaBot;

import org.usfirst.frc3824.BetaBot.Robot;
import org.usfirst.frc3824.BetaBot.RobotMap;
import org.usfirst.frc3824.BetaBot.Constants;
import org.usfirst.frc3824.BetaBot.commands.*;
import org.usfirst.frc3824.BetaBot.subsystems.*;
import org.usfirst.frc3824.BetaBot.subsystems.Targets.Target;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	Command autonomousCommand;

	public static OI oi;

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static Chassis chassis;
    public static Power power;
    public static Shooter shooter;
    public static Targets targets;
    public static TargetCam targetCam;
    public static BattleAxes battleAxes;
    public static Climber climber;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public static SendableChooser defenseChooser;
	public static SendableChooser startingLocationChooser;
	public static SendableChooser shotChooser;
	public static USBCamera driverCam;
	
	public class AutoParameters
	{
		public int m_turn;
		public int m_goal;
		
		AutoParameters(int turn, int goal)
		{
			m_turn = turn;
			m_goal = goal;
		}
		
		public int getTurn()
		{
			return m_turn;
		}
		
		public int getGoal()
		{
			return m_goal;
		}
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{
		// Initialize the robot constants
		Constants.InitConstants();

		// Initialize the robot components
		RobotMap.init();

		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        chassis = new Chassis();
        power = new Power();
        shooter = new Shooter();
        targets = new Targets();
        targetCam = new TargetCam();
        battleAxes = new BattleAxes();
        climber = new Climber();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();

		// instantiate the command used for the autonomous period
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

		// set up the chooser for the shooting.
		shotChooser = new SendableChooser();
		shotChooser.addDefault("No Shot",    Constants.NO_GOAL);
		shotChooser.addObject("High Shot",   Constants.HIGH_GOAL);
		shotChooser.addObject("Shot Return", Constants.SHOT_RETURN);
		SmartDashboard.putData("Shot Selection", shotChooser);
		
		// set up the chooser for the defense class 
		// - this tells us what we need to do to get past the defense
		defenseChooser = new SendableChooser();
		defenseChooser.addDefault("1) Do Nothing",     Constants.DEFENSE_DO_NOTHING);
		defenseChooser.addObject("2) Low Bar",         Constants.DEFENSE_LOW_BAR);
		defenseChooser.addObject("3) Rough Terrian",   Constants.DEFENSE_ROUGH_TERRIAN);
		defenseChooser.addObject("4) Ramparts",        Constants.DEFENSE_RAMPARTS);
		defenseChooser.addObject("5) Rock Wall",       Constants.DEFENSE_ROCK_WALL);
		defenseChooser.addObject("6) Moat",            Constants.DEFENSE_MOAT);
		defenseChooser.addObject("7) Cheval de Frise", Constants.DEFENSE_CHEVAL_DE_FRISE);
		defenseChooser.addObject("8) Portcullis",      Constants.DEFENSE_PORTCULLIS);
		SmartDashboard.putData("Defense to cross", defenseChooser);
		
		// set up the chooser for the starting location a
		startingLocationChooser = new SendableChooser();
		startingLocationChooser.addDefault("2", Constants.STARTING_POSITION_2);
		startingLocationChooser.addObject("3",  Constants.STARTING_POSITION_3);
		startingLocationChooser.addObject("4",  Constants.STARTING_POSITION_4);
		startingLocationChooser.addObject("5",  Constants.STARTING_POSITION_5);
		SmartDashboard.putData("Starting Location", startingLocationChooser );
		

		// Start the compressor
		RobotMap.chassisCompressor.setClosedLoopControl(true);
		
		// Show the calculated gyro center
		SmartDashboard.putNumber("Calculated Gryo Center", RobotMap.chassisGyro.getCenter());

		// Add a USB camera for the Driver
		boolean camConnected = false;
		for(int camNum = 0; camNum <= 0; camNum++)  // Thing 1 is CAM 3
		{
			try
			{
				System.out.println("Driver camera: attempting to connect to cam" + String.valueOf(camNum));
				driverCam = new USBCamera("cam" + String.valueOf(camNum));
				camConnected = true;
				System.out.println("Driver camera: successfully connected to cam" + String.valueOf(camNum));
			}
			catch(Exception e)
			{
				System.out.println("Driver camera: exception: " + e);
			}
			
			if(camConnected == true)
				break;
		}
		
		if(camConnected == true)
		{
			// Set the driver camera resolution and frames per second
			driverCam.setSize(320,  240);
			driverCam.setFPS(30);
			CameraServer.getInstance().startAutomaticCapture(driverCam);		
		}

		// Do a clean start of the image processing
		if (TargetCam.waitForVisionSystem() == true)
			TargetCam.cleanAndStartVision();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit()
	{

	}

	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();

//		int button = 0;
//		for (button = 1; button < Robot.oi.controllerJoystick.getButtonCount(); button++)
//		{
//			if (Robot.oi.controllerJoystick.getRawButton(button) == true)
//				break;
//		}
//		SmartDashboard.putNumber("Button", button);

		// Add current gyro angle to smart dashboard
		SmartDashboard.putNumber("Gyro Angle",       Robot.chassis.getCurrentHeading());	
//		SmartDashboard.putNumber("Ultrasonic Right", Robot.chassis.getRightUltrasonicDistance());
//		SmartDashboard.putNumber("Ultrasonic Left",  Robot.chassis.getLeftUltrasonicDistance());
		
		// find target - don't care about results, but this will also update the SmartDashboard
		Robot.targets.getTargetingInfo();
	}

	public void autonomousInit()
	{
		int startingLocation = 0;
		int shotChoice = Constants.NO_GOAL;
		
		// Determine the starting location
		if (startingLocationChooser.getSelected() != null)
			startingLocation = (int) startingLocationChooser.getSelected();

		// Determine the shoot parameter
		if (shotChooser.getSelected() != null)
			shotChoice = (int) shotChooser.getSelected();
			
		// Determine the autonomous command
		if (defenseChooser.getSelected() != null)
		{
			switch((int) defenseChooser.getSelected())
			{
			case Constants.DEFENSE_DO_NOTHING:
				autonomousCommand = new AutonomousDoNothing();
				break;
			case Constants.DEFENSE_LOW_BAR:
				autonomousCommand = new AutonomousLowBar(shotChoice);
				break;
			case Constants.DEFENSE_ROUGH_TERRIAN:
				autonomousCommand = new AutonomousRoughTerrian(startingLocation, shotChoice);
				break;
			case Constants.DEFENSE_RAMPARTS:
				autonomousCommand = new AutonomousRamparts(startingLocation, shotChoice);
				break;
			case Constants.DEFENSE_ROCK_WALL:
				autonomousCommand = new AutonomousRockWall(startingLocation, shotChoice);
				break;
			case Constants.DEFENSE_MOAT:
				autonomousCommand = new AutonomousMoat(startingLocation, shotChoice);
				break;
			case Constants.DEFENSE_CHEVAL_DE_FRISE:
				autonomousCommand = new AutonomousChevaldeFrise(startingLocation, shotChoice);
				break;	
			case Constants.DEFENSE_PORTCULLIS:
				autonomousCommand = new AutonomousPortcullis(startingLocation, shotChoice);
				break;		
			default:
				autonomousCommand = new AutonomousDoNothing();
				break;				
			}
				
			// Reset the gyro before the start of autonomous
			Robot.chassis.resetGyro();
				
			// schedule the autonomous command
			autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();

		// Add current gyro angle to smart dashboard
		SmartDashboard.putNumber("Gyro Angle", Robot.chassis.getCurrentHeading());	
		SmartDashboard.putNumber("Lidar Range (cm)", Robot.chassis.getLidarDistanceCentimeters());
//		SmartDashboard.putNumber("Ultrasonic Right", Robot.chassis.getRightUltrasonicDistance());
//		SmartDashboard.putNumber("Ultrasonic Left",  Robot.chassis.getLeftUltrasonicDistance());
		
//		Robot.targets.updateSmartDashboard();
}

	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic()
	{
		Scheduler.getInstance().run();
		
		// Add current gyro angle to smart dashboard
		SmartDashboard.putNumber("Gyro Angle", Robot.chassis.getCurrentHeading());	
		
		// Show the Lidar range on the SmartDashboard
		SmartDashboard.putNumber("Lidar Range (cm)", Robot.chassis.getLidarDistanceCentimeters());
//		SmartDashboard.putNumber("Ultrasonic Right", Robot.chassis.getRightUltrasonicDistance());
//		SmartDashboard.putNumber("Ultrasonic Left", Robot.chassis.getLeftUltrasonicDistance());
		
//		SmartDashboard.putNumber("X Encoder Error", Robot.chassis.getEncoderPID_Error());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()
	{
		LiveWindow.run();
	}
}
