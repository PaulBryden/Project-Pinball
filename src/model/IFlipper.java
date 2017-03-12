package model;

import java.util.List;

import physics.Angle;
import physics.LineSegment;
import physics.Vect;

public interface IFlipper extends IGizmo {

	public Angle getAngle();
	
	public void setAngle(Angle angle);

	public double getAngularVelocity();

	public void toggleOpen();
	
	public Vect getPivot();
	
	public Vect getEndCentre();
	
	public double getWidth();

	public void moveForTime(double tickTime);
	
	public double timeUntilStatic();

}
