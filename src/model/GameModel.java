package model;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import physics.Vect;

public class GameModel extends Observable implements IModel {
	
	BoardFileHandler fileHandler;
	private List<IGizmo> gizmos;
	private List<IBall> balls;
	private Map<Integer, ITrigger> keyPressedTriggers;
	private Map<Integer, ITrigger> keyReleasedTriggers;
	private Color backgroundColour;
	private CollisionEvaluator collisionEvaluator;
	private PhysicsEvaluator physicsEvaluator;
	private double gravity;
	private double mu;
	private double mu2;
	
	public GameModel() {
		reset();
		setDefaultPhysics();
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
		for (Absorber absorber : getAbsorbers()) {
			absorber.updateFiring();
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
	
	public List<Absorber> getAbsorbers() {
		List<Absorber> absorbers = new LinkedList<>();
		for (IGizmo gizmo : gizmos) {
			if (gizmo instanceof Absorber) {
				absorbers.add((Absorber) gizmo);
			}
		}
		return absorbers;
	
	}
	
	public IBall getBall(Vect coords) {
		for (IBall ball : balls) {
			Vect pos = ball.getCentre();
			double r = ball.getRadius();
			if (pos.x() + r > coords.x() && pos.x() - r < coords.x() + 1
					&& pos.y() + r > coords.y() && pos.y() - r < coords.y() + 1)
				return ball;
		}
		return null;
	}

	public IGizmo getGizmo(Vect coords) {
		for (IGizmo gizmo : gizmos) {
			Vect gizmoCoords = gizmo.getGridCoords();
			int width = gizmo.getGridWidth();
			int height = gizmo.getGridHeight();
			if (gizmoCoords != null && coords.x() >= gizmoCoords.x() && coords.x() < gizmoCoords.x() + width
					&& coords.y() >= gizmoCoords.y() && coords.y() < gizmoCoords.y() + height) {
				return gizmo;
			}
		}
		return null;
	}

	public boolean isCellEmpty(Vect coords) {
		return getGizmo(coords) == null && getBall(coords) == null;
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
	
	public void removeBall(IBall ball) {
		balls.remove(ball);
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

	@Override
	public double getGravity() {
		return gravity;
	}

	@Override
	public double getFrictionMu() {
		return mu;
	}

	@Override
	public double getFrictionMu2() {
		return mu2;
	}

	@Override
	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	@Override
	public void setFrictionMu(double mu) {
		this.mu = mu;
	}

	@Override
	public void setFrictionMu2(double mu2) {
		this.mu2 = mu2;
	}

	@Override
	public void setDefaultPhysics() {
		this.gravity = Constants.DEFAULT_GRAVITY;
		this.mu = Constants.DEFAULT_MU;
		this.mu2 = Constants.DEFAULT_MU2;
	}
}
