package model;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import physics.Circle;
import physics.Geometry;
import physics.Geometry.VectPair;
import physics.LineSegment;
import physics.Vect;

public class GameModel extends Observable implements IModel {
	BoardFileHandler fileHandler;
	private List<IGizmo> gizmos;
	private List<IBall> balls;
	private Map<Integer, ITrigger> keyPressedTriggers;
	private Map<Integer, ITrigger> keyReleasedTriggers;
	private boolean pauseGame = false;
	private Color backgroundColour;
	private CollisionEvaluator collisionEvaluator;
	private PhysicsEvaluator physicsEvaluator;
	public GameModel() {
		reset();
	}

	public void tick() {
		// Evaluate collisions for all items in Gizmolist
		collisionEvaluator.evaluate();
		double tick = collisionEvaluator.getTickTime();
		// Move all items based on that tick time
		for (IBall ball : balls) {
			ball.moveForTime(tick);
		}
		for (IFlipper flipper : getFlippers()) {
			flipper.moveForTime(tick);
		}
		// Resolve collision
		
		collisionEvaluator.resolveCollision();
		// Apply friction and gravity
		physicsEvaluator.applyGravity(tick);
		physicsEvaluator.applyFriction(tick);
		
		// Update view
		setChanged();
		notifyObservers();
	}

	public List<IFlipper> getFlippers() {
		List<IFlipper> flippers = new LinkedList<>();
		for (IGizmo gizmo : gizmos) {
			if (gizmo instanceof IFlipper) {
				flippers.add((IFlipper) gizmo);
			}
		}
		return flippers;
	}

	public List<IGizmo> getGizmos() {
		return gizmos;
	}

	public void addGizmo(IGizmo gizmo) {
		gizmos.add(gizmo);
	}

	public void addBall(IBall ball) {
		balls.add(ball);
	}

	public void removeGizmo(IGizmo gizmo) {
		gizmos.remove(gizmo);
	}

	public void reset() {
		gizmos = new LinkedList<>();
		gizmos.add(new Wall(0, 0, 0, 20));
		gizmos.add(new Wall(0, 0, 20, 0));
		gizmos.add(new Wall(20, 0, 20, 20));
		gizmos.add(new Wall(0, 20, 20, 20));

		balls = new LinkedList<>();

		keyPressedTriggers = new HashMap<>();
		keyReleasedTriggers = new HashMap<>();

		backgroundColour = Constants.BACKGROUND_DEFAULT_COLOUR;
		collisionEvaluator = new CollisionEvaluator(this);
		physicsEvaluator = new PhysicsEvaluator(this);
	}

	public List<IBall> getBalls() {
		return balls;
	}

	@Override
	public void processKeyPressedTrigger(int keyCode) {
		if (keyPressedTriggers.containsKey(keyCode)) {
			keyPressedTriggers.get(keyCode).triggerConnectedGizmos();
		}
	}

	@Override
	public void processKeyReleasedTrigger(int keyCode) {
		if (keyReleasedTriggers.containsKey(keyCode)) {
			keyReleasedTriggers.get(keyCode).triggerConnectedGizmos();
		}
	}

	@Override
	public void addKeyPressedTrigger(int keyCode, IGizmo gizmo) {
		if (keyPressedTriggers.containsKey(keyCode)) {
			keyPressedTriggers.get(keyCode).addGizmoToTrigger(gizmo);
		} else {
			keyPressedTriggers.put(keyCode, new KeyTrigger(gizmo));
		}
	}

	@Override
	public void addKeyReleasedTrigger(int keyCode, IGizmo gizmo) {
		if (keyReleasedTriggers.containsKey(keyCode)) {
			keyReleasedTriggers.get(keyCode).addGizmoToTrigger(gizmo);
		} else {
			keyReleasedTriggers.put(keyCode, new KeyTrigger(gizmo));
		}
	}

	@Override
	public Color getBackgroundColour() {
		return this.backgroundColour;
	}

	@Override
	public void setBackgroundColour(Color colour) {
		this.backgroundColour = colour;
	}

	public Map<Integer, ITrigger> getKeyPressedTriggers() {
		return keyPressedTriggers;
	}

	public Map<Integer, ITrigger> getKeyReleasedTriggers() {
		return keyReleasedTriggers;
	}
}
