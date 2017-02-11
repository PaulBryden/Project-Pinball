package model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public interface IGizmo {
	

	public boolean isStatic();
	public Color getColour();
	public void setColour(Color colour);
	public void addTriggerAction(IAction action);
	public void addGizmoToTrigger(IGizmo gizmo);
	public void performActions();
	public void onCollision(IBall ball);
	public void triggerConnectedGizmos();
	public ArrayList<IGizmo> getGizmosToTrigger();
	public Vect getCoords();
	public void setCoords(Vect coords);
	public ArrayList<Circle> getAllCircles();
	public ArrayList<LineSegment> getAllLineSegments();
	public String serializeGizmo();
	public int getID();
	public void rotate(float angle);
	public float getRotation();
	
}
