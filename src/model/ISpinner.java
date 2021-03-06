package model;

import model.Spinner.Direction;
import physics.Angle;
import physics.Vect;

public interface ISpinner extends IGizmo {

	void moveForTime(double time);

	double getWidth();

	Vect getPivot();

	double getAngularVelocity();

	void setAngle(Angle angle);

	Angle getAngle();

	Vect getEndCentre1();

	Vect getEndCentre2();

	void setDirection(Direction direction);
	
	Direction getDirection();

	void toggleDirection();


}
