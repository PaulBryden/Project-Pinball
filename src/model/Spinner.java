package model;

import java.util.ArrayList;
import java.util.List;


import java.awt.Color;
import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class Spinner extends AbstractGizmo implements ISpinner{

	protected static final double RADIUS = 0.25;
	protected static final double ABS_ANGULAR_VELOCITY = 2; // in rad/sec,
	// approx. 1080
	// deg/sec
	protected double angularVelocity;
	protected Angle angle;

	protected Vect pivot;
	protected Vect restingEndCentre;
	protected Vect endCentre;
	private boolean vertical;
	private Direction direction;

	public Spinner(String id, Vect coords, Direction direction) {
		super(id, coords, 3,3, Color.PINK, false);
		this.pivot = new Vect(coords.x() + 1.5, coords.y() + 1.5);
		this.endCentre = new Vect(coords.x() + 1.5, coords.y() + 3 - RADIUS);
		this.restingEndCentre = endCentre;
		this.direction = direction;
		// this.openAngle = Angle.DEG_90;
		generateLinesAndCircles();
	}

	public Spinner(String id, int x, int y) {
		this(id, new Vect(x, y), Direction.CW);
	}

	@Override
	public String serializeGizmo() {
		String serializedGizmo = "RightFlipper " + getID() + " " + (int) this.getGridCoords().x() + " "
				+ (int) this.getGridCoords().y() + "\n";
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + this.getID() + " " + gizmo.getID() + "\n";
		}
		return serializedGizmo;
	}

	protected void generateLinesAndCircles() {
		circles.clear();
		circles.add(new Circle(pivot, RADIUS));
		lines.clear();
		LineSegment l;
		if (vertical) {
			l = new LineSegment(pivot.x() - RADIUS, pivot.y(), restingEndCentre.x() - RADIUS, restingEndCentre.y());
		} else {
			l = new LineSegment(pivot.x(), pivot.y() - RADIUS, restingEndCentre.x(), restingEndCentre.y() - RADIUS);
		}
		lines.add(Geometry.rotateAround(l, pivot, angle));
		if (vertical) {
			l = new LineSegment(pivot.x() + RADIUS, pivot.y(), restingEndCentre.x() + RADIUS, restingEndCentre.y());
		} else {
			l = new LineSegment(pivot.x(), pivot.y() + RADIUS, restingEndCentre.x(), restingEndCentre.y() + RADIUS);
		}
		lines.add(Geometry.rotateAround(l, pivot, angle));
		addEndPoints();
		endCentre = (Geometry.rotateAround(restingEndCentre, pivot, angle));
		circles.add(new Circle(endCentre, RADIUS));
	}

	/**
	 * Add zero-diameter circles to the ends of the line segments.
	 */
	private void addEndPoints() {
		for (LineSegment line : lines) {
			circles.add(new Circle(line.p1(), 0));
			circles.add(new Circle(line.p2(), 0));
		}
	}

	@Override
	public void rotate(int steps) {
		Angle a = Angle.ZERO;
		Vect cor = new Vect(coords.x() + 1, coords.y() + 1);
		for (int i = 0; i < steps; i++) {
			vertical = !vertical;
			a = a.plus(Angle.DEG_90);
		}
		restingEndCentre = Geometry.rotateAround(restingEndCentre, cor, a);
		endCentre = Geometry.rotateAround(endCentre, cor, a);
		pivot = Geometry.rotateAround(pivot, cor, a);
		super.rotate(steps);
	}

	@Override
	public void setGridCoords(Vect coords) {
		Vect shift = coords.minus(this.coords);
		this.coords = coords;
		this.pivot = pivot.plus(shift);
		this.endCentre = endCentre.plus(shift);
		this.restingEndCentre = restingEndCentre.plus(shift);
		generateLinesAndCircles();
	}

	@Override
	public Angle getAngle() {
		return angle;
	}

	@Override
	public void setAngle(Angle angle) {
		this.angle = angle;
		generateLinesAndCircles();
	}

	@Override
	public double getAngularVelocity() {
		return angularVelocity;
	}

	@Override
	public Vect getPivot() {
		return pivot;
	}

	@Override
	public List<Vect> getExactCoords() {
		List<Vect> flipperVector = new ArrayList<Vect>();
		flipperVector.add(pivot);
		flipperVector.add(endCentre);
		return flipperVector;
	}

	@Override
	public Vect getEndCentre() {
		return endCentre;
	}

	@Override
	public double getWidth() {
		return 2 * RADIUS;
	}

	@Override
	public void moveForTime(double time) {

		angularVelocity = ABS_ANGULAR_VELOCITY;
		this.isStatic = false;
		double rad = angularVelocity * time;
		boolean clockwise = (direction == Direction.CW);
		angularVelocity *= -1;
		rad *= clockwise ? 1 : -1;
		Angle rot = new Angle(rad);
		angle = angle.plus(rot);

		generateLinesAndCircles();
	}

	public enum Direction {
		CW, CCW;
	}
}
