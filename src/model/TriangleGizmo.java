package model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import com.sun.prism.image.Coords;

import observer.IObservable;
import observer.IObserver;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class TriangleGizmo implements IGizmo, IObservable{
	private Color colour;
	private ArrayList<LineSegment> lines;
	private ArrayList<Circle> circles;
	private ArrayList<IObserver> observerList = new ArrayList<>();
	private IAction triggerAction;
	private ArrayList<IGizmo> triggerList = new ArrayList<>();
	private int ID;
	private float rotation;
	private Vect coords;
	public TriangleGizmo(int ID, int x, int y) throws Exception{
		rotation=0;
		this.ID=ID;
		colour = Color.BLUE;
		coords = new Vect(x,y);
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
		String serializedGizmo = "Triangle"+getID()+" "+lines.get(0).p1().x()+" "+lines.get(0).p1().y()+" "+"\n";
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
		rotation+=angle;
		//Need to manage movement of lines/circles here too!!

	}


	@Override
	public float getRotation() {
		// TODO Auto-generated method stub
		return rotation;
	}


	@Override
	public void setCoords(Vect coords) {
		// TODO Auto-generated method stub
		this.coords=coords;
		circles = new ArrayList<>();
		lines = new ArrayList<>();
		circles.add(new Circle(coords.x(), coords.y(), 0));
		circles.add(new Circle(coords.x() + 1, coords.y(), 0));
		circles.add(new Circle(coords.x(), coords.y() + 1, 0));
		lines.add(new LineSegment(coords.x(), coords.y(), coords.x() + 1, coords.y()));
		lines.add(new LineSegment(coords.x(), coords.y() + 1, coords.x() + 1, coords.y()));
		lines.add(new LineSegment(coords.x(), coords.y(), coords.x(), coords.y() + 1));
	}



	@Override
	public Vect getCoords() {
		// TODO Auto-generated method stub
		return coords;
	}





	@Override
	public void onCollision(IBall ball) {
		// TODO Auto-generated method stub
		
	}

}
