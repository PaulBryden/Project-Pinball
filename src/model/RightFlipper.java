package model;

import physics.Angle;
import physics.Vect;

class RightFlipper extends AbstractFlipper implements IFlipper{
	
	public RightFlipper(String id, Vect coords) {
		super(id, coords);
		this.pivot = new Vect(coords.x() + 2 - RADIUS, coords.y() + RADIUS);
		this.endCentre = new Vect(coords.x() + 2 - RADIUS, coords.y() + 2 - RADIUS);
		this.restingEndCentre = endCentre;
		this.openClockwise = true;
		this.openAngle = Angle.DEG_90;
		generateLinesAndCircles();
	}
	
	public RightFlipper(String id, int x, int y) {
		this(id, new Vect(x, y));
	}

	@Override
	public String getType() {
		return "RightFlipper";
	}
}
