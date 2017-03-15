package model;

import physics.Vect;

/**
 * 
 * @author Paul
 *
 */
public interface ICircleGizmo extends IGizmo {

	double getRadius();

	Vect getCentre();

	void setRadius(double radius);

}
