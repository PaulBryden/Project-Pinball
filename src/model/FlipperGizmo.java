package model;

import java.awt.Color;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public class FlipperGizmo extends AbstractGizmo implements IFlipper {
	
	private double flipperSpeed;
	private boolean isRight;

	public FlipperGizmo(Vect coords, double flipperSpeed, int id, boolean isRight) {
		super("F" + id, coords, Color.GREEN, false);
		this.flipperSpeed = flipperSpeed;
		this.isRight = isRight;
		if (isRight) {
			this.addTriggerAction(new RightFlipperAction());
		} else {
			this.addTriggerAction(new LeftFlipperAction());
		}
		// Implement rounded edges after initial testing is complete.
	}
	
	@Override
	protected void generateLinesAndCircles() {
		circles.clear();
		circles.add(new Circle(coords.x() + 0.1, coords.y() + 0.9, 0));
		circles.add(new Circle(coords.x() + 0.9, coords.y() + 0.9, 0));
		lines.clear();
		lines.add(new LineSegment(coords.x() + 0.9, coords.y() + 0.8, coords.x() + 0.1, coords.y() + 0.8));
		lines.add(new LineSegment(coords.x() + 0.9, coords.y() + 1, coords.x() + 0.1, coords.y() + 1));
	}
	
	@Override
	public String serializeGizmo() {
		String serializedGizmo = "";
		if (isRight) {
			serializedGizmo = "RightFlipper" + getID() + " " + lines.get(0).p1().x() + " " + lines.get(0).p1().y() + " "
					+ "\n";

		} else {
			serializedGizmo = "LeftFlipper" + getID() + " " + lines.get(0).p1().x() + " " + lines.get(0).p1().y() + " "
					+ "\n";

		}
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + getID() + " " + gizmo.getID() + "\n";
		}
		return serializedGizmo;
	}

	@Override
	public void setFlipperSpeed(double speed) {
		flipperSpeed = speed;
	}

	@Override
	public double getFlipperSpeed() {
		return flipperSpeed;
	}

	@Override
	public boolean isRight() {
		return isRight;
	}

}
