package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class FlipperGizmo implements IFlipper{
	private Color colour;
	private IGizmoPhysics gizmoPhysics;
	private ArrayList<LineSegment> lines;
	private ArrayList<Circle> circles;
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
	public void setFlipperSpeed(float x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getFlipperSpeed() {
		// TODO Auto-generated method stub
		return 0;
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
	public boolean isStatic() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Color getColour() {
		// TODO Auto-generated method stub
		return null;
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
	public CollisionDetails evalCollisions(double tickTime, GizmoList gizmoList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void moveGizmo(CollisionDetails collisions) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPoints(double[][] points) {
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
	public void performActions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggerConnectedGizmos() {
		// TODO Auto-generated method stub
		
	}

	
	protected void triggerConnectedGizmos(List<IGizmo> visited){}

	protected void performActions(List<IGizmo> visited){}
}
