package model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import observer.IObservable;
import observer.IObserver;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class TriangleGizmo implements IPolygon, IObservable{
	private Color colour;
	private IGizmoPhysics gizmoPhysics;
	private ArrayList<LineSegment> lines;
	private ArrayList<Circle> circles;
	public TriangleGizmo(double[][] points, double xv, double yv,IGizmoPhysics physics) throws Exception{
		colour = Color.BLUE;
		gizmoPhysics=physics;
		if(points.length!=3){
			throw new Exception("Error: Please ensure only 3 points are provided for this triangle");
		}
		for(int i=0; i<points.length-1;i++){
				lines.add(new LineSegment(points[i][0],points[i][1],points[i+1][0],points[i+1][1]));
		}
	}
	@Override
	public Vect getVelo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVelo(Vect v) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Color getColour() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setPoints(double[][] points) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public CollisionDetails evalCollisions(double tickTime, GizmoList gizmoList) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void moveGizmo(CollisionDetails collisions) {
		notifyAllObservers();
		
	}
	@Override
	public void attach(IObserver obs) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notifyAllObservers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isStatic() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Color setColour() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addTriggerAction(IAction action) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addGizmoToTrigger(IGizmo gizmo) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<IGizmo> getGizmosToTrigger() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public float[][] getCoords() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setCoords(float[][] coords) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ArrayList<Circle> getAllCircles() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<LineSegment> getAllLineSegments() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void trigger(ArrayList<IGizmo> triggeredGizmoList) {
		// TODO Auto-generated method stub
		
	}

}
