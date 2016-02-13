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

import org.usfirst.frc3824.BetaBot.utilities.Lidar;
import org.usfirst.frc3824.BetaBot.utilities.USBCamera;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static AnalogGyro chassisGyro;
    public static SpeedController chassisRightMotorA;
    public static SpeedController chassisRightMotorB;
    public static SpeedController chassisLeftMotorA;
    public static SpeedController chassisLeftMotorB;
    public static RobotDrive chassisWCDrive4;
    public static Compressor chassisCompressor;
    public static Solenoid chassisTransmission;
    public static PowerDistributionPanel powerPowerDistributionPanel;
    public static AnalogPotentiometer shooterAnalogPotentiometer;
    public static SpeedController shooterActuator;
    public static CANTalon shooterWheelRightA;
    public static CANTalon shooterWheelRightB;
    public static CANTalon shooterWheelLeftA;
    public static CANTalon shooterWheelLeftB;
    public static Solenoid shooterBallShooterPiston;
    public static SpeedController boulderIntakeBoulderIntakeRight;
    public static SpeedController boulderIntakeBoulderIntakeLeft;
    public static SpeedController boulderIntakeBoulderWheel;
    public static AnalogInput boulderIntakeBoudlerRightPosition;
    public static AnalogInput boulderIntakeBoulderLeftPosition;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static Lidar chassisLidar;

	public static void init()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        chassisGyro = new AnalogGyro(0);
        LiveWindow.addSensor("Chassis", "Gyro", chassisGyro);
        chassisGyro.setSensitivity(0.007);
        chassisRightMotorA = new Talon(0);
        LiveWindow.addActuator("Chassis", "Right Motor A", (Talon) chassisRightMotorA);
        
        chassisRightMotorB = new Talon(1);
        LiveWindow.addActuator("Chassis", "Right Motor B", (Talon) chassisRightMotorB);
        
        chassisLeftMotorA = new Talon(2);
        LiveWindow.addActuator("Chassis", "Left Motor A", (Talon) chassisLeftMotorA);
        
        chassisLeftMotorB = new Talon(3);
        LiveWindow.addActuator("Chassis", "Left Motor B", (Talon) chassisLeftMotorB);
        
        chassisWCDrive4 = new RobotDrive(chassisLeftMotorA, chassisLeftMotorB,
              chassisRightMotorA, chassisRightMotorB);
        
        chassisWCDrive4.setSafetyEnabled(true);
        chassisWCDrive4.setExpiration(0.1);
        chassisWCDrive4.setSensitivity(0.5);
        chassisWCDrive4.setMaxOutput(1.0);

        chassisCompressor = new Compressor(0);
        
        
        chassisTransmission = new Solenoid(0, 2);
        LiveWindow.addActuator("Chassis", "Transmission", chassisTransmission);
        
        powerPowerDistributionPanel = new PowerDistributionPanel(0);
        LiveWindow.addSensor("Power", "PowerDistributionPanel", powerPowerDistributionPanel);
        
        shooterAnalogPotentiometer = new AnalogPotentiometer(1, 1.0, 0.0);
        LiveWindow.addSensor("Shooter", "Analog Potentiometer", shooterAnalogPotentiometer);
        
        shooterActuator = new VictorSP(4);
        LiveWindow.addActuator("Shooter", "Actuator", (VictorSP) shooterActuator);
        
        shooterWheelRightA = new CANTalon(1);
        LiveWindow.addActuator("Shooter", "Wheel Right A", shooterWheelRightA);
        
        shooterWheelRightB = new CANTalon(2);
        LiveWindow.addActuator("Shooter", "Wheel Right B", shooterWheelRightB);
        
        shooterWheelLeftA = new CANTalon(3);
        LiveWindow.addActuator("Shooter", "Wheel Left A", shooterWheelLeftA);
        
        shooterWheelLeftB = new CANTalon(4);
        LiveWindow.addActuator("Shooter", "Wheel Left B", shooterWheelLeftB);
        
        shooterBallShooterPiston = new Solenoid(0, 1);
        LiveWindow.addActuator("Shooter", "Ball Shooter Piston", shooterBallShooterPiston);
        
        boulderIntakeBoulderIntakeRight = new Talon(5);
        LiveWindow.addActuator("Boulder Intake", "Boulder Intake Right", (Talon) boulderIntakeBoulderIntakeRight);
        
        boulderIntakeBoulderIntakeLeft = new Talon(6);
        LiveWindow.addActuator("Boulder Intake", "Boulder Intake Left", (Talon) boulderIntakeBoulderIntakeLeft);
        
        boulderIntakeBoulderWheel = new Talon(7);
        LiveWindow.addActuator("Boulder Intake", "Boulder Wheel", (Talon) boulderIntakeBoulderWheel);
        
        boulderIntakeBoudlerRightPosition = new AnalogInput(2);
        LiveWindow.addSensor("Boulder Intake", "Boudler Right Position", boulderIntakeBoudlerRightPosition);
        
        boulderIntakeBoulderLeftPosition = new AnalogInput(3);
        LiveWindow.addSensor("Boulder Intake", "Boulder Left Position", boulderIntakeBoulderLeftPosition);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
          
        
        chassisLidar = new Lidar(0, 1);
//        LiveWindow.addSensor("Independent", "Lidar Range", chassisLidar);

        shooterWheelRightA.setInverted(true);
        shooterWheelRightB.setInverted(true);
	}
}
