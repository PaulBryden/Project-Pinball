package model;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class CounterGizmo extends AbstractGizmo implements ICounterGizmo {
	
	private Vect bottomRightCoords;
	private int count;

	public CounterGizmo(String id, Vect topLeftCoords, Vect bottomRightCoords) {
		super(id, topLeftCoords, (int) (bottomRightCoords.x() - topLeftCoords.x()),
				(int) (bottomRightCoords.y() - topLeftCoords.y()), Constants.COUNTER_GIZMO_DEFAULT_COLOUR, true);
		this.bottomRightCoords = bottomRightCoords;
		this.count = 0;
		this.addTriggerAction(new CounterAction(this));
		generateLinesAndCircles();
	}

	@Override
	public List<Vect> getExactCoords() {
		List<Vect> coordVector = new ArrayList<Vect>();
		coordVector.add(this.getAllLineSegments().get(0).p1());
		coordVector.add(this.getAllLineSegments().get(0).p2());
		coordVector.add(this.getAllLineSegments().get(1).p2());
		coordVector.add(this.getAllLineSegments().get(2).p2());
		return coordVector;
	}

	@Override
	public int getCount() {
		return count;
	}
	
	@Override
	public void increment() {
		this.count++;
	}

	@Override
	public void reset() {
		this.count = 0;
	}
	
	@Override
	protected void generateLinesAndCircles() {
		circles.clear();
		circles.add(new Circle(coords.x(), coords.y(), 0));
		circles.add(new Circle(bottomRightCoords.x(), coords.y(), 0));
		circles.add(new Circle(coords.x(), bottomRightCoords.y(), 0));
		circles.add(new Circle(bottomRightCoords.x(), bottomRightCoords.y(), 0));
		lines.clear();
		lines.add(new LineSegment(coords.x(), coords.y(), bottomRightCoords.x(), coords.y()));
		lines.add(new LineSegment(coords.x(), bottomRightCoords.y(), bottomRightCoords.x(), bottomRightCoords.y()));
		lines.add(new LineSegment(coords.x(), coords.y(), coords.x(), bottomRightCoords.y()));
		lines.add(new LineSegment(bottomRightCoords.x(), coords.y(), bottomRightCoords.x(), bottomRightCoords.y()));
	}
	
	@Override
	public String serializeGizmo() {
		String serializedGizmo = "CounterGizmo " + getID() + " " + (int) this.getGridCoords().x() + " "
				+ (int) this.getGridCoords().y() + " " + (int) this.getBottomRightCoords().x() + " "
				+ (int) this.getBottomRightCoords().y() + "\n";
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + this.getID() + " " + gizmo.getID() + "\n";
		}
		return serializedGizmo;
	}

	@Override
	public Vect getBottomRightCoords() {
		return bottomRightCoords;
	}

}
