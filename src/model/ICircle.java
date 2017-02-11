package model;

import physics.Vect;

public interface ICircle extends IGizmo{
	public float getRadius();
	public void setRadius(float radius);
	public void setCentre(float x, float y);
	public Vect getCentre(float x, float y);
	public boolean isBall();
	
}
