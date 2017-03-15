package model;

import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

/**
 * 
 * @author David
 *
 */
abstract class AbstractFlipper extends AbstractGizmo implements IFlipper {

	protected static final double RADIUS = 0.25;
	protected static final double ABS_ANGULAR_VELOCITY = 18.85; // in rad/sec,
																// approx. 1080
																// deg/sec
	protected double angularVelocity;
	protected Angle angle;
	protected boolean open;
	protected boolean openClockwise;
	protected Angle openAngle;
	protected Vect pivot;
	protected Vect restingEndCentre;
	protected Vect endCentre;
	private boolean vertical;

	/**
	 * 
	 * @param id
	 *            The unique ID
	 * @param coords
	 *            Top left corner
	 */
	public AbstractFlipper(String id, Vect coords) {
		super(id, coords, 2, 2, Constants.FLIPPER_DEFAULT_COLOUR, true);
		this.angularVelocity = 0;
		this.angle = Angle.ZERO;
		this.open = false;
		this.addTriggerAction(new FlipperAction(this));
		this.coefficientOfReflection = 0.95;
		this.vertical = true;
	}

	@Override
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
	public void toggleOpen() {
		this.open = !open;
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
	public double timeUntilStatic() {
		if (isStatic)
			return 0;
		Angle remaining = open ? openAngle.minus(angle) : angle;
		return Math.abs(remaining.radians() / angularVelocity);
	}

	@Override
	public void moveForTime(double time) {
		if (open && angle.equals(openAngle) || !open && angle.equals(Angle.ZERO)) {
			return;
		}
		angularVelocity = ABS_ANGULAR_VELOCITY;
		this.isStatic = false;
		double rad = angularVelocity * time;
		boolean clockwise = open == openClockwise;
		if (!clockwise)
			angularVelocity *= -1;
		if (open) { // flipper trying to open
			Angle remaining = angle.minus(openAngle);
			if (Math.abs(remaining.radians()) - Constants.FLOAT_MARGIN < rad) {
				angle = new Angle(openAngle.radians());
				this.isStatic = true;
				angularVelocity = 0;
			} else {
				rad *= clockwise ? 1 : -1;
				Angle rot = new Angle(rad);
				angle = angle.plus(rot);
			}
		} else { // flipper trying to close
			if (Math.abs(angle.radians()) - Constants.FLOAT_MARGIN < rad) {
				angle = Angle.ZERO;
				this.isStatic = true;
				angularVelocity = 0;
			} else {
				rad *= clockwise ? 1 : -1;
				Angle rot = new Angle(rad);
				angle = angle.plus(rot);
			}
		}
		generateLinesAndCircles();
	}

}
