// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc3824.BetaBot.subsystems;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.usfirst.frc3824.BetaBot.Constants;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Targets extends Subsystem
{
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	NetworkTable m_contoursReport;
	NetworkTable m_imageReport;
	NetworkTable m_frameRateReport;
	NetworkTable m_linesReport;
	List<Line>   m_lines;
	List<Target> m_targets;

	// Used to clear the target arrays before reading target information
	double[] m_defaultValue = new double[0];

	public class TargetInfo
	{
		public int    offsetFromTargetX;	// pixels from targeting point in the X direction
		public int    offsetFromTargetY;	// pixels from targeting point in the Y direction
		public double distanceToTarget;		// robot's distance to the target
		public double bottomLineLength;		// length of the bottom line of target - relative to horizontal
		public double bottomLineAngle;		// angle of the bottom line of target - relative to horizontal

		public int    centerX;
		public int    centerY;
		public int    height;
		public int    width;
		public int    area;
		public double rotationAngleOffset;	// robot's rotation offset from perfectly lined up with target
		public double positionAngleOffset;	// robot's position offset on field from the centerline of the target

		TargetInfo()
		{
			offsetFromTargetX   = 0;
			offsetFromTargetY   = 0;
			distanceToTarget    = 0;
			bottomLineLength    = 0;
			bottomLineAngle     = 0;

			centerX             = 0;
			centerY             = 0;
			height              = 0;
			width               = 0;
			area                = 0;
			rotationAngleOffset = 0;
			positionAngleOffset = 0;
		}
	}

	public class Point
	{
		private double x;
		private double y;

		public Point()
		{
			this.x = -9999;
			this.y = -9999;
		}

		public Point(double x, double y)
		{
			this.x = x;
			this.y = y;
		}

		public String toString()
		{
			return "(" + this.x + "," + this.y + ")";
		}
	}

	public class Target
	{
		public int centerX;
		public int centerY;
		public int height;
		public int width;
		public int area;
	}

	public static class TargetCompare
	{
		public static Comparator<Target> XComparator = new Comparator<Target>()
		{
			public int compare(Target t1, Target t2)
			{
				// ascending order: line1_p1x > line2_p1x returns +1
				if (t1.centerX > t2.centerX)
					return 1;
				else if (t2.centerX > t1.centerX)
					return -1;
				else
					return 0;
			}
		};

		private static Comparator<Target> AreaComparatorDescending = new Comparator<Target>()
		{
			public int compare(Target t1, Target t2)
			{
				// descending order: target1.area < target2.area returns +1
				if (t1.area < t2.area)
					return 1;
				else if (t2.area < t1.area)
					return -1;
				else
					return 0;
			}
		};

		public static Comparator<Target> AreaComparatorAscending = new Comparator<Target>()
		{
			public int compare(Target t1, Target t2)
			{
				// ascending order: target1.area > target2.area returns +1
				if (t1.area > t2.area)
					return 1;
				else if (t2.area > t1.area)
					return -1;
				else
					return 0;
			}
		};
	}

	public class Line
	{
		public Point  p1;
		public Point  p2;
		public double angle;
		public double length;
		public double compareAngle;

		public Line()
		{
			p1           = new Point();
			p2           = new Point();
			angle        = -9999;
			length       = -9999;
			compareAngle = -9999;
		}
	}

	public static class LineCompare
	{
		public static Comparator<Line> LineXComparator = new Comparator<Line>()
		{
			public int compare(Line line1, Line line2)
			{
				double line1_p1x = line1.p1.x;
				double line2_p1x = line2.p1.x;

				// ascending order: line1_p1x > line2_p1x returns +1
				if (line1_p1x > line2_p1x)
					return 1;
				else if (line2_p1x > line1_p1x)
					return -1;
				else
					return 0;
			}
		};

		public static Comparator<Line> LineAngleComparator = new Comparator<Line>()
		{
			public int compare(Line line1, Line line2)
			{
				// ascending order: line1_p1x > line2_p1x returns +1
				if (line1.compareAngle > line2.compareAngle)
					return 1;
				else if (line2.compareAngle > line1.compareAngle)
					return -1;
				else
					return 0;
			}
		};
	}

	public void initDefaultCommand()
	{
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	}

	public Targets()
	{
		m_contoursReport  = NetworkTable.getTable("GRIP/cameraTargets");
		m_frameRateReport = NetworkTable.getTable("GRIP");
		m_linesReport     = NetworkTable.getTable("GRIP/cameraTargetLines");
		m_lines           = null;
		m_targets         = null;
	}

	public TargetInfo getTargetingInfo()
	{
		// if the image is positioned to the right, the robot is too far left.
		// so this return value is flipped
		double     positionFromOnTargetXNormalized = 0.0;
		Target     largestTarget;
		TargetInfo targetInfo = null;
		Line       foundTargetLine;

		// pull all the GRIP data into data structures (m_lines and m_targets)
		importTargetData();

		// Ensure the target and lines objects exist
		if ((m_targets != null) && (m_lines != null))
		{
			// if there are any targets, then process them, otherwise just exit
			if (m_targets.size() > 0)
			{
				targetInfo = new TargetInfo();

				m_targets.sort(TargetCompare.AreaComparatorDescending);	// sort from largest to smallest
				largestTarget      = m_targets.get(0);		            // get the largest target
				targetInfo.centerX = largestTarget.centerX;             // copy it into the TargetInfo object
				targetInfo.centerY = largestTarget.centerY;
				targetInfo.height  = largestTarget.height;
				targetInfo.width   = largestTarget.width;
				targetInfo.area    = largestTarget.area;

				// find only the "horizontal lines"
				filterToHorizontalLines();

				// find the target associated with the line and assign the center
				Iterator<Line> lineIterator = m_lines.iterator();
				while (lineIterator.hasNext())
				{
					Line tmpLine = lineIterator.next();
					
					// remove any horizontal lines whose X midpoint is more than 10 pixels from the
					// largest target center
					if (Math.abs(largestTarget.centerX - (Math.abs(tmpLine.p2.x + tmpLine.p1.x) / 2.0)) > 10)
					{
						lineIterator.remove();
					}
				}

				// There should be exactly two lines left, but lets verify and not assume
				m_lines.sort(LineCompare.LineXComparator);
				if (m_lines.size() > 0)
				{
					foundTargetLine = null;

					if (m_lines.size() == 1)
						foundTargetLine = m_lines.get(0);
					else if (m_lines.size() == 2)
					{
						if (m_lines.get(0).length > m_lines.get(1).length)
							foundTargetLine = m_lines.get(0);
						else
							foundTargetLine = m_lines.get(1);
					}
					else
					{
						targetInfo = null;
					}

					if (targetInfo != null)
					{
						targetInfo.bottomLineAngle  = foundTargetLine.angle;
						targetInfo.bottomLineLength = foundTargetLine.length;

						// --------------------------------------------
						// calculate robot's distance from the tower based on
						// line size and angle
						// ---
						targetInfo.distanceToTarget = calculateDistanceToTargetWithLine(foundTargetLine.length);

						// --------------------------------------------
						// calculate rotation offset of robot
						// ---
						// calculate offset from "OnTarget" in pixels
						targetInfo.offsetFromTargetX = targetingPositionX(largestTarget, targetInfo.distanceToTarget, targetInfo.bottomLineAngle) - largestTarget.centerX;
						targetInfo.offsetFromTargetY = targetingPositionY(largestTarget, targetInfo.distanceToTarget, targetInfo.bottomLineAngle) - largestTarget.centerY;

						// convert the offset in pixels to a normalized range where -1 is one half an
						// image width to the left and 1 is one half an image width to the right.
						positionFromOnTargetXNormalized = (-targetInfo.offsetFromTargetX) / (Constants.IMAGE_WIDTH / 2.0);

						targetInfo.rotationAngleOffset = positionFromOnTargetXNormalized * (Constants.CAM_FOV / 2.0);

						// --------------------------------------------
						// calculate robot's offset from centerline of target
						// ---
						targetInfo.positionAngleOffset = calculatePositionAngleOffsetWithLine(foundTargetLine.angle);
					}
				}
				else
				{
					targetInfo = null;
				}
			}
		}

		// Update the smart dash board with the target parameters
		updateSmartDashboard(targetInfo);

		// Return the targeting information
		return targetInfo;
	}

	/**
	 * ***********************************************************************
	 * Determine if the GRIP image processing pipeline is running on the
	 * RaspberryPi. We assume that if the frame rate is non-zero, then the
	 * pipeline is running. If the frame rate is 0, it is not running
	 */
	public boolean isImageProcessingRunning()
	{
		return (m_frameRateReport. getNumber("cameraFrameRate", 0.0) > 0);
	}

	public int getFrameRate()
	{
		return (int) m_frameRateReport.getNumber("cameraFrameRate", 0.0);
	}
	/**
	 * ***********************************************************************
	 * Display data values on the smart dash board
	 */
	public void updateSmartDashboard(TargetInfo target)
	{
		SmartDashboard.putBoolean("Image Processing Running", isImageProcessingRunning());
		SmartDashboard.putNumber("Targets FrameRate", m_frameRateReport.getNumber("cameraFrameRate", 0.0));

		SmartDashboard.putBoolean("Target Found", (target != null) ? true : false);

		if (target != null)
		{
			SmartDashboard.putNumber("TargetPixel_X",      target.centerX);
			SmartDashboard.putNumber("TargetPixel_Y",      target.centerY);
//			SmartDashboard.putNumber("TargetHeight",       target.height);
			SmartDashboard.putNumber("TargetOffset_X",     target.offsetFromTargetX);
			SmartDashboard.putNumber("TargetOffset_Y",     target.offsetFromTargetY);
			SmartDashboard.putNumber("Target Line Angle",  target.bottomLineAngle);
			SmartDashboard.putNumber("Target Line Length", target.bottomLineLength);
		}
		else
		{
			SmartDashboard.putNumber("TargetPixel_X",      -9999);
			SmartDashboard.putNumber("TargetPixel_Y",      -9999);
//			SmartDashboard.putNumber("TargetHeight",       -9999);
			SmartDashboard.putNumber("TargetOffset_X",     -9999);
			SmartDashboard.putNumber("TargetOffset_Y",     -9999);
			SmartDashboard.putNumber("Target Line Angle",  -9999);
			SmartDashboard.putNumber("Target Line Length", -9999);
		}
	}

	/**
	 * Method to import the image processing data into internal objects:
	 * m_lines - a List<> of lines from the image processing
	 * m_targets - a List<> of contour bounding boxes
	 */
	private void importTargetData()
	{
		// Import the lines - sorted by X left to right
		Line line;

		try
		{
			double[] angle  = m_linesReport.getNumberArray("angle",  m_defaultValue);
			double[] length = m_linesReport.getNumberArray("length", m_defaultValue);
			double[] x1     = m_linesReport.getNumberArray("x1",     m_defaultValue);
			double[] y1     = m_linesReport.getNumberArray("y1",     m_defaultValue);
			double[] x2     = m_linesReport.getNumberArray("x2",     m_defaultValue);
			double[] y2     = m_linesReport.getNumberArray("y2",     m_defaultValue);

			if (angle.length == 0)
			{
				m_lines = null;
				m_targets = null;
				return;
			}

			m_lines = new ArrayList<Line>();

			for (int i = 0; i < angle.length; i++)
			{
				line = new Line();

				line.angle  = angle[i];
				line.length = length[i];
				line.p1.x   = x1[i];
				line.p1.y   = y1[i];
				line.p2.x   = x2[i];
				line.p2.y   = y2[i];

				m_lines.add(line);
			}
		}
		catch (Exception e)
		{
			m_lines = null;
			m_targets = null;
			System.out.println(e);
		}

		// Import the targets
		Target target;

		try
		{
			double[] centerXs = m_contoursReport.getNumberArray("centerX", m_defaultValue);
			double[] centerYs = m_contoursReport.getNumberArray("centerY", m_defaultValue);
			double[] areas    = m_contoursReport.getNumberArray("area",    m_defaultValue);
			double[] widths   = m_contoursReport.getNumberArray("width",   m_defaultValue);
			double[] heights  = m_contoursReport.getNumberArray("height",  m_defaultValue);

			if (areas.length == 0)
			{
				m_lines = null;
				m_targets = null;
				return;
			}
			
			m_targets = new ArrayList<Target>();

			for (int i = 0; i < areas.length; i++)
			{
				target = new Target();

				target.centerX = (int) centerXs[i];
				target.centerY = (int) centerYs[i];
				target.area    = (int) areas[i];
				target.width   = (int) widths[i];
				target.height  = (int) heights[i];

				m_targets.add(target);
			}
		}
		catch (Exception e)
		{
			m_lines = null;
			m_targets = null;
			System.out.println(e);
		}
	}

	/**
	 * Method to remove any line with an angle of 45 degrees or less from vertical
	 */
	private void filterToHorizontalLines()
	{
		double angle;
		Line   line1;
		Line   line2;

		if (m_lines == null)
			return;

		// remove "vertical" lines
		Iterator<Line> linesIterator = m_lines.iterator();
		while (linesIterator.hasNext())
		{
			angle = Math.abs(linesIterator.next().angle);
			if ((angle >= 45.0) && (angle <= 135.0))
			{
				linesIterator.remove();
			}
		}

		// sort the lines from smallest angle to largest angle
		m_lines.sort(LineCompare.LineAngleComparator);

		// iterate over the lines. If two lines are within 5 degrees of each
		// other then we'll call them a target pair and we only want the
		// longest line of the target pair.
		for (int i = 0; i < m_lines.size() - 1;)
		{
			line1 = m_lines.get(i);
			line2 = m_lines.get(i + 1);

			if (Math.abs(line1.compareAngle - line2.compareAngle) < 5)
			{
				if (line1.length < line2.length)
				{
					// delete line
					m_lines.remove(line1);
				}
				else
				{
					// delete line2
					m_lines.remove(line2);
				}
			}
			else
				i++;
		}
	}

	/**
	 * Method to return the distance from the largest target based on the target area
	 */
	public double getDistanceFromLargestTarget()
	{
		TargetInfo found_target;
		double area;
		double distanceFromTarget = -1.0;

		found_target = getTargetingInfo();

		// Ensure a target was identified
		if (found_target != null)
		{
			// get the area of the selected target
			area = found_target.area;

			// y = 9E-06x2 - 0.1589x + 583.49
			distanceFromTarget = (9e-6 * area * area) - (0.1589 * area) + 583.49;
		}

		// Return the distance from the target
		return distanceFromTarget;
	}

	/**
	 * Method to return the position offset on the field from center of a target based on
	 * the angle of the horizontal line
	 * 
	 * Use line fit:
	 *   line angle versus actual angle
	 */
	public double calculatePositionAngleOffsetWithLine(double lineAngle)
	{
		double angleOffset;

		if (lineAngle < 75)
		{
			angleOffset = 0 - lineAngle; // left of center is negative angle
		}
		else
		{
			angleOffset = 0 - (lineAngle - 360.0); // right of center is positive angle
		}

		return angleOffset;
	}

	/**
	 * Method to return the distance from the specified target based on the target line length
	 * and angle of the line
	 * 
	 * Use line fit:
	 * 	line length versus target distance
	 */
	public double calculateDistanceToTargetWithLine(double length)
	{
		double distanceFromTarget;

		// Determine the distance from the target
		// y = Ax^2 + Bx + C
		distanceFromTarget = (Constants.DISTANCE_A * length * length) + 
				             (Constants.DISTANCE_B * length) + 
				              Constants.DISTANCE_C;

		// Return the distance from the target
		return distanceFromTarget;
	}

	/**
	 * Method to return the desired target X position based on the distance and
	 * angle to the target
	 */
	int targetingPositionX(Target foundTarget, double distanceToTarget, double lineAngle)
	{
		int targetX;

		// Get the image X target position
		targetX = Constants.IMAGE_ON_TARGET_X_POSITION;
		
//		SmartDashboard.putNumber("Target Base X", targetX);

		// Calculate the X pixel offset
		// -15 -> -2
		// -10 -> -1
		//  -5 ->  0
		//   0 ->  0
		//   5 ->  0
		//  10 ->  1
		//  15 ->  2 
		if (Math.abs(lineAngle) > 90.0)
			if (lineAngle > 0)
				lineAngle -= 180.0;
			else
				lineAngle += 180.0;
		
		int Xoffset = (int) (lineAngle / 2.5);

		// Modify the offset based on the angle
		targetX -= Xoffset;
		
//		SmartDashboard.putNumber("Target Offset X", Xoffset);
//		SmartDashboard.putNumber("Target Aim X", targetX);

		return targetX;
	}

	/**
	 * Method to return the desired targetY position based on the distance and
	 * angle to the target
	 */
	int targetingPositionY(Target foundTarget, double distanceToTarget, double lineAngle)
	{
		int targetY;

		// Ensure found target
		if (foundTarget == null)
			return -1;

		// Determine the image target Y pixel
		// y = Ax^2 + Bx + C
		targetY = (int) ((Constants.IMAGE_Y_A * distanceToTarget * distanceToTarget) + 
				         (Constants.IMAGE_Y_B * distanceToTarget) +
				          Constants.IMAGE_Y_C);

//		SmartDashboard.putNumber("Target Base Y", targetY);

		// Calculate the Y pixel offset
		// -15 -> 2
		// -10 -> 1
		//  -5 -> 0
		//   0 -> 0
		//   5 -> 0
		//  10 -> 1
		//  15 -> 2  
		if (Math.abs(lineAngle) > 90.0)
			if (lineAngle > 0)
				lineAngle -= 180.0;
			else
				lineAngle += 180.0;
		
//		SmartDashboard.putNumber("ANGLE", lineAngle);

		int Yoffset = (int) (Math.abs(lineAngle) / 5.0);

		// Modify the offset based on the angle
		targetY += Yoffset;
		
		// Additional offset to compensate for X position
		targetY += Constants.IMAGE_ON_TARGET_Y_OFFSET;
		
//		SmartDashboard.putNumber("Target Offset Y", Yoffset);
//		SmartDashboard.putNumber("Target Aim Y", targetY);

		// Return the Y target pixel
		return targetY;
	}
}
