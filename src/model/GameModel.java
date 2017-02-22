package model;

import java.awt.Color;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

public class GameModel extends Observable implements IModel {

	BoardFileHandler fileHandler;
	private List<IGizmo> gizmos;
	private List<IBall> balls;
	private List<IWall> walls;
	private Map<Integer, ITrigger> keyPressedTriggers;
	private Map<Integer, ITrigger> keyReleasedTriggers;
	private boolean pauseGame = false;
	private Color backgroundColour;

	public GameModel() {
		reset();
	}

	public void tick() {
		// Evaluate collisions for all items in Gizmolist
		CollisionDetails collision = evaluateCollisions();
		// Use smallest tick time until next collision.
		double tick = (collision == null) ? Constants.TICK_TIME : collision.getTuc();
		// Check if flipper will stop moving within the tick time.
		for (IFlipper flipper : getFlippers()) {
			double tus = flipper.timeUntilStatic(); 
			if (tus < tick && tus > 0) {
				collision = null;
				tick = tus;
			}
		}
		// Move all items based on that tick time
		for (IBall ball : balls) {
			ball.moveForTime(tick);
		}
		// Resolve collision
		if (collision != null) {
			double coeff = collision.getGizmo().getCoefficientOfReflection();
			Vect velo = collision.getVelo().times(coeff);
			velo = (velo.length() > Constants.MIN_VELOCITY) ? velo : Vect.ZERO;
			
			/*double velox = (Math.abs(velo.x()) > -0.1) ? velo.x() : 0.0;
			double veloy = (Math.abs(velo.y()) > Constants.MIN_VELOCITY) ? velo.y() : 0.0;
			velo = new Vect(velox,veloy);*/
			collision.getBall().setVelo(velo);
		}
		
		applyGravity(tick);
		applyFriction(tick);
		// Trigger any gizmos that have been collided with
		if (collision != null && collision.getGizmo() != null) {
			collision.getGizmo().onCollision(collision.getBall());
			collision.getGizmo().triggerConnectedGizmos();
		}
		// Update view
		for (IGizmo gizmo : gizmos) {
			if (gizmo instanceof IFlipper) {
				((IFlipper) gizmo).moveForTime(tick);
			}
		}
		setChanged();
		notifyObservers();
	}
	
	private List<IFlipper> getFlippers() {
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
		walls = new LinkedList<>();
		walls.add(new Wall(0, 0, 0, 20));
		walls.add(new Wall(0, 0, 20, 0));
		walls.add(new Wall(20, 0, 20, 20));
		walls.add(new Wall(0, 20, 20, 20));

		gizmos = new LinkedList<>();
		balls = new LinkedList<>();
		
		keyPressedTriggers = new HashMap<>();
		keyReleasedTriggers = new HashMap<>();
		
		backgroundColour = Constants.BACKGROUND_DEFAULT_COLOUR;
	}

	public List<IBall> getBalls() {
		return balls;
	}

	public List<IWall> getWalls() {
		return walls;
	}

	private CollisionDetails evaluateCollisions() {
		CollisionDetails collision = null;
		CollisionDetails cd;
		for (IBall ball : balls) {
			for (IGizmo gizmo : gizmos) {
				if (gizmo.isStatic()) {
					for (Circle circle : gizmo.getAllCircles()) {
						cd = evaluateCollisionWithStaticCircle(ball, circle, gizmo);
						if (cd != null && (collision == null || cd.getTuc() < collision.getTuc())
								&& cd.getTuc() < Constants.TICK_TIME)
							collision = cd;
					}
					for (LineSegment line : gizmo.getAllLineSegments()) {
						cd = evaluateCollisionWithStaticLine(ball, line, gizmo);
						if (cd != null && (collision == null || cd.getTuc() < collision.getTuc())
								&& cd.getTuc() < Constants.TICK_TIME)
							collision = cd;
					}
				}
			}
			for (IFlipper flipper : getFlippers()) {
				// evaluate flipper collisions
			}
			for (IWall wall : walls) {
				cd = evaluateCollisionWithStaticLine(ball, wall.getLine(), wall);
				if (cd != null && (collision == null || cd.getTuc() < collision.getTuc())
						&& cd.getTuc() < Constants.TICK_TIME)
					collision = cd;
			}
		}
		return collision;
	}

	private CollisionDetails evaluateCollisionWithStaticCircle(IBall ball, Circle circle, IGizmo gizmo) {
		Circle ballCircle = ball.getAllCircles().get(0);
		double tuc = Geometry.timeUntilCircleCollision(circle, ballCircle, ball.getVelo());
		if (tuc == Double.POSITIVE_INFINITY)
			return null;
		return new CollisionDetails(tuc,
				Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ball.getVelo()), ball, gizmo);
	}

	private CollisionDetails evaluateCollisionWithStaticLine(IBall ball, LineSegment line, IGizmo gizmo) {
		Circle ballCircle = ball.getAllCircles().get(0);
		double tuc = Geometry.timeUntilWallCollision(line, ballCircle, ball.getVelo());
		if (tuc == Double.POSITIVE_INFINITY)
			return null;
		return new CollisionDetails(tuc, Geometry.reflectWall(line, ball.getVelo()), ball, gizmo);
	}

	private void applyGravity(double tickTime) {
		for (IBall ball : balls) {
			Vect v = ball.getVelo();
			Vect gravComponent = new Vect(0, Constants.GRAVITY * tickTime);
			ball.setVelo(v.plus(gravComponent));
		}
	}

	private void applyFriction(double tickTime) {
		for (IBall ball : balls) {
			Vect v = ball.getVelo();
			double frictionScale = (1 - Constants.MU * tickTime - Constants.MU2 * v.length() * tickTime);
			ball.setVelo(v.times(frictionScale));
		}
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
