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
	protected static final double MARGIN = 0.000001;
	// approx. 1080
	// deg/sec
	protected double angularVelocity;
	protected Angle angle;
	

	protected Vect pivot;
	protected Vect restingEndCentre1;
	protected Vect restingEndCentre2;
	protected Vect endCentre1;
	protected Vect endCentre2;
	private boolean vertical;
	private Direction direction;

	public Spinner(String id, Vect coords, Direction direction) {
		super(id, coords, 3,3, Color.PINK, false);
		this.pivot = new Vect(coords.x() + 1.5, coords.y() + 1.5);
		this.vertical = true;
		this.endCentre1 = new Vect(coords.x() + 1.5, coords.y() + 3 - RADIUS);
		this.endCentre2 = new Vect(coords.x() + 1.5, coords.y() + RADIUS);
		this.restingEndCentre1 = endCentre1;
		this.restingEndCentre2 = endCentre2;
		this.angle = new Angle(1.0,0.0 );
		this.direction = direction;
		generateLinesAndCircles();
	}

	public Spinner(String id, int x, int y) {
		this(id, new Vect(x, y), Direction.CW);
	}
	public Spinner(String id, Vect coords) {
		this(id, coords, Direction.CW);
	}

	@Override
	public String serializeGizmo() {
		String serializedGizmo = "Spinner " + getID() + " " + (int) this.getGridCoords().x() + " "
				+ (int) this.getGridCoords().y() + "\n";
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + this.getID() + " " + gizmo.getID() + "\n";
		}
		return serializedGizmo;
	}

	protected void generateLinesAndCircles() {
		circles.clear();
		lines.clear();
		LineSegment l;
		
		if (vertical) {
			l = new LineSegment(restingEndCentre2.x() - (RADIUS-MARGIN), restingEndCentre2.y(), restingEndCentre1.x() - (RADIUS-MARGIN), restingEndCentre1.y());
		} else {
			l = new LineSegment(restingEndCentre2.x(), restingEndCentre2.y() - (RADIUS-MARGIN), restingEndCentre1.x(), restingEndCentre1.y() - (RADIUS-MARGIN));
		}
		lines.add(Geometry.rotateAround(l, pivot, angle));
		
		if (vertical) {
			l = new LineSegment(restingEndCentre2.x() + (RADIUS-MARGIN), restingEndCentre2.y(), restingEndCentre1.x() + (RADIUS-MARGIN), restingEndCentre1.y());
		} else {
			l = new LineSegment(restingEndCentre2.x(), restingEndCentre2.y() + (RADIUS-MARGIN), restingEndCentre1.x(), restingEndCentre1.y() + (RADIUS-MARGIN));
		}
		lines.add(Geometry.rotateAround(l, pivot, angle));
		
		addEndPoints();
		
		endCentre1 = (Geometry.rotateAround(restingEndCentre1, pivot, angle));
		endCentre2 = (Geometry.rotateAround(restingEndCentre2, pivot, angle));
		circles.add(new Circle(endCentre2, RADIUS));
		circles.add(new Circle(endCentre1, RADIUS));
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
		Vect cor = new Vect(coords.x() + 1.5, coords.y() + 1.5);
		for (int i = 0; i < steps; i++) {
			vertical = !vertical;
			a = a.plus(Angle.DEG_90);
		}
		restingEndCentre1 = Geometry.rotateAround(restingEndCentre1, cor, a);
		endCentre1 = Geometry.rotateAround(endCentre1, cor, a);
		restingEndCentre2 = Geometry.rotateAround(restingEndCentre2, cor, a);
		endCentre2 = Geometry.rotateAround(endCentre2, cor, a);
		pivot = Geometry.rotateAround(pivot, cor, a);
		super.rotate(steps);
	}

	@Override
	public void setGridCoords(Vect coords) {
		Vect shift = coords.minus(this.coords);
		this.coords = coords;
		this.pivot = pivot.plus(shift);
		this.endCentre1 = endCentre1.plus(shift);
		this.restingEndCentre1 = restingEndCentre1.plus(shift);
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
		flipperVector.add(endCentre1);
		return flipperVector;
	}

	@Override
	public Vect getEndCentre1() {
		return endCentre1;
	}

	@Override
	public Vect getEndCentre2() {
		return endCentre2;
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
	
	@Override
	public void setDirection(Direction direction){
		this.direction = direction;
	}
	
	@Override
	public Direction getDirection() {
		return direction;
	}
	
		@Override
	public void toggleDirection() {
		direction = (direction == direction.CW)? direction.CCW:direction.CW;
		
	}
	public enum Direction {
		CW, CCW;
	}




}
