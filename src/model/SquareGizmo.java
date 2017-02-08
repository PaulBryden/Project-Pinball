package model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import observer.IObservable;
import observer.IObserver;
import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class SquareGizmo implements IPolygon, IObservable{
	private Color colour;
	private ArrayList<LineSegment> lines;
	private ArrayList<Circle> circles;
	private ArrayList<IObserver> observerList = new ArrayList<>();
	private IAction triggerAction;
	private ArrayList<IGizmo> triggerList = new ArrayList<>();
	private Vect velocity;
	int ID;
	public SquareGizmo(double[][] points, double xv, double yv, int ID) throws Exception{
		this.ID=ID;
		colour = Color.BLUE;
		if(points.length!=4){
			throw new Exception("Error: Please ensure only 4 points are provided for this square");
		}
		for(int i=0; i<points.length-1;i++){
				lines.add(new LineSegment(points[i][0],points[i][1],points[i+1][0],points[i+1][1]));
		}
	}
	@Override
	public Vect getVelo() {
		// TODO Auto-generated method stub
		return velocity;
	}

	@Override
	public void setVelo(Vect v) {
		velocity=v;
		
	}


	@Override
	public void setPoints(double[][] points) {
		// TODO Auto-generated method stub
		lines = new ArrayList<LineSegment>();
		for(int i=0; i<points.length-1;i++){
			lines.add(new LineSegment(points[i][0],points[i][1],points[i+1][0],points[i+1][1]));
	}
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
		String serializedGizmo = getID()+" "+lines.get(0).p1().x()+" "+lines.get(0).p1().y()+" "+"\n";
		for(IGizmo gizmo : triggerList){
			serializedGizmo+="Connect "+getID()+" "+gizmo.getID()+"\n";
		}
		return serializedGizmo;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return "Square"+ID;
	}
	@Override
	public void moveForTime(double tickTime) {
		// TODO Auto-generated method stub
		
	}


}
