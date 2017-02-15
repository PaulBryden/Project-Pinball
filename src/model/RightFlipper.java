package model;

import physics.Angle;
import physics.Vect;

public class RightFlipper extends AbstractFlipper implements IFlipper{
	
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
	public String serializeGizmo() {
		String serializedGizmo = "RightFlipper " + getID() + " " + this.getGridCoords().x() + " " + this.getGridCoords().y() + " "
				+ "\n";
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + this.getID() + " " + gizmo.getID() + "\n";
		}
		return serializedGizmo;
	}
}
