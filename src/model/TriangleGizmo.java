package model;

import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class TriangleGizmo extends AbstractGizmo {

	public TriangleGizmo(String id, Vect coords) {
		super(id, coords, 1, 1, Constants.TRIANGLE_DEFAULT_COLOUR, true);
		generateLinesAndCircles();
	}

	public TriangleGizmo(String id, int x, int y) {
		this(id, new Vect(x, y));
	}

	@Override
	protected void generateLinesAndCircles() {
		Angle a = Angle.ZERO;
		for (int i = 0; i < rotation; i++)
			a = a.plus(Angle.DEG_90);
		Vect centre = new Vect(coords.x() + 0.5, coords.y() + 0.5);
		circles.clear();
		Circle c = new Circle(coords.x(), coords.y(), 0);
		circles.add(Geometry.rotateAround(c, centre, a));
		c = new Circle(coords.x() + 1, coords.y(), 0);
		circles.add(Geometry.rotateAround(c, centre, a));
		c = new Circle(coords.x(), coords.y() + 1, 0);
		circles.add(Geometry.rotateAround(c, centre, a));
		lines.clear();
		LineSegment l = new LineSegment(coords.x(), coords.y(), coords.x() + 1, coords.y());
		lines.add(Geometry.rotateAround(l, centre, a));
		l = new LineSegment(coords.x(), coords.y() + 1, coords.x() + 1, coords.y());
		lines.add(Geometry.rotateAround(l, centre, a));
		l = new LineSegment(coords.x(), coords.y(), coords.x(), coords.y() + 1);
		lines.add(Geometry.rotateAround(l, centre, a));
	}

	@Override
	public List<Vect> getExactCoords() {
		List<Vect> coordVector = new ArrayList<Vect>();
		coordVector.add(this.getAllLineSegments().get(0).p1());
		coordVector.add(this.getAllLineSegments().get(0).p2());
		coordVector.add(this.getAllLineSegments().get(1).p1());
		return coordVector;
	}
	
	@Override
	public String serializeGizmo() {
		String serializedGizmo = "Triangle " + getID() + " " + (int) this.getGridCoords().x() + " " + (int) this.getGridCoords().y()
				+ "\n";
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + this.getID() + " " + gizmo.getID() + "\n";
		}
		return serializedGizmo;
	}

}
