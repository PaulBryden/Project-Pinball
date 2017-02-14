package model;

import physics.Angle;
import physics.Vect;

public class RightFlipper extends AbstractFlipper implements IFlipper{
	
	public RightFlipper(int id, Vect coords) {
		super("RF" + id, coords);
		this.pivot = new Vect(coords.x() + 2 - RADIUS, coords.y() + RADIUS);
		this.endCentre = new Vect(coords.x() + 2 - RADIUS, coords.y() + 2 - RADIUS);
		this.restingEndCentre = endCentre;
		this.openClockwise = true;
		this.openAngle = Angle.DEG_90;
		generateLinesAndCircles();
	}
	
	public RightFlipper(int id, int x, int y) {
		this(id, new Vect(x, y));
	}

}
