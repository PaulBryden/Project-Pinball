package model;

import java.awt.Color;
import java.util.List;

import physics.Circle;
import physics.Vect;

public class CircleGizmo extends AbstractGizmo implements ICircle {

	private double radius;
	private Circle physicsCircle;

	public CircleGizmo(String id, Vect coords) {
		super(id, coords, Color.YELLOW, true);
		this.radius = 0.5;
		generateLinesAndCircles();
	}

	public CircleGizmo(String id, int x, int y) {
		this(id, new Vect(x, y));
	}

	@Override
	protected void generateLinesAndCircles() {
		this.physicsCircle = new Circle(coords.x() + 0.5, coords.y() + 0.5, radius);
		circles.clear();
		circles.add(physicsCircle);
	}

	@Override
	public String serializeGizmo() {
		String serializedGizmo = "Circle " + getID() + " " + (physicsCircle.getCenter().x() - 0.5) + " "
				+ (physicsCircle.getCenter().y() - 0.5) + " " + "\n";
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + getID() + " " + gizmo.getID() + "\n";
		}
		return serializedGizmo;
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
		return false;
	}

	@Override
	public List<Vect> getExactCoords() {
		// TODO Auto-generated method stub
		return null;
	}

}
