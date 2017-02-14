package model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class Wall implements IWall {
	
	private List<IGizmo> triggers;
	private LineSegment line;
	private double coefficientOfReflection;
	
	public Wall(Vect p1, Vect p2) {
		line = new LineSegment(p1, p2);
		triggers = new LinkedList<>();
		coefficientOfReflection = 1;
	}
	
	public Wall(int x1, int y1, int x2, int y2) {
		this(new Vect(x1, y1), new Vect(x2, y2));
	}

	@Override
	public LineSegment getLine() {
		return line;
	}
	
	@Override
	public Vect p1() {
		return line.p1();
	}
	
	@Override
	public Vect p2() {
		return line.p2();
	}

	@Override
	public void addGizmoToTrigger(IGizmo gizmo) {
		triggers.add(gizmo);
	}
	
	@Override
	public List<IGizmo> getGizmosToTrigger() {
		return triggers;
	}

	@Override
	public void triggerConnectedGizmos() {
		for (IGizmo gizmo : triggers) {
			gizmo.performActions();
		}
	}

	@Override
	public boolean isStatic() {
		return true;
	}

	@Override
	public Color getColour() {
		return null;
	}

	@Override
	public void setColour(Color colour) {
		// Walls don't have colours
	}

	@Override
	public void addTriggerAction(IAction action) {
		// Walls don't have actions
	}

	@Override
	public void performActions() {
		// Walls don't have actions
	}

	@Override
	public void onCollision(IBall ball) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vect getGridCoords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGridCoords(Vect coords) {
		// Walls can't be moved
	}

	@Override
	public List<Vect> getExactCoords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Circle> getAllCircles() {
		return null;
	}

	@Override
	public List<LineSegment> getAllLineSegments() {
		return null;
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

	@Override
	public void rotate(int steps) {
		// Walls can't be rotated
	}

	@Override
	public int getRotation() {
		return 0;
	}

	@Override
	public double getCoefficientOfReflection() {
		return coefficientOfReflection;
	}

}
