package model;

import java.util.List;

import physics.Circle;
import physics.Vect;

class BallGizmo extends AbstractGizmo implements IBall {

	private double radius;
	private Circle physicsCircle;
	private Vect velocity;

	public BallGizmo(String id, Vect coords, Vect velo) {
		super(id, coords, -1, -1, Constants.BALL_DEFAULT_COLOUR, false);
		this.radius = 0.3;
		velocity = velo;
		generateLinesAndCircles();
	}

	public BallGizmo(String id, double x, double y, double vx, double vy) {
		this(id, new Vect(x, y), new Vect(vx, vy));
	}

	@Override
	protected void generateLinesAndCircles() {
		circles.clear();
		this.physicsCircle = new Circle(coords, radius);
		circles.add(physicsCircle);
	}

	@Override
	public Vect getVelo() {
		return velocity;
	}

	@Override
	public void setVelo(Vect v) {
		velocity = v;
	}

	@Override
	public double getRadius() {
		return radius;
	}

	@Override
	public void setRadius(double radius) {
		this.radius = radius;
		generateLinesAndCircles();
	}

	@Override
	public Vect getCentre() {
		return physicsCircle.getCenter();
	}

	@Override
	public String serializeGizmo() {
		String serializedGizmo = "Ball " + getID() + " " + String.format("%.4f", physicsCircle.getCenter().x()) + " "
				+ String.format("%.4f", physicsCircle.getCenter().y()) + " " + String.format("%.4f", this.getVelo().x())
				+ " " + String.format("%.4f", this.getVelo().y()) + "\n";
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + getID() + " " + gizmo.getID() + "\n";
		}
		return serializedGizmo;
	}

	@Override
	public void moveForTime(double tickTime) {
		double x = physicsCircle.getCenter().x() + velocity.x() * tickTime;
		double y = physicsCircle.getCenter().y() + velocity.y() * tickTime;
		setGridCoords(new Vect(x, y));
	}

	@Override
	public Vect getGridCoords() {
		return physicsCircle.getCenter();
	}

	@Override
	public List<Vect> getExactCoords() {
		return null;
	}

	@Override
	public void setCentre(Vect v) {
		physicsCircle = new Circle(v, physicsCircle.getRadius());
	}

}
