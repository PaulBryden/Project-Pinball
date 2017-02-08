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
public class BallGizmo implements ICircle,IObservable{
	private double radius;
	private Color colour;
	private IGizmoPhysics gizmoPhysics;
	private Circle physicsCircle;
	private ArrayList<IGizmo> gizmoList= new ArrayList<>();
	private ArrayList<IObserver> observerList = new ArrayList<>();
	private IAction triggerAction;
	private ArrayList<IGizmo> triggerList = new ArrayList<>();
	private int ID;
	public BallGizmo(double radius, double x, double y, double xv, double yv, Color colour,int ID){
		this.colour = colour;
		this.radius=radius;
		this.physicsCircle=new Circle(x,y,radius);
		this.gizmoPhysics=new BallPhysics(xv, yv);
		this.ID=ID;
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
		physicsCircle = new Circle(new Vect(points[0][0],points[0][1]),radius);
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
		String serializedGizmo = getID()+" "+physicsCircle.getCenter().x()+" "+physicsCircle.getCenter().y()+" "+gizmoPhysics.getVelocity().x()+" "+gizmoPhysics.getVelocity().y()+"\n";
		for(IGizmo gizmo : triggerList){
			serializedGizmo+="Connect "+getID()+" "+gizmo.getID()+"\n";
		}
		return serializedGizmo;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "Ball"+ID;
	}



}
