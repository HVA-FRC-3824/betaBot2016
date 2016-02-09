package org.usfirst.frc3824.BetaBot.utilities;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SensorBase;
import edu.wpi.first.wpilibj.livewindow.LiveWindowSendable;
import edu.wpi.first.wpilibj.tables.ITable;
/**
 * 
 * @author Robert Palmer - Team 3824
 * 
 * Lidar class is for the PulsedLight LidarLight v2
 *
 */
public class Lidar extends SensorBase implements PIDSource, LiveWindowSendable{
	private Counter m_counter;
	private DigitalOutput m_ping;
	private DigitalInput m_echo;
	private Boolean m_enabled;
	
	protected PIDSourceType m_pidSource = PIDSourceType.kDisplacement;

	
	/**
	 * Constants for the class
	 */
	private static final int kConversionFactorCentimeters = 100000;
	
	/**
	 * Create a Lidar object.  This expects the Lidar to be configured with the
	 * mode control pin such that pulling the pin low will create return pulses
	 * proportional to the distance.  The pulses will be generated as long as
	 * the Lidar is enabled.
	 */
	public Lidar(int pingChannel, int echoChannel)
	{
		m_ping = new DigitalOutput(pingChannel);
		m_echo = new DigitalInput(echoChannel);
		m_counter = new Counter(m_echo);
		
		initialize();
	}
	
	/**
	 * Common initializaiton of the Lidar
	 * - sets the counter to count the duration of high going pulses
	 * - set the ping channel to high which disables measurement
	 */
	private synchronized void initialize()
	{
		m_counter.setSemiPeriodMode(true);	// configure to count DURATION of HIGH pulses
		m_ping.set(true);					// disable measurement
		setEnabled(true);					// enable the Lidar at startup
	}
	
	/**
	 * Turn the Lidar on or off - i.e. start or stop sending lidar pulises
	 * 
	 * passing true will enable the lidar - this will pull the m_ping line 
	 * low and as long as the line is held low, the Lidar will return 
	 * pulses with the distance reflected in the pulse width.  
	 */
	public void setEnabled(boolean enabled)
	{
		if(enabled)
		{
			m_ping.set(false);
		}
		else
		{
			m_ping.set(true);
		}
		
		m_counter.reset();
		m_enabled = enabled;
	}
	
	/**
	 * Is the ultrasonic enabled
	 *
	 * @return true if the ultrasonic is enabled
	 */
	public boolean isEnabled() {
		return m_enabled;
	}

	/**
	 * Check if there is a valid range measurement. The ranges are accumulated in
	 * a counter that will increment on each edge of the echo (return) signal. If
	 * the count is not at least 2, then the range has not yet been measured, and
	 * is invalid.
	 *
	 * @return true if the range is valid
	 */
	public boolean isRangeValid() {
		return m_counter.get() > 1;
	}

	/**
	 * Gets the distance based on the last lidar pulse.  The value is returned
	 * in centimeters.
	 *   
	 * - You must have called @link{Lidar.enable()} before getting distance.
	 * 
	 * @return distance in centimeters to the target
	 */
	public double getDistanceCentimeters()
	{
		if(m_enabled)
		{
			// return the distance in centimeters
			return m_counter.getPeriod() * kConversionFactorCentimeters;
		}
		else
		{
			return -1.0;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setPIDSourceType(PIDSourceType pidSource) 
	{
		if (!pidSource.equals(PIDSourceType.kDisplacement)) 
		{
			throw new IllegalArgumentException("Only displacement PID is allowed for Lidar.");
		}
		m_pidSource = pidSource;
	}

	/**
	 * {@inheritDoc}
	 */
	public PIDSourceType getPIDSourceType() 
	{
		return m_pidSource;
	}

	/**
	 * Get the range in the current DistanceUnit for the PIDSource base object.
	 *
	 * @return The range in DistanceUnit
	 */
	public double pidGet() 
	{
		return getDistanceCentimeters();
	}
  
	/*
	 * Live Window code, only does anything if live window is activated.
	 */
	public String getSmartDashboardType() 
	{
		return "Ultrasonic";
	}

	private ITable m_table;

	/**
	 * {@inheritDoc}
	 */
	public void initTable(ITable subtable) 
	{
		m_table = subtable;
		updateTable();
	}

	/**
	 * {@inheritDoc}
	 */
	public ITable getTable() 
	{
		return m_table;
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateTable() 
	{
		if (m_table != null) 
		{
			m_table.putNumber("Value", getDistanceCentimeters());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void startLiveWindowMode() {}

	/**
	 * {@inheritDoc}
	 */
	public void stopLiveWindowMode() {}
}
