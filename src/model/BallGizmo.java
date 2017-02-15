package model;

import java.awt.Color;
import java.util.List;

import physics.Circle;
import physics.Vect;

public class BallGizmo extends AbstractGizmo implements IBall {

	private double radius;
	private Circle physicsCircle;
	private Vect velocity;

	public BallGizmo(String id, Vect coords, Vect velo) {
		super("B" + id, coords, Color.BLUE, false);
		this.radius = 0.3;
		velocity = velo;
		generateLinesAndCircles();
	}

	public BallGizmo(String id, int x, int y, int vx, int vy) {
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
	public boolean isBall() {
		return true;
	}

	@Override
	public String serializeGizmo() {
		String serializedGizmo = "Ball" + getID() + " " + physicsCircle.getCenter().x() + " "
				+ physicsCircle.getCenter().y()  + " " + this.getVelo().x() + " "
						+ this.getVelo().y() +  "\n";
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
		// TODO Auto-generated method stub
		return null;
	}

}
