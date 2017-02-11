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
	private ArrayList<LineSegment> lines;
	private ArrayList<Circle> circles;
	private ArrayList<IObserver> observerList = new ArrayList<>();
	private IAction triggerAction;
	private ArrayList<IGizmo> triggerList = new ArrayList<>();
	private float flipperSpeed;
	private float rotation;
	private Vect coords;
	private int ID;
	private boolean isRight;
	public FlipperGizmo(int x, int y, float flipperSpeed, int ID, boolean isRight){
		coords = new Vect(x,y);
		colour=Color.GREEN;
		this.flipperSpeed=flipperSpeed;
		setCoords(coords);
		this.isRight=isRight;
		this.ID=ID;
		if(isRight){
			triggerAction=new RightFlipperAction();
		}else{
			triggerAction=new LeftFlipperAction();
		}
		//Implement rounded edges after initial testing is complete.
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
		String serializedGizmo="";
		if(isRight){
			serializedGizmo = "RightFlipper"+getID()+" "+lines.get(0).p1().x()+" "+lines.get(0).p1().y()+" "+"\n";

		}else{
			serializedGizmo = "LeftFlipper"+getID()+" "+lines.get(0).p1().x()+" "+lines.get(0).p1().y()+" "+"\n";

		}
		for(IGizmo gizmo : triggerList){
			serializedGizmo+="Connect "+getID()+" "+gizmo.getID()+"\n";
		}
		return serializedGizmo;
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
	public int getID() {
		// TODO Auto-generated method stub
		return ID;
	}



	@Override
	public void rotate(float angle) {
		// TODO Auto-generated method stub
		rotation+=angle;
		//Need to manage movement of lines/circles here too!!
		
	}



	@Override
	public float getRotation() {
		// TODO Auto-generated method stub
		return rotation;
	}



	@Override
	public Vect getCoords() {
		// TODO Auto-generated method stub
		return coords;
	}



	@Override
	public void setCoords(Vect coords) {
		this.coords=coords;
		circles = new ArrayList<>();
		lines = new ArrayList<>();
		circles.add(new Circle(coords.x()+0.1, coords.y()+0.9, 0));
		circles.add(new Circle(coords.x()+0.9, coords.y()+0.9, 0));
		lines.add(new LineSegment(coords.x()+0.9, coords.y()+0.8, coords.x()+0.1, coords.y()+0.8));
		lines.add(new LineSegment(coords.x() + 0.9, coords.y()+1, coords.x() + 0.1, coords.y() + 1));
		
	}



	@Override
	public void onCollision(IBall ball) {
		// TODO Auto-generated method stub
		
	}



	

}

