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

import org.usfirst.frc3824.BetaBot.utilities.HVAGyro;
import org.usfirst.frc3824.BetaBot.utilities.Lidar;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
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
    public static SpeedController chassisRightMotorA;
    public static SpeedController chassisRightMotorB;
    public static SpeedController chassisLeftMotorA;
    public static SpeedController chassisLeftMotorB;
    public static RobotDrive chassisWCDrive4;
    public static Compressor chassisCompressor;
    public static Solenoid chassisTransmission;
    public static Encoder chassisEncoderRight;
    public static Encoder chassisEncoderLeft;
    public static AnalogInput chassisUltrasonicSensorRight;
    public static AnalogInput chassisUltrasonicSensorLeft;
    public static PowerDistributionPanel powerPowerDistributionPanel;
    public static AnalogPotentiometer shooterAnalogPotentiometer;
    public static SpeedController shooterActuator;
    public static CANTalon shooterWheelRightA;
    public static CANTalon shooterWheelRightB;
    public static CANTalon shooterWheelLeftA;
    public static CANTalon shooterWheelLeftB;
    public static Solenoid shooterBallShooterPiston;
    public static Solenoid battleAxesAxeRight;
    public static Solenoid battleAxesAxeLeft;
    public static SpeedController battleAxesRightMotor;
    public static SpeedController battleAxesLeftMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public static HVAGyro chassisGyro;
    
    public static Lidar chassisLidar;

	public static void init()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
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
        
        chassisEncoderRight = new Encoder(2, 3, false, EncodingType.k4X);
        LiveWindow.addSensor("Chassis", "Encoder Right", chassisEncoderRight);
        chassisEncoderRight.setDistancePerPulse(0.058994);
        chassisEncoderRight.setPIDSourceType(PIDSourceType.kDisplacement);
        chassisEncoderLeft = new Encoder(4, 5, false, EncodingType.k4X);
        LiveWindow.addSensor("Chassis", "Encoder Left", chassisEncoderLeft);
        chassisEncoderLeft.setDistancePerPulse(0.058994);
        chassisEncoderLeft.setPIDSourceType(PIDSourceType.kDisplacement);
        chassisUltrasonicSensorRight = new AnalogInput(3);
        LiveWindow.addSensor("Chassis", "Ultrasonic Sensor Right", chassisUltrasonicSensorRight);
        
        chassisUltrasonicSensorLeft = new AnalogInput(2);
        LiveWindow.addSensor("Chassis", "Ultrasonic Sensor Left", chassisUltrasonicSensorLeft);
        
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
        
        battleAxesAxeRight = new Solenoid(0, 7);
        LiveWindow.addActuator("Battle Axes", "Axe Right", battleAxesAxeRight);
        
        battleAxesAxeLeft = new Solenoid(0, 0);
        LiveWindow.addActuator("Battle Axes", "Axe Left", battleAxesAxeLeft);
        
        battleAxesRightMotor = new Talon(5);
        LiveWindow.addActuator("Battle Axes", "Right Motor", (Talon) battleAxesRightMotor);
        
        battleAxesLeftMotor = new Talon(6);
        LiveWindow.addActuator("Battle Axes", "Left Motor", (Talon) battleAxesLeftMotor);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        
        
        chassisGyro = new HVAGyro(0);
        LiveWindow.addSensor("Chassis", "Gyro", chassisGyro);
        chassisGyro.setSensitivity(0.007);
        
        chassisLidar = new Lidar(0, 1);
//        LiveWindow.addSensor("Independent", "Lidar Range", chassisLidar);
        
        shooterWheelLeftA.setInverted(true);
        shooterWheelLeftB.setInverted(true);
	}
}
