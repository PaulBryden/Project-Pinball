package model;

import java.awt.Color;
import java.awt.event.ActionEvent;

import physics.Circle;
import physics.Vect;

public interface IGizmo {
	
	public Vect getVelo();
	public void setVelo(Vect v);
	public double[][] getPoints();
	public double getRadius();
	public Color getColour();
	public CollisionDetails evalCollisions(double tickTime, GizmoList gizmoList);
	public void moveGizmo(CollisionDetails collisions);
	public void setPoints(double[][] points);
	public void addEvent(ActionEvent e);

}
