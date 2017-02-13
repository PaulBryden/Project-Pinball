package model;

import java.util.LinkedList;
import java.util.List;

import physics.LineSegment;
import physics.Vect;

public class Wall implements IWall {
	
	private List<IGizmo> triggers;
	private LineSegment line;
	
	public Wall(Vect p1, Vect p2) {
		line = new LineSegment(p1, p2);
		triggers = new LinkedList<>();
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

}
