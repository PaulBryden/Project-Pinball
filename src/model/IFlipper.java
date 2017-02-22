package model;

import physics.Angle;
import physics.Vect;

public interface IFlipper extends IGizmo {

	public Angle getAngle();

	public double getAngularVelocity();

	public void toggleOpen();
	
	public Vect getPivot();
	
	public Vect getEndCentre();
	
	public double getWidth();

	public void moveForTime(double tickTime);

}
