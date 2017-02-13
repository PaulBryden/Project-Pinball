package model;

import physics.Angle;
import physics.Vect;

public class LeftFlipper extends AbstractFlipper {
	
	public LeftFlipper(int id, Vect coords) {
		super(id, coords);
		this.pivot = new Vect(coords.x() + RADIUS, coords.y() + RADIUS);
		this.endCentre = new Vect(coords.x() + RADIUS, coords.y() + 2 - RADIUS);
		this.restingEndCentre = endCentre;
		this.openClockwise = false;
		this.openAngle = Angle.DEG_270;
		generateLinesAndCircles();
	}
	
	public LeftFlipper(int id, int x, int y) {
		this(id, new Vect(x, y));
	}

	@Override
	public String serializeGizmo() {
		// TODO Auto-generated method stub
		return null;
	}

}
