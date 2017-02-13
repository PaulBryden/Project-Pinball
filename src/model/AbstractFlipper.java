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
	protected Angle openAngle;
	protected Vect pivot;
	protected Vect restingEndCentre;
	protected Vect endCentre;
	
	public AbstractFlipper(int id, Vect coords) {
		super("F" + id, coords, Color.RED, false);
		this.angularVelocity = 18.85; // in rad/sec, approx. 1080 deg/sec
		this.angle = Angle.ZERO;
		this.open = false;
		this.addTriggerAction(new FlipperAction(this));
	}

	protected void generateLinesAndCircles() {
		circles.clear();
		circles.add(new Circle(pivot, RADIUS));
		lines.clear();
		LineSegment l = new LineSegment(pivot.x() - RADIUS, pivot.y(), restingEndCentre.x() - RADIUS, restingEndCentre.y());
		lines.add(Geometry.rotateAround(l, pivot, angle));
		l = new LineSegment(pivot.x() + RADIUS, pivot.y(), restingEndCentre.x() + RADIUS, restingEndCentre.y());
		lines.add(Geometry.rotateAround(l, pivot, angle));
		endCentre = (Geometry.rotateAround(restingEndCentre, pivot, angle));
		circles.add(new Circle(endCentre, RADIUS));
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
	
	public double getWidth() {
		return 2 * RADIUS;
	}
	
	public void moveForTime(double time) {
		if (open && angle.equals(openAngle) || !open && angle.equals(Angle.ZERO))
			return;
		double rad = angularVelocity * time;
		boolean clockwise = open == openClockwise;
		if (open) { // flipper trying to open
			Angle remaining = angle.minus(openAngle);
			if (Math.abs(remaining.radians()) < rad) {
				angle = new Angle(openAngle.radians());
			} else {
				rad *= clockwise ? 1 : -1;
				Angle rot = new Angle(rad);
				angle = angle.plus(rot);
			}
		} else { // flipper trying to close
			if (Math.abs(angle.radians()) < rad) {
				angle = Angle.ZERO;
			} else {
				rad *= clockwise ? 1 : -1;
				Angle rot = new Angle(rad);
				angle = angle.plus(rot);
			}
		}
		generateLinesAndCircles();
	}
}
