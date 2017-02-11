package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class DavidsGizmo implements IGizmo {
	
	private int x;
	private int y;
	private Color colour;
	private ArrayList<Circle> circles;
	private ArrayList<LineSegment> lines;
	float rotation;
	public DavidsGizmo(int x, int y) {
		this.x = x;
		this.y = y;
		colour = Color.GREEN;
		circles = new ArrayList<>();
		lines = new ArrayList<>();
		circles.add(new Circle(x, y, 0));
		circles.add(new Circle(x + 1, y, 0));
		circles.add(new Circle(x, y + 1, 0));
		circles.add(new Circle(x + 1, y + 1, 0));
		lines.add(new LineSegment(x, y, x + 1, y));
		lines.add(new LineSegment(x, y + 1, x + 1, y + 1));
		lines.add(new LineSegment(x, y, x, y + 1));
		lines.add(new LineSegment(x + 1, y, x + 1, y + 1));
	}


	@Override
	public boolean isStatic() {
		return true;
	}

	@Override
	public Color getColour() {
		return colour;
	}

	@Override
	public void setColour(Color colour) {
		this.colour = colour;
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
	public void performActions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggerConnectedGizmos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<IGizmo> getGizmosToTrigger() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public void setPoints(double[][] points) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Circle> getAllCircles() {
		return circles;
	}

	@Override
	public ArrayList<LineSegment> getAllLineSegments() {
		return lines;
	}

	@Override
	public String serializeGizmo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getID() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}


	@Override
	public void rotate(float angle) {
		rotation+=angle;
		
	}


	@Override
	public float getRotation() {
		return rotation;
		
	}


	@Override
	public Vect getCoords() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setCoords(Vect coords) {
		// TODO Auto-generated method stub
		
	}

}
