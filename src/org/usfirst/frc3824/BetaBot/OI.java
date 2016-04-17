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

import org.usfirst.frc3824.BetaBot.commands.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton joystickShiftReleased;
    public JoystickButton joystickShiftPressed;
    public JoystickButton shooterExtend;
    public JoystickButton shooterRetract;
    public JoystickButton jogRight;
    public JoystickButton jogLeft;
    public JoystickButton aimandShoot;
    public JoystickButton aimandShootXOnly;
    public JoystickButton teleopDriveOverDefense;
    public JoystickButton jiggleBoulder;
    public JoystickButton jiggleBoulderRelease;
    public Joystick driveJoystick;
    public JoystickButton jogUp;
    public JoystickButton jogDown;
    public JoystickButton intake;
    public JoystickButton intakeNoBoulderArm;
    public JoystickButton home;
    public JoystickButton shoot1;
    public JoystickButton shoot2;
    public JoystickButton shoot3;
    public JoystickButton shoot4;
    public JoystickButton shoot5;
    public JoystickButton shooterWheelsOut;
    public JoystickButton shooterWheelsOutSlow;
    public JoystickButton shooterWheelsIn;
    public JoystickButton shooterWheelsOutReleased;
    public JoystickButton shooterWheelsOutSlowReleased;
    public JoystickButton shooterWheelsInReleased;
    public JoystickButton extendArms;
    public JoystickButton retractArms;
    public JoystickButton boulderJiggle;
    public Joystick controllerJoystick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

