package model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;
import observer.IObservable;
import observer.IObserver;
public class CircleGizmo implements ICircle,IObservable{
	private double radius;
	private Color colour;
	private Circle physicsCircle;
	private ArrayList<IObserver> observerList = new ArrayList<>();
	private IAction triggerAction;
	private ArrayList<IGizmo> triggerList = new ArrayList<>();
	private int ID;
	private float rotation;
	private Vect coords;
	public CircleGizmo(double radius, double x, double y, int ID){
		this.colour = Color.BLUE;
		this.radius=radius;
		this.physicsCircle=new Circle(x+0.5,y+0.5,radius);
		this.coords = new Vect(x,y);
		this.ID=ID;
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
	public float getRadius() {
		// TODO Auto-generated method stub
		return (float)physicsCircle.getRadius();
	}

	@Override
	public void setRadius(float radius) {
		//Do we need this?
		
	}

	@Override
	public void setCentre(float x, float y) {
		physicsCircle = new Circle(x,y,physicsCircle.getRadius());
		
	}

	@Override
	public Vect getCentre(float x, float y) {
		// TODO Auto-generated method stub
		return physicsCircle.getCenter();
	}

	@Override
	public boolean isBall() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ArrayList<Circle> getAllCircles() {
		// TODO Auto-generated method stub
		ArrayList<Circle> circleList = new ArrayList<>();
		circleList.add(physicsCircle);
		return circleList;
	}

	@Override
	public ArrayList<LineSegment> getAllLineSegments() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void performActions() {
		triggerAction.performAction();
		
		
	}
	protected void performActions(List<IGizmo> visited){
		//Needs rethought
	}

	@Override
	public void triggerConnectedGizmos() {
		for(IGizmo gizmo : triggerList){
			gizmo.performActions();
		}
		
	}
	protected void triggerConnectedGizmos(List<IGizmo> visited){}

	@Override
	public String serializeGizmo() {
		String serializedGizmo = "Circle"+getID()+" "+physicsCircle.getCenter().x()+" "+physicsCircle.getCenter().y()+" "+"\n";
		for(IGizmo gizmo : triggerList){
			serializedGizmo+="Connect "+getID()+" "+gizmo.getID()+"\n";
		}
		return serializedGizmo;
	}

	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}

	@Override
	public void rotate(float angle) {
		// TODO Auto-generated method stub
		rotation+=angle;
	}

	@Override
	public float getRotation() {
		return rotation;
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vect getCoords() {
		return coords;
	}

	@Override
	public void setCoords(Vect coords) {
		this.physicsCircle=new Circle(coords,radius);
		this.coords = coords;

	}


	@Override
	public void onCollision(IBall ball) {
		// TODO Auto-generated method stub
		
	}



}
