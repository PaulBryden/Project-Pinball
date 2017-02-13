package model;

import physics.Vect;

public class LeftFlipper extends AbstractFlipper {
	
	public LeftFlipper(int id, Vect coords) {
		super(id, coords);
		this.pivot = new Vect(coords.x() + 0.25, coords.y() + 0.25);
		this.endCentre = new Vect(coords.x() + 1.75, coords.y() + 1.75);
		this.openClockwise = false;
	}

	@Override
	public String serializeGizmo() {
		// TODO Auto-generated method stub
		return null;
	}

}
