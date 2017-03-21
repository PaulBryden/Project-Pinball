package model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import physics.Circle;
import physics.LineSegment;
import physics.Vect;

/**
 * 
 * @author Matt, David
 *
 */
abstract class AbstractGizmo implements IGizmo {

	protected String id;
	protected Vect coords;
	protected int gridWidth;
	protected int gridHeight;
	protected int rotation;
	protected Color colour;
	protected boolean isStatic;
	protected double coefficientOfReflection;
	protected List<LineSegment> lines;
	protected List<Circle> circles;
	protected List<IAction> actions;
	protected Set<IGizmo> triggers;

	/**
	 * 
	 * @param id
	 *            A unique ID
	 * @param coords
	 *            Top left corner
	 * @param gridWidth
	 *            Width in L
	 * @param gridHeight
	 *            Height in L
	 * @param colour
	 * @param isStatic
	 *            True if gizmo is not moving
	 */
	public AbstractGizmo(String id, Vect coords, int gridWidth, int gridHeight, Color colour, boolean isStatic) {
		this.id = id;
		this.coords = coords;
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;
		rotation = 0;
		this.colour = colour;
		this.isStatic = isStatic;
		this.coefficientOfReflection = Constants.DEFAULT_COR;
		actions = new ArrayList<>();
		triggers = new HashSet<>();
		circles = new ArrayList<>();
		lines = new ArrayList<>();
	}

	/**
	 * Populate the lists containing the circles and line segments that make up
	 * the gizmo. This needs to be called from the constructor, and may be
	 * called subsequently to update the gizmos locationt.
	 */
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
	public int getGridWidth() {
		return gridWidth;
	}

	@Override
	public int getGridHeight() {
		return gridHeight;
	}

	@Override
	public void rotate(int steps) {
		rotation += steps;
		rotation %= 4;
		generateLinesAndCircles();
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
	public Set<IGizmo> getGizmosToTrigger() {
		return triggers;
	}

	@Override
	public void onCollision(IBall ball) {
		// By default gizmos do nothing on collision.
	}

	@Override
	public double getCoefficientOfReflection() {
		return coefficientOfReflection;
	}
	
	@Override
	public void setCoefficientOfReflection(double cor) {
		this.coefficientOfReflection = cor;
	}

	@Override
	public String serializeGizmo() {
		String serializedGizmo = getType() + " " + getID() + " " + (int) this.getGridCoords().x() + " " + (int) this.getGridCoords().y() + " " + "\n";
		for (IGizmo gizmo : triggers) {
			serializedGizmo += "Connect " + this.getID() + " " + gizmo.getID() + "\n";
		}
		serializedGizmo += "Colour " + this.getID() + " " + this.getColour().getRGB() + "\n";
		return serializedGizmo;
	}

}
