package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

public abstract class AbstractGizmo implements IGizmo {

	protected String id;
	protected Vect coords;
	protected int rotation;
	protected Color colour;
	protected boolean isStatic;
	protected double coefficientOfReflection;
	protected List<LineSegment> lines;
	protected List<Circle> circles;
	protected List<IAction> actions;
	protected List<IGizmo> triggers;

	public AbstractGizmo(String id, Vect coords, Color colour, boolean isStatic) {
		this.id = id;
		this.coords = coords;
		rotation = 0;
		this.colour = colour;
		this.isStatic = isStatic;
		this.coefficientOfReflection = Constants.DEFAULT_COR;
		actions = new ArrayList<>();
		triggers = new ArrayList<>();
		circles = new ArrayList<>();
		lines = new ArrayList<>();
	}

	protected abstract void generateLinesAndCircles();

	@Override
	public String getID() {
		return id;
	}

	@Override
	public void setGridCoords(Vect coords) {
		this.coords = coords;
		generateLinesAndCircles();
	}

	@Override
	public Vect getGridCoords() {
		return coords;
	}

	@Override
	public void rotate(int steps) {
		rotation += steps;
		rotation %= steps;
	}

	@Override
	public int getRotation() {
		return rotation;
	}

	@Override
	public void setColour(Color colour) {
		this.colour = colour;
	}

	@Override
	public Color getColour() {
		return colour;
	}

	@Override
	public boolean isStatic() {
		return isStatic;
	}

	@Override
	public List<LineSegment> getAllLineSegments() {
		return lines;
	}

	@Override
	public List<Circle> getAllCircles() {
		return circles;
	}

	@Override
	public void addTriggerAction(IAction action) {
		actions.add(action);
	}

	@Override
	public void performActions() {
		for (IAction action : actions) {
			action.performAction();
		}
	}

	@Override
	public void addGizmoToTrigger(IGizmo gizmo) {
		triggers.add(gizmo);
	}

	@Override
	public void triggerConnectedGizmos() {
		for (IGizmo gizmo : triggers) {
			gizmo.performActions();
		}
	}

	@Override
	public List<IGizmo> getGizmosToTrigger() {
		return triggers;
	}

	@Override
	public void onCollision(IBall ball) {
		// TODO Auto-generated method stub

	}
	
	public String serializeGizmo() {
		String serializedGizmo = getID() + " " + this.getGridCoords().x() + " " + this.getGridCoords().y() + " "
				+ "\n";
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + this.getID() + " " + gizmo.getID() + "\n";
		}
		return serializedGizmo;
	}

	public double getCoefficientOfReflection() {
		return coefficientOfReflection;
	}

}
