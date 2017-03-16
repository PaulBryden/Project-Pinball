package model;

import physics.Angle;
import physics.Vect;

public interface ISpinner extends IGizmo {

	void moveForTime(double time);

	double getWidth();

	Vect getEndCentre();

	Vect getPivot();

	double getAngularVelocity();

	void setAngle(Angle angle);

	Angle getAngle();


}
