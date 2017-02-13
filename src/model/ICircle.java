package model;

import physics.Vect;

public interface ICircle extends IGizmo {
	
	public double getRadius();

	public void setRadius(double radius);

	public Vect getCentre();

	public boolean isBall();

}
