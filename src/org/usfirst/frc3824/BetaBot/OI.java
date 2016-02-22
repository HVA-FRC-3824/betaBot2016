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
    public Joystick driveJoystick;
    public JoystickButton jogUp;
    public JoystickButton jogDown;
    public JoystickButton intake;
    public JoystickButton intakeBoulder;
    public JoystickButton home;
    public JoystickButton homeBoulder;
    public JoystickButton shoot1;
    public JoystickButton shoot2;
    public JoystickButton shoot3;
    public JoystickButton shoot4;
    public JoystickButton shoot5;
    public JoystickButton boulderIntakeLow;
    public JoystickButton shooterWheelsOut;
    public JoystickButton shooterWheelsIn;
    public JoystickButton shooterWheelsOutReleased;
    public JoystickButton shooterWheelsInReleased;
    public JoystickButton intakePotEnable;
    public Joystick controllerJoystick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public OI()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        controllerJoystick = new Joystick(1);
        
        intakePotEnable = new JoystickButton(controllerJoystick, 6);
        intakePotEnable.whileHeld(new BoulderIntakeControl(-600.0, false));
        shooterWheelsInReleased = new JoystickButton(controllerJoystick, 19);
        shooterWheelsInReleased.whenReleased(new ShooterSetWheelSpeed(0));
        shooterWheelsOutReleased = new JoystickButton(controllerJoystick, 20);
        shooterWheelsOutReleased.whenReleased(new ShooterSetWheelSpeed(0));
        shooterWheelsIn = new JoystickButton(controllerJoystick, 19);
        shooterWheelsIn.whenPressed(new ShooterSetWheelSpeed(-1));
        shooterWheelsOut = new JoystickButton(controllerJoystick, 20);
        shooterWheelsOut.whenPressed(new ShooterSetWheelSpeed(1));
        boulderIntakeLow = new JoystickButton(controllerJoystick, 14);
        boulderIntakeLow.whileHeld(new BoulderIntakeControl(-1400.0, false));
        shoot5 = new JoystickButton(controllerJoystick, 1);
        shoot5.whenPressed(new ShooterPositionControl(-100.0));
        shoot4 = new JoystickButton(controllerJoystick, 5);
        shoot4.whenPressed(new ShooterPositionControl(-500.0));
        shoot3 = new JoystickButton(controllerJoystick, 4);
        shoot3.whenPressed(new ShooterPositionControl(-400.0));
        shoot2 = new JoystickButton(controllerJoystick, 3);
        shoot2.whenPressed(new ShooterPositionControl(-300.0));
        shoot1 = new JoystickButton(controllerJoystick, 2);
        shoot1.whenPressed(new ShooterPositionControl(-200.0));
        homeBoulder = new JoystickButton(controllerJoystick, 17);
        homeBoulder.whenPressed(new BoulderIntakeControl(-1700.0, false));
        home = new JoystickButton(controllerJoystick, 17);
        home.whenPressed(new ShooterPositionControl(-1700.0));
        intakeBoulder = new JoystickButton(controllerJoystick, 18);
        intakeBoulder.whenPressed(new BoulderIntakeControl(-1800.0, true));
        intake = new JoystickButton(controllerJoystick, 18);
        intake.whenPressed(new ShooterPositionControl(-1800.0));
        jogDown = new JoystickButton(controllerJoystick, 10);
        jogDown.whenPressed(new ShooterPositionControl(-1000.0));
        jogUp = new JoystickButton(controllerJoystick, 11);
        jogUp.whenPressed(new ShooterPositionControl(-1100.0));
        driveJoystick = new Joystick(0);
        
        jogLeft = new JoystickButton(driveJoystick, 11);
        jogLeft.whenPressed(new ChassisTurnAngle(-5.0, 0));
        jogRight = new JoystickButton(driveJoystick, 12);
        jogRight.whenPressed(new ChassisTurnAngle(5.0, 0));
        shooterRetract = new JoystickButton(driveJoystick, 1);
        shooterRetract.whenReleased(new ShooterShoot(false));
        shooterExtend = new JoystickButton(driveJoystick, 1);
        shooterExtend.whenPressed(new ShooterShoot(true));
        joystickShiftPressed = new JoystickButton(driveJoystick, 2);
        joystickShiftPressed.whenPressed(new ShiftGear(true));
        joystickShiftReleased = new JoystickButton(driveJoystick, 2);
        joystickShiftReleased.whenReleased(new ShiftGear(false));


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous No Barrier Low Goal", new AutonomousNoBarrierLowGoal());
        SmartDashboard.putData("Autonomous No Barrier High Goal", new AutonomousNoBarrierHighGoal());
        SmartDashboard.putData("Autonomous Low Bar Drive Only", new AutonomousLowBarDriveOnly());
        SmartDashboard.putData("Autonomous Low Bar Shoot Low Goal", new AutonomousLowBarShootLowGoal());
        SmartDashboard.putData("Autonomous Low Bar Shoot High Goal", new AutonomousLowBarShootHighGoal());
        SmartDashboard.putData("Chassis Drive Straight", new ChassisDriveStraight());
        SmartDashboard.putData("Chassis Drive Straight Distance: TwoMeters", new ChassisDriveStraightDistance(200.0, 1.0));
        SmartDashboard.putData("Chassis Drive Target LIDAR: BaseOfTower", new ChassisDriveTargetLIDAR(100.0));
        SmartDashboard.putData("Chassis Turn Angle: Turn90", new ChassisTurnAngle(90.0, 0.6));
        SmartDashboard.putData("ShiftGear: highGear", new ShiftGear(true));
        SmartDashboard.putData("Shooter Shoot: ShootControl", new ShooterShoot(true));
        SmartDashboard.putData("Shoot Boulder In Goal: ShootLowGoal", new ShootBoulderInGoal(0.2, 0.5));
        SmartDashboard.putData("ChassisTurnToImageTarget", new ChassisTurnToImageTarget());
        SmartDashboard.putData("Pregame Configuration", new PregameConfiguration());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
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
