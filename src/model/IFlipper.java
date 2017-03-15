package model;

import physics.Angle;
import physics.Vect;

/**
 * 
 * @author David, Matt
 *
 */
public interface IFlipper extends IGizmo {

	Angle getAngle();
	
	void setAngle(Angle angle);

	double getAngularVelocity();

	void toggleOpen();
	
	Vect getPivot();
	
	Vect getEndCentre();
	
	double getWidth();

	void moveForTime(double tickTime);
	
	double timeUntilStatic();

}
