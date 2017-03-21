package model;

import java.util.List;

import physics.Circle;
import physics.Vect;

/**
 * 
 * @author Paul
 *
 */
class CircleGizmo extends AbstractGizmo implements ICircleGizmo {

	private double radius;
	private Circle physicsCircle;

	/**
	 * 
	 * @param id
	 *            A unique ID
	 * @param coords
	 *            Top left corner of inscribing square
	 */
	public CircleGizmo(String id, Vect coords) {
		super(id, coords, 1, 1, Constants.CIRCLE_DEFAULT_COLOUR, true);
		this.radius = 0.5;
		generateLinesAndCircles();
	}

	@Override
	protected void generateLinesAndCircles() {
		this.physicsCircle = new Circle(coords.x() + 0.5, coords.y() + 0.5, radius);
		circles.clear();
		circles.add(physicsCircle);
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
	public List<Vect> getExactCoords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		return "Circle";
	}

}
