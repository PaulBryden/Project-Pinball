package model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public interface IGizmo {
	
	public Vect getVelo();
	public void setVelo(Vect v);
	public boolean isStatic();
	public Color getColour();
	public Color setColour();
	public void addTriggerAction(IAction action);
	public void addGizmoToTrigger(IGizmo gizmo);
	public void trigger(ArrayList<IGizmo> triggeredGizmoList);
	public ArrayList<IGizmo> getGizmosToTrigger();
	public CollisionDetails evalCollisions(double tickTime, GizmoList gizmoList);
	public void moveGizmo(CollisionDetails collisions);
	public void setPoints(double[][] points);
	public ArrayList<Circle> getAllCircles();
	public ArrayList<LineSegment> getAllLineSegments();
}
