package model;

import java.awt.Color;
import java.awt.event.ActionEvent;

import observer.IObservable;
import observer.IObserver;
import physics.Vect;

public class PolygonGizmo implements IGizmo, IObservable{
	private Vect velocity;
	private double radius;
	private double[][] coordinates = new double[1][2];
	private Color colour;
	private IGizmoPhysics gizmoPhysics;
	private ActionEvent trigger;
	public PolygonGizmo(double[][] points, double xv, double yv,IGizmoPhysics physics){
		coordinates=points;
		colour = Color.BLUE;
		velocity = new Vect(xv,yv);
		radius = -1;
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
		return null;
	}

	@Override
	public double getRadius() {
		// TODO Auto-generated method stub
		return -1;
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
	public void addEvent(ActionEvent e) {
		trigger=e;
		
	}

}
