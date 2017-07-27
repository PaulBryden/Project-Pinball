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

	void setID(String id);

	void addTriggerAction(IAction action);

	void addGizmoToTrigger(IGizmo gizmo);

	void performActions();

	void onCollision(IBall ball);

	Vect getGridCoords();
	
	int getGridWidth();
	
	int getGridHeight();

	void setGridCoords(Vect coords);
	
	List<Vect> getExactCoords();
	
	/**
	 * 
	 * @return a list of all the cicle objects in the gizmo
	 */
	List<Circle> getAllCircles();

	/**
	 * 
	 * @return a list of all the lines in the gizmo
	 */
	List<LineSegment> getAllLineSegments();

	/**
	 * @returns a String that represents the current state of the ball 
	 * 	in the format: type id centreX centreY velocityX velovityY
	 */
	String serializeGizmo();

	String getID();

	void rotate(int steps);

	int getRotation();
	
	double getCoefficientOfReflection();

	void setCoefficientOfReflection(double value);
	
	void triggerConnectedGizmos();

	Set<IGizmo> getGizmosToTrigger();
	
	String getType();

}
