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
 *
 */
public class Ultrasonic extends SensorBase implements PIDSource, LiveWindowSendable{
	private Counter m_counter;
	
	protected PIDSourceType m_pidSource = PIDSourceType.kDisplacement;

	
	/**
	 * Constants for the class
	 */
	private static final int kConversionFactorCentimeters = 100000; //TODO: update for Ultrasonic
	
	/**
	 * Create a Ultrasonic object.  
	 */
	public Ultrasonic(int channel)
	{
		m_counter = new Counter(channel);
		
		initialize();
	}
	
	/**
	 * Common initializaiton of the Ultrasonic
	 * - sets the counter to count the duration of high going pulses
	 * - sets the Ultrasonic to be enabled (Calls setEnabled(true))
	 */
	private synchronized void initialize()
	{
		m_counter.setSemiPeriodMode(true);	// configure to count DURATION of HIGH pulses
	}
	
	/**
	 * Resets the internal counter  
	 */
	public void reset()
	{
		m_counter.reset();
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
	 * Gets the distance based on the last pulse.  The value is returned
	 * in centimeters.
	 *   
	 * @return distance in centimeters to the target
	 */
	public double getDistanceCentimeters()
	{
		return m_counter.getPeriod() * kConversionFactorCentimeters;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void setPIDSourceType(PIDSourceType pidSource) 
	{
		if (!pidSource.equals(PIDSourceType.kDisplacement)) 
		{
			throw new IllegalArgumentException("Only displacement PID is allowed for Ultrasonic.");
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
