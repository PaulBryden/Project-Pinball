package model;

import java.util.ArrayList;
import java.util.List;

import physics.Angle;
import physics.Vect;

public class LeftFlipper extends AbstractFlipper implements IFlipper{
	
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

	@Override
	public List<Vect> getExactCoords() {
		// TODO Auto-generated method stub
		List<Vect> flipperVector = new ArrayList<Vect>();
		flipperVector.add(this.getAllCircles().get(0).getCenter());
		flipperVector.add(this.getAllCircles().get(1).getCenter());

		
		return flipperVector;
	}

}
