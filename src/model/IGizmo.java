package model;

import java.awt.Color;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public interface IGizmo extends ITrigger {

	public boolean isStatic();

	public Color getColour();

	public void setColour(Color colour);

	public void addTriggerAction(IAction action);

	public void addGizmoToTrigger(IGizmo gizmo);

	public void performActions();

	public void onCollision(IBall ball);

	public Vect getGridCoords();

	public void setGridCoords(Vect coords);
	
	public List<Vect> getExactCoords();

	public List<Circle> getAllCircles();

	public List<LineSegment> getAllLineSegments();

	public String serializeGizmo();

	public String getID();

	public void rotate(int steps);

	public int getRotation();
	
	public double getCoefficientOfReflection();

}
