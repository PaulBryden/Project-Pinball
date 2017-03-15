package model;

import java.awt.Color;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

class Wall implements IGizmo {
	
	private Set<IGizmo> triggers;
	private LineSegment line;
	private double coefficientOfReflection;
	
	public Wall(Vect p1, Vect p2) {
		line = new LineSegment(p1, p2);
		triggers = new HashSet<>();
		coefficientOfReflection = Constants.DEFAULT_COR;
	}
	
	public Wall(int x1, int y1, int x2, int y2) {
		this(new Vect(x1, y1), new Vect(x2, y2));
	}

	public LineSegment getLine() {
		return line;
	}
	
	public Vect p1() {
		return line.p1();
	}
	
	public Vect p2() {
		return line.p2();
	}

	@Override
	public void addGizmoToTrigger(IGizmo gizmo) {
		triggers.add(gizmo);
	}
	
	@Override
	public Set<IGizmo> getGizmosToTrigger() {
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
	}

	@Override
	public Vect getGridCoords() {
		return null;
	}

	@Override
	public void setGridCoords(Vect coords) {
		// Walls can't be moved
	}

	@Override
	public List<Vect> getExactCoords() {
		return null;
	}

	@Override
	public List<Circle> getAllCircles() {
		return new LinkedList<>();
	}

	@Override
	public List<LineSegment> getAllLineSegments() {
		List<LineSegment> l = new LinkedList<>();
		l.add(this.line);
		return l;
	}

	@Override
	public String serializeGizmo() {
		return null;
	}

	@Override
	public String getID() {
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

	@Override
	public int getGridWidth() {
		return -1;
	}

	@Override
	public int getGridHeight() {
		return -1;
	}

}
