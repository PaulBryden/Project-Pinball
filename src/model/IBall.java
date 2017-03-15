package model;

import physics.Vect;

public interface IBall extends IGizmo {
	
	public Vect getVelo();

	public void setVelo(Vect v);

	public void moveForTime(double tickTime);

	public void setCentre(Vect v);

	public Vect getCentre();

	public double getRadius();

	void setRadius(double radius);

}
