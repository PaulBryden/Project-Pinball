package model;

import java.awt.Color;
import java.util.List;
import java.util.Set;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

/**
 * 
 * @author Paul, David, Matt
 *
 */
public interface IGizmo {

	boolean isStatic();

	Color getColour();

	void setColour(Color colour);

	void addTriggerAction(IAction action);

	void addGizmoToTrigger(IGizmo gizmo);

	void performActions();

	void onCollision(IBall ball);

	Vect getGridCoords();
	
	int getGridWidth();
	
	int getGridHeight();

	void setGridCoords(Vect coords);
	
	List<Vect> getExactCoords();

	List<Circle> getAllCircles();

	List<LineSegment> getAllLineSegments();

	String serializeGizmo();

	String getID();

	void rotate(int steps);

	int getRotation();
	
	double getCoefficientOfReflection();

	void triggerConnectedGizmos();

	Set<IGizmo> getGizmosToTrigger();

}