//    public JoystickButton DriverJogUp;
//    public JoystickButton DriverJogDown;
//    public JoystickButton DriverEnableShooterWheelsOn;
//    public JoystickButton DriverEnableShooterWheelsOff;

	public OI()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        controllerJoystick = new Joystick(1);
        
        boulderJiggle = new JoystickButton(controllerJoystick, 8);
        boulderJiggle.whenPressed(new JiggleBoulder());
        retractArms = new JoystickButton(controllerJoystick, 14);
        retractArms.whenReleased(new AxeControlUp());
        extendArms = new JoystickButton(controllerJoystick, 14);
        extendArms.whenPressed(new AxeControlDown());
        shooterWheelsInReleased = new JoystickButton(controllerJoystick, 13);
        shooterWheelsInReleased.whenReleased(new ShooterSetWheelSpeed(0));
        shooterWheelsOutSlowReleased = new JoystickButton(controllerJoystick, 19);
        shooterWheelsOutSlowReleased.whenReleased(new ShooterSetWheelSpeed(0));
        shooterWheelsOutReleased = new JoystickButton(controllerJoystick, 20);
        shooterWheelsOutReleased.whenReleased(new ShooterSetWheelSpeed(0));
        shooterWheelsIn = new JoystickButton(controllerJoystick, 13);
        shooterWheelsIn.whenPressed(new ShooterSetWheelSpeed(-0.8));
        shooterWheelsOutSlow = new JoystickButton(controllerJoystick, 19);
        shooterWheelsOutSlow.whenPressed(new ShooterSetWheelSpeed(0.5));
        shooterWheelsOut = new JoystickButton(controllerJoystick, 20);
        shooterWheelsOut.whenPressed(new ShooterSetWheelSpeed(1.0));
        shoot5 = new JoystickButton(controllerJoystick, 1);
        shoot5.whenPressed(new ShooterPositionControl(-100.0));
        shoot4 = new JoystickButton(controllerJoystick, 5);
        shoot4.whenPressed(new ShooterPositionControl(-500.0));
        shoot3 = new JoystickButton(controllerJoystick, 4);
        shoot3.whenPressed(new ShooterPositionControl(-400.0));
        shoot2 = new JoystickButton(controllerJoystick, 3);
        shoot2.whenPressed(new ShooterPositionControl(-300.0));
        shoot1 = new JoystickButton(controllerJoystick, 17);
        shoot1.whenPressed(new ShooterPositionControl(-1700.0));
        home = new JoystickButton(controllerJoystick, 2);
        home.whenPressed(new ShooterPositionControl(-200.0));
        intakeNoBoulderArm = new JoystickButton(controllerJoystick, 8);
        intakeNoBoulderArm.whenPressed(new ShooterPositionControl(-1800.0));
        intake = new JoystickButton(controllerJoystick, 18);
        intake.whenPressed(new ShooterPositionControl(-1800.0));
        jogDown = new JoystickButton(controllerJoystick, 10);
        jogDown.whenPressed(new ShooterPositionControl(-1000.0));
        jogUp = new JoystickButton(controllerJoystick, 11);
        jogUp.whenPressed(new ShooterPositionControl(-1100.0));
        driveJoystick = new Joystick(0);
        
        jiggleBoulderRelease = new JoystickButton(driveJoystick, 7);
        jiggleBoulderRelease.whenReleased(new IntakeBoulderAfterJiggling());
        jiggleBoulder = new JoystickButton(driveJoystick, 7);
        jiggleBoulder.whileHeld(new JiggleBoulder());
        teleopDriveOverDefense = new JoystickButton(driveJoystick, 3);
        teleopDriveOverDefense.whileHeld(new TeleopAutoDriveOverDefense());
        aimandShootXOnly = new JoystickButton(driveJoystick, 6);
        aimandShootXOnly.whileHeld(new VisionAutomatedAimAndShootX());
        aimandShoot = new JoystickButton(driveJoystick, 5);
        aimandShoot.whileHeld(new VisionAutomatedAimAndShoot());
        jogLeft = new JoystickButton(driveJoystick, 11);
        jogLeft.whenPressed(new ChassisTurnJog(-1.0));
        jogRight = new JoystickButton(driveJoystick, 12);
        jogRight.whenPressed(new ChassisTurnJog(1.0));
        shooterRetract = new JoystickButton(driveJoystick, 1);
        shooterRetract.whenReleased(new ShooterShoot(false));
        shooterExtend = new JoystickButton(driveJoystick, 1);
        shooterExtend.whenPressed(new ShooterShoot(true));
        joystickShiftPressed = new JoystickButton(driveJoystick, 2);
        joystickShiftPressed.whenPressed(new ShiftGear(true));
        joystickShiftReleased = new JoystickButton(driveJoystick, 2);
        joystickShiftReleased.whenReleased(new ShiftGear(false));


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Low Bar: NoShot", new AutonomousLowBar(0));
        SmartDashboard.putData("Pregame Configuration", new PregameConfiguration());
        SmartDashboard.putData("Set Gyro Center", new SetGyroCenter());
        SmartDashboard.putData("Restart Image Processing", new RestartImageProcessing());
        SmartDashboard.putData("Melee", new Melee());
        SmartDashboard.putData("AutonomousAutoDriveOverDefense: CommandTest", new AutonomousAutoDriveOverDefense(200, 0.7));
        SmartDashboard.putData("Jiggle Boulder", new JiggleBoulder());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
//        DriverJogDown = new JoystickButton(driveJoystick, 4);
//        DriverJogDown.whenPressed(new ShooterPositionControl(-1000.0));
//        DriverJogUp = new JoystickButton(driveJoystick, 6);
//        DriverJogUp.whenPressed(new ShooterPositionControl(-1100.0));
//        DriverEnableShooterWheelsOn = new JoystickButton(driveJoystick, 3);
//        DriverEnableShooterWheelsOn.whenPressed(new ShooterSetWheelSpeed(1));
//        DriverEnableShooterWheelsOff = new JoystickButton(driveJoystick, 3);
//        DriverEnableShooterWheelsOff.whenReleased(new ShooterSetWheelSpeed(0));

//        SmartDashboard.putData("ChassisTurnToImageTarget LEFT", new ChassisTurnToImageTarget(ChassisTurnToImageTarget.kTargetLeft));
	}

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriveJoystick() {
        return driveJoystick;
    }

    public Joystick getControllerJoystick() {
        return controllerJoystick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}
