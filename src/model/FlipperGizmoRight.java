package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import observer.IObservable;
import observer.IObserver;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class FlipperGizmoRight implements IFlipper, IObservable{
	private Color colour;
	private ArrayList<LineSegment> lines;
	private ArrayList<Circle> circles;
	private ArrayList<IObserver> observerList = new ArrayList<>();
	private IAction triggerAction;
	private ArrayList<IGizmo> triggerList = new ArrayList<>();
	private float flipperSpeed;
	private float rotation;
	public FlipperGizmoRight(double[][] points, double xv, double yv, int ID) throws Exception{

		if(points.length!=4){
			throw new Exception("Error: Please ensure 4 points are provided for this flipper");
		}
		for(int i=0; i<points.length-1;i++){
				lines.add(new LineSegment(points[i][0],points[i][1],points[i+1][0],points[i+1][1]));
		}
		//Implement rounded edges after initial testing is complete.
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
	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void moveForTime(double tickTime) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void rotate(float angle) {
		// TODO Auto-generated method stub
		rotation+=angle;
	}



	@Override
	public float getRotation() {
		// TODO Auto-generated method stub
		return rotation;
	}






	@Override
	public void setCoords(Vect coords) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Vect getCoords() {
		// TODO Auto-generated method stub
		return null;
	}

}

