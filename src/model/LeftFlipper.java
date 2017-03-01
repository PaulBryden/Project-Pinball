package model;

import physics.Angle;
import physics.Vect;

public class LeftFlipper extends AbstractFlipper implements IFlipper{
	
	public LeftFlipper(String id, Vect coords) {
		super(id, coords);
		this.pivot = new Vect(coords.x() + RADIUS, coords.y() + RADIUS);
		this.endCentre = new Vect(coords.x() + RADIUS, coords.y() + 2 - RADIUS);
		this.restingEndCentre = endCentre;
		this.openClockwise = false;
		this.openAngle = Angle.DEG_270;
		generateLinesAndCircles();
	}
	
	public LeftFlipper(String id, int x, int y) {
		this(id, new Vect(x, y));
	}

	@Override
	public void setGridCoords(Vect coords) {
		this.pivot = new Vect(coords.x() + RADIUS, coords.y() + RADIUS);
		this.endCentre = new Vect(coords.x() + RADIUS, coords.y() + 2 - RADIUS);
		this.restingEndCentre = endCentre;
		generateLinesAndCircles();
	}

	@Override
	public Vect getGridCoords(){
		return (new Vect(this.pivot.x() - RADIUS, this.pivot.y() - RADIUS));
	}

	@Override
	public String serializeGizmo() {
		String serializedGizmo = "LeftFlipper " + getID() + " " + (int) this.getGridCoords().x() + " " + (int) this.getGridCoords().y() + " "
				+ "\n";
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + this.getID() + " " + gizmo.getID() + "\n";
		}
		return serializedGizmo;
	}

}
