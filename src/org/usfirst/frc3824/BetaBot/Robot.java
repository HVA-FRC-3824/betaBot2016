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

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    public static TargetCam targetCam;
    public static Shooter shooter;
    public static BoulderIntake boulderIntake;
    public static Targets targets;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public static SendableChooser defenseChooser;
	public static SendableChooser startingLocationChooser;
	public static SendableChooser gripChooser;
	public static AutoParameters autoParameters;
	public class AutoParameters
	{
		public double m_turnAngle;
		public double m_shootAngle;
		public double m_lidarDistance;
		public double m_driveStraightDistance;
		
		AutoParameters(double turnAngle, double shootAngle, double lidarDistance, double driveStraightDistance)
		{
			m_turnAngle = turnAngle;
			m_shootAngle = shootAngle;
			m_lidarDistance = lidarDistance;
		}
		
		public double getTurnAngle()
		{
			return m_turnAngle;
		}
		
		public double getShootAngle()
		{
			return m_shootAngle;
		}
		
		public double getLIDARDistance()
		{
			return m_lidarDistance;
		}
		
		public double getDriveStraightDistance()
		{
			return m_driveStraightDistance;
		}
	}
	
	private boolean m_runGripPrevious;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit()
	{
		m_runGripPrevious = false;
		
		// Initialize the robot constants
		Constants.InitConstants();

		// Initialize the robot components
		RobotMap.init();

		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        chassis = new Chassis();
        power = new Power();
        targetCam = new TargetCam();
        shooter = new Shooter();
        boulderIntake = new BoulderIntake();
        targets = new Targets();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();

		// instantiate the command used for the autonomous period
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

		// set up the chooser for the defense class 
		// - this tells us what we need to do to get past the defense
		defenseChooser = new SendableChooser();
		defenseChooser.addDefault("1) Do Nothing", new AutonomousShootBoulder());
		defenseChooser.addDefault("2) Drive Over Defense", new AutonomousShootBoulder());
		defenseChooser.addDefault("3) Cheval de Frise", new AutonomousShootBoulder());
		defenseChooser.addDefault("4) Portcullis", new AutonomousShootBoulder());
		defenseChooser.addDefault("5) Drawbridge", new AutonomousShootBoulder());
		defenseChooser.addDefault("6) Sally Port", new AutonomousShootBoulder());
		defenseChooser.addDefault("7) Low Bar", new AutonomousShootBoulder());
		defenseChooser.addDefault("8) Turn To Image Target", new ChassisTurnToImageTarget());
		SmartDashboard.putData("Defense to cross", defenseChooser);
		
		// set up the chooser for the starting location and high goal vs low goal shot
		// - this tells us what direction we need to turn to get the goal in sight and
		//   whether we're shooting high or low.
		startingLocationChooser = new SendableChooser();
		startingLocationChooser.addDefault("1 Low", new AutoParameters(Constants.TURN_RIGHT1, Constants.LOW_GOAL, Constants.LIDAR_DISTANCE1, Constants.DRIVE_STRAIGHT_DISTANCE1));
		startingLocationChooser.addObject("1 High", new AutoParameters(Constants.TURN_RIGHT1, Constants.HIGH_GOAL, Constants.LIDAR_DISTANCE1, Constants.DRIVE_STRAIGHT_DISTANCE1));
		startingLocationChooser.addDefault("2 Low", new AutoParameters(Constants.TURN_RIGHT2, Constants.LOW_GOAL, Constants.LIDAR_DISTANCE2, Constants.DRIVE_STRAIGHT_DISTANCE2));
		startingLocationChooser.addObject("2 High", new AutoParameters(Constants.TURN_RIGHT2, Constants.HIGH_GOAL, Constants.LIDAR_DISTANCE2, Constants.DRIVE_STRAIGHT_DISTANCE2));
		startingLocationChooser.addObject("3 (High)", new AutoParameters(Constants.TURN_NONE, Constants.HIGH_GOAL, Constants.LIDAR_DISTANCE3, Constants.DRIVE_STRAIGHT_DISTANCE3));
		startingLocationChooser.addObject("4 (High)", new AutoParameters(Constants.TURN_NONE, Constants.HIGH_GOAL, Constants.LIDAR_DISTANCE4, Constants.DRIVE_STRAIGHT_DISTANCE4));
		startingLocationChooser.addObject("5 Low", new AutoParameters(Constants.TURN_LEFT5, Constants.LOW_GOAL, Constants.LIDAR_DISTANCE5, Constants.DRIVE_STRAIGHT_DISTANCE5));
		startingLocationChooser.addObject("5 High", new AutoParameters(Constants.TURN_LEFT5, Constants.HIGH_GOAL, Constants.LIDAR_DISTANCE5, Constants.DRIVE_STRAIGHT_DISTANCE5));
		SmartDashboard.putData("Starting Location & Shot", startingLocationChooser );

		
		// add a chooser to control the operation of the GRIP process on the ROBORIO
		gripChooser = new SendableChooser();
		gripChooser.addDefault("GRIP RUN", true);
		gripChooser.addObject("GRIP OFF", false);
		SmartDashboard.putData("Image Processing Control", gripChooser );

		
		RobotMap.chassisCompressor.setClosedLoopControl(true);
		
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

		Robot.targets.getTargetOffsetFromCenterNormalized();
		Robot.targets.updateSmartDashboard();
		
		boolean runGrip = (boolean) gripChooser.getSelected();
		if(runGrip && (m_runGripPrevious == false))
		{
			targetCam.configAndStartImageProcessing();
			m_runGripPrevious = true;
		}
		else if (!runGrip && (m_runGripPrevious == true))
		{
			targetCam.killImageProcessing();
			m_runGripPrevious = false;
		}
}

	public void autonomousInit()
	{
		// Determine the autonomous command
		if (defenseChooser.getSelected() != null)
		{
			// Reset the gyro before the start of autonomous
			Robot.chassis.resetGyro();

			// Get the autonomous command
			autonomousCommand = (edu.wpi.first.wpilibj.command.Command) defenseChooser.getSelected();

			autoParameters = (AutoParameters) startingLocationChooser.getSelected();
						
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
		SmartDashboard.putNumber("Gyro Angle", Robot.chassis.getGyro().getAngle());
		
		SmartDashboard.putNumber("Lidar Range (cm)", Robot.chassis.getLidarDistanceCentimeters());

		Robot.targets.getTargetOffsetFromCenterNormalized();
		Robot.targets.updateSmartDashboard();
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
		SmartDashboard.putBoolean("Compressor Enabled", RobotMap.chassisCompressor.enabled());
		SmartDashboard.putBoolean("Pressure Switch", RobotMap.chassisCompressor.getPressureSwitchValue());
		SmartDashboard.putNumber("Compressor Current", RobotMap.chassisCompressor.getCompressorCurrent());
		
		SmartDashboard.putNumber("Lidar Range (cm)", Robot.chassis.getLidarDistanceCentimeters());

		Robot.targets.getTargetOffsetFromCenterNormalized();
		Robot.targets.updateSmartDashboard();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic()
	{
		LiveWindow.run();
	}
}
