package model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

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
	
	
	public BallGizmo(double radius, double x, double y, double xv, double yv){
		colour = Color.BLUE;
		this.radius=radius;
		this.physicsCircle=new Circle(x,y,radius);
		gizmoPhysics=new BallPhysics(xv, yv);
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
	public void setPoints(double[][] points) {
		// TODO Auto-generated method stub
		physicsCircle = new Circle(new Vect(points[0][0],points[0][1]),radius);
	}



	@Override
	public Color getColour() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CollisionDetails evalCollisions(double tickTime, GizmoList gizmoList) {
		
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
	public float getRadius() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRadius(float radius) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCentre(float x, float y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float[] getCentre(float x, float y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBall() {
		// TODO Auto-generated method stub
		return false;
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
