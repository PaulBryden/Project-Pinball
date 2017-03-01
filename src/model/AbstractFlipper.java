package model;

import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public abstract class AbstractFlipper extends AbstractGizmo implements IFlipper {

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

	public AbstractFlipper(String id, Vect coords) {
		super(id, coords, Constants.FLIPPER_DEFAULT_COLOUR, true); // static for
																	// now -
																	// until we
																	// resolve
																	// flipper
																	// collisions
																	// properly.
		this.angularVelocity = 0;
		this.angle = Angle.ZERO;
		this.open = false;
		this.addTriggerAction(new FlipperAction(this));
		this.coefficientOfReflection = 0.95;
	}

	protected void generateLinesAndCircles() {
		circles.clear();
		circles.add(new Circle(pivot, RADIUS));
		lines.clear();
		LineSegment l = new LineSegment(pivot.x() - RADIUS, pivot.y(), restingEndCentre.x() - RADIUS,
				restingEndCentre.y());
		lines.add(Geometry.rotateAround(l, pivot, angle));
		addEndPoints(l);
		l = new LineSegment(pivot.x() + RADIUS, pivot.y(), restingEndCentre.x() + RADIUS, restingEndCentre.y());
		lines.add(Geometry.rotateAround(l, pivot, angle));
		addEndPoints(l);
		endCentre = (Geometry.rotateAround(restingEndCentre, pivot, angle));
		circles.add(new Circle(endCentre, RADIUS));
	}

	private void addEndPoints(LineSegment line) {
		circles.add(new Circle(line.p1(), 0));
		circles.add(new Circle(line.p2(), 0));
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

	@Override
	public List<Vect> getExactCoords() {
		List<Vect> flipperVector = new ArrayList<Vect>();
		flipperVector.add(pivot);
		flipperVector.add(endCentre);
		return flipperVector;
	}

	public Vect getEndCentre() {
		return endCentre;
	}

	public double getWidth() {
		return 2 * RADIUS;
	}

	public double timeUntilStatic() {
		if (isStatic)
			return 0;
		Angle remaining = open ? openAngle.minus(angle) : angle;
		return Math.abs(remaining.radians() / angularVelocity);
	}

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
