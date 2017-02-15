package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class Absorber extends AbstractGizmo {

	public Absorber(String id, Vect coords, List<IBall> balls) {
		super("A" + id, coords, Color.GREEN, true);
		generateLinesAndCircles();
		this.addTriggerAction(new AbsorberAction(balls, this));
		this.coefficientOfReflection = 0;
	}

	public Absorber(String id, int x, int y,List<IBall> balls) {
		this(id, new Vect(x, y), balls);
	}

	@Override
	protected void generateLinesAndCircles() {
		circles.clear();
		circles.add(new Circle(coords.x(), coords.y(), 0));
		circles.add(new Circle(coords.x() + 1, coords.y(), 0));
		circles.add(new Circle(coords.x(), coords.y() + 1, 0));
		circles.add(new Circle(coords.x() + 1, coords.y() + 1, 0));
		lines.clear();
		lines.add(new LineSegment(coords.x(), coords.y(), coords.x() + 1, coords.y()));
		lines.add(new LineSegment(coords.x(), coords.y() + 1, coords.x() + 1, coords.y() + 1));
		lines.add(new LineSegment(coords.x(), coords.y(), coords.x(), coords.y() + 1));
		lines.add(new LineSegment(coords.x() + 1, coords.y(), coords.x() + 1, coords.y() + 1));
	}



	@Override
	public List<Vect> getExactCoords() {
		// TODO Auto-generated method stub
		List<Vect> coordVector = new ArrayList<Vect>();
		coordVector.add(this.getAllLineSegments().get(0).p1());
		coordVector.add(this.getAllLineSegments().get(0).p2());
		coordVector.add(this.getAllLineSegments().get(1).p2());
		coordVector.add(this.getAllLineSegments().get(2).p2());

		return coordVector;
		
	}

}
