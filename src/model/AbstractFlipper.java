package model;

import java.awt.Color;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public abstract class AbstractFlipper extends AbstractGizmo implements IFlipper {
	
	protected static final double RADIUS = 0.25;
	protected double angularVelocity;
	protected Angle angle;
	protected boolean open;
	protected boolean openClockwise;
	protected Vect pivot;
	protected Vect endCentre;
	
	public AbstractFlipper(int id, Vect coords) {
		super("F" + id, coords, Color.GREEN, false);
		this.angularVelocity = 18.85; // in rad/sec, approx. 1080 deg/sec
		this.angle = Angle.ZERO;
		this.open = false;
		this.addTriggerAction(new FlipperAction(this));
	}

	protected void generateLinesAndCircles() {
		circles.clear();
		circles.add(new Circle(pivot, RADIUS));
		Circle c = new Circle(endCentre, RADIUS);
		circles.add(Geometry.rotateAround(c, pivot, angle));
		lines.clear();
		LineSegment l = new LineSegment(pivot.x() - RADIUS, pivot.y(), endCentre.x() - RADIUS, endCentre.y());
		lines.add(Geometry.rotateAround(l, pivot, angle));
		l = new LineSegment(pivot.x() + RADIUS, pivot.y(), endCentre.x() + RADIUS, endCentre.y());
		lines.add(Geometry.rotateAround(l, pivot, angle));
		
	}
	
	public Angle getAngle() {
		return angle;
	}
	
	public double getAngularVelocity() {
		return angularVelocity;
	}
	
	public void toggleOpen() {
		this.open = !open;
	}
	
	public Vect getPivot() {
		return pivot;
	}
	
	public Vect getEndCentre() {
		return endCentre;
	}
	
	public void moveForTime(double time) {
		double rad = angularVelocity * time;
		rad *= (open == openClockwise) ? 1 : -1;
		Angle rot = new Angle(rad);
		angle = angle.plus(rot);
		generateLinesAndCircles();
	}
}
