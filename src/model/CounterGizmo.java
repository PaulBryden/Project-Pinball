package model;

import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class CounterGizmo extends AbstractGizmo implements ICounterGizmo {
	
	private IModel model;
	private Vect bottomRightCoords;
	private int count;

	public CounterGizmo(IModel model, String id, Vect topLeftCoords, Vect bottomRightCoords) {
		super(id, topLeftCoords, (int) (bottomRightCoords.x() - topLeftCoords.x()),
				(int) (bottomRightCoords.y() - topLeftCoords.y()), Constants.COUNTER_GIZMO_DEFAULT_COLOUR, true);
		this.model = model;
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
	public void setGridCoords(Vect coords) {
		int xdiff = (int) (coords.x() - this.coords.x());
		int ydiff = (int) (coords.y() - this.coords.y());
		this.coords = coords;
		this.bottomRightCoords = new Vect(bottomRightCoords.x() + xdiff, bottomRightCoords.y() + ydiff);
		generateLinesAndCircles();
	}

	@Override
	public void rotate(int steps) {
		if (steps % 2 != 0) { // number of steps is odd, so rotate
			int w = this.getGridHeight();
			int h = this.getGridWidth();
			for (int x = (int) coords.x(); x < coords.x() + w; x++) {
				for (int y = (int) coords.y(); y < coords.y() + h; y++) {
					if (x > 19 || y > 19) {
						throw new IllegalRotationException("Cannot rotate absorber over the edge of the board.");
					}
					if (!model.isCellEmpty(new Vect(x, y)) && !this.equals(model.getGizmo(new Vect(x,y)))) {
						throw new IllegalRotationException("Cannot rotate absorber over another object.");
					}
				}
			}
			
			this.gridHeight = this.getGridWidth();
			this.gridWidth = w;
			this.bottomRightCoords = new Vect(coords.x() + gridWidth, coords.y() + gridHeight);
			generateLinesAndCircles();
		}
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
		serializedGizmo += "Colour " + this.getID() + " " + this.getColour().getRGB() + "\n";
		return serializedGizmo;
	}

	@Override
	public Vect getBottomRightCoords() {
		return bottomRightCoords;
	}

	@Override
	public String getType() {
		return "CounterGizmo";
	}

}
