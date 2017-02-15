package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class TriangleGizmo extends AbstractGizmo {

	public TriangleGizmo(String id, Vect coords) {
		super(id, coords, Color.RED, true);
		generateLinesAndCircles();
	}

	public TriangleGizmo(String id, int x, int y) {
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
	public List<Vect> getExactCoords() {
		// TODO Auto-generated method stub
		List<Vect> coordVector = new ArrayList<Vect>();
		coordVector.add(this.getAllLineSegments().get(0).p1());
		coordVector.add(this.getAllLineSegments().get(0).p2());
		coordVector.add(this.getAllLineSegments().get(1).p1());

		return coordVector;

		

	}
	
	@Override
	public String serializeGizmo() {
		String serializedGizmo = "TriangleGizmo " + getID() + " " + this.getGridCoords().x() + " " + this.getGridCoords().y() + " "
				+ "\n";
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + this.getID() + " " + gizmo.getID() + "\n";
		}
		return serializedGizmo;
	}

}
