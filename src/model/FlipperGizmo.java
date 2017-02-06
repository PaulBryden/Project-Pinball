package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import observer.IObservable;
import observer.IObserver;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class FlipperGizmo implements IFlipper, IObservable{
	private Color colour;
	private IGizmoPhysics gizmoPhysics;
	private ArrayList<LineSegment> lines;
	private ArrayList<Circle> circles;
	private ArrayList<IObserver> observerList = new ArrayList<>();
	private IAction triggerAction;
	private ArrayList<IGizmo> triggerList = new ArrayList<>();
	private float flipperSpeed;
	public FlipperGizmo(){
		
	}
	@Override
	public Vect getVelo() {
		// TODO Auto-generated method stub
		return gizmoPhysics.getVelocity();
	}

	@Override
	public void setVelo(Vect v) {
		gizmoPhysics.setVelocity(v);
		
	}


	@Override
	public void setPoints(double[][] points) {
		// TODO Auto-generated method stub
		//NEED TO EVALUATE THIS MORE CLOSELY
	}



	@Override
	public Color getColour() {
		// TODO Auto-generated method stub
		return colour;
	}

	@Override
	public CollisionDetails evalCollisions(double tickTime, GizmoList gizmoList) {
		
		//This method needs thought through with the physics guys.
		return null;
	}

	@Override
	public void moveGizmo(CollisionDetails collisions) {
		gizmoPhysics.moveGizmoForTime(this);
		// Move all co-ordinates according to ballGizmo
		notifyAllObservers();
	}

	@Override
	public void attach(IObserver obs) {
		observerList.add(obs);
	}

	@Override
	public void notifyAllObservers() {
		for(IObserver observer : observerList){
			observer.notify();
		}
		
	}

	@Override
	public boolean isStatic() {
		// Ball will always be a movable object
		return false;
	}

	@Override
	public void setColour(Color colour) {
		// TODO Auto-generated method stub
		this.colour=colour;
	}

	@Override
	public void addTriggerAction(IAction action) {
		this.triggerAction=action;
		
	}

	@Override
	public void addGizmoToTrigger(IGizmo gizmo) {
		triggerList.add(gizmo);
		
	}

	@Override
	public ArrayList<IGizmo> getGizmosToTrigger() {
		
		return triggerList;
	}
	@Override
	public float[][] getCoords() {
		// NEED TO RETHINK THIS. THESE ARE UNNECESSARY
		return null;
	}
	@Override
	public void setCoords(float[][] coords) {
		// NEED TO RETHINK THIS. THESE ARE UNNECESSARY

		
	}
	@Override
	public ArrayList<Circle> getAllCircles() {
		// TODO Auto-generated method stub
		return circles;
	}
	@Override
	public ArrayList<LineSegment> getAllLineSegments() {
		// TODO Auto-generated method stub
		return lines;
	}

	protected void triggerConnectedGizmos(List<IGizmo> visited){}
	protected void performActions(List<IGizmo> visited){}
	@Override
	public void performActions() {
		triggerAction.performAction();
	}
	@Override
	public void triggerConnectedGizmos() {
		for(IGizmo gizmo : triggerList){
			gizmo.performActions();
		}
		
	}
	@Override
	public String serializeGizmo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setFlipperSpeed(float x) {
		flipperSpeed=x;
	}
	@Override
	public float getFlipperSpeed() {
		// TODO Auto-generated method stub
		return flipperSpeed;
	}

}

