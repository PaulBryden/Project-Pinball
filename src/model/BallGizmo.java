package model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import physics.Vect;
import observer.IObservable;
import observer.IObserver;
public class BallGizmo implements IGizmo,IObservable{
	private Vect velocity;
	private double radius;
	private double[][] coordinates = new double[1][2];
	private Color colour;
	private IGizmoPhysics gizmoPhysics;
	private ArrayList<IGizmo> gizmoList= new ArrayList<>();
	private ActionEvent trigger;
	
	public BallGizmo(double radius, double x, double y, double xv, double yv, IGizmoPhysics physics){
		coordinates[1][1]= x;
		coordinates[1][2]=y;
		colour = Color.BLUE;
		velocity = new Vect(xv,yv);
		this.radius=radius;
		gizmoPhysics=physics;
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
	public double[][] getPoints() {
		// TODO Auto-generated method stub
		return coordinates;
	}
	
	@Override
	public void setPoints(double[][] points) {
		// TODO Auto-generated method stub
		 coordinates=points;
	}

	@Override
	public double getRadius() {
		// TODO Auto-generated method stub
		return 0;
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
		gizmoPhysics.moveGizmo(this,collisions);
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
	public void addEvent(ActionEvent e) {
		trigger=e;
		
	}


}
