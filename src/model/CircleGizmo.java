package model;

import java.util.List;

import physics.Circle;
import physics.Vect;

public class CircleGizmo extends AbstractGizmo {

	private double radius;
	private Circle physicsCircle;

	public CircleGizmo(String id, Vect coords) {
		super(id, coords, 1, 1, Constants.CIRCLE_DEFAULT_COLOUR, true);
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
		String serializedGizmo = "Circle " + getID() + " " + (int) (physicsCircle.getCenter().x() - 0.5) + " "
				+ (int) (physicsCircle.getCenter().y() - 0.5) + "\n";
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + getID() + " " + gizmo.getID() + "\n";
		}
		return serializedGizmo;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
		generateLinesAndCircles();
	}

	public Vect getCentre() {
		return physicsCircle.getCenter();
	}

	@Override
	public List<Vect> getExactCoords() {
		// TODO Auto-generated method stub
		return null;
	}

}
