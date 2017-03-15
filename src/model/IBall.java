package model;

import physics.Vect;

/**
 * 
 * @author Paul, David
 *
 */
public interface IBall extends IGizmo {
	
	Vect getVelo();

	void setVelo(Vect v);

	void moveForTime(double tickTime);

	void setCentre(Vect v);

	Vect getCentre();

	double getRadius();

	void setRadius(double radius);

}
