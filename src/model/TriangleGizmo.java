package model;

import java.awt.Color;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class TriangleGizmo extends AbstractGizmo {

	public TriangleGizmo(int id, Vect coords) {
		super("T" + id, coords, Color.RED, true);
		generateLinesAndCircles();
	}

	public TriangleGizmo(int id, int x, int y) {
		this(id, new Vect(x, y));
	}

	@Override
	protected void generateLinesAndCircles() {
		circles.clear();
		circles.add(new Circle(coords.x(), coords.y(), 0));
		circles.add(new Circle(coords.x() + 1, coords.y(), 0));
		circles.add(new Circle(coords.x(), coords.y() + 1, 0));
		lines.clear();
		lines.add(new LineSegment(coords.x(), coords.y(), coords.x() + 1, coords.y()));
		lines.add(new LineSegment(coords.x(), coords.y() + 1, coords.x() + 1, coords.y()));
		lines.add(new LineSegment(coords.x(), coords.y(), coords.x(), coords.y() + 1));
	}

	@Override
	public String serializeGizmo() {
		String serializedGizmo = "Triangle" + getID() + " " + lines.get(0).p1().x() + " " + lines.get(0).p1().y() + " "
				+ "\n";
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + getID() + " " + gizmo.getID() + "\n";
		}
		return serializedGizmo;
	}

}
