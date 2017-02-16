package model;

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

	public GameModel() {

		walls = new LinkedList<>();
		walls.add(new Wall(0, 0, 0, 20));
		walls.add(new Wall(0, 0, 20, 0));
		walls.add(new Wall(20, 0, 20, 20));
		walls.add(new Wall(0, 20, 20, 20));

		gizmos = new LinkedList<>();
		balls = new LinkedList<>();
		
		keyPressedTriggers = new HashMap<>();
		keyReleasedTriggers = new HashMap<>();

		fileHandler = new BoardFileHandler(this);
		fileHandler.load("spec_save_file.txt");

		/*
		 * gizmos.add(new SquareGizmo("S35", 3, 5)); gizmos.add(new
		 * SquareGizmo("S1310", 13, 10)); gizmos.add(new SquareGizmo("S1319",
		 * 13, 19)); gizmos.add(new SquareGizmo("S142", 14, 2)); gizmos.add(new
		 * SquareGizmo("S416", 4, 16)); gizmos.add(new SquareGizmo("S516", 5,
		 * 16)); gizmos.add(new SquareGizmo("S616", 6, 16)); gizmos.add(new
		 * SquareGizmo("S716", 7, 16)); gizmos.add(new TriangleGizmo("T2", 1,
		 * 0)); TriangleGizmo triangle = new TriangleGizmo("T1", 0, 18);
		 * triangle.rotate(2); gizmos.add(triangle); Absorber absorber = new
		 * Absorber("A", 1,18,5,20, balls); gizmos.add(absorber); IFlipper
		 * flipper = new LeftFlipper("LF102", 10, 2); gizmos.add(flipper);
		 * IGizmo magicGizmo = new SquareGizmo("S1818", 18, 18);
		 * magicGizmo.addGizmoToTrigger(flipper); gizmos.add(magicGizmo);
		 * balls.add(new BallGizmo("B", 10, 11, 13, 17)); keyTriggers = new
		 * HashMap<>(); addKeyTrigger('b', flipper);
		 * absorber.addGizmoToTrigger(absorber); addKeyTrigger('a', absorber);
		 */
	}

	public void tick() {
		// Evaluate collisions for all items in Gizmolist
		CollisionDetails collision = evaluateCollisions();
		// Use smallest tick time until next collision.
		double tick = (collision == null) ? Constants.TICK_TIME : collision.getTuc();
		// Move all items based on that tick time
		for (IBall ball : balls) {
			ball.moveForTime(tick);
		}
		// Resolve collision
		if (collision != null) {
			double coeff = collision.getGizmo().getCoefficientOfReflection();
			Vect velo = collision.getVelo().times(coeff);
			velo = (velo.length() > Constants.MIN_VELOCITY) ? velo : Vect.ZERO;
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

	public List<IGizmo> getGizmos() {
		return gizmos;
	}

	public void updateGizmoList(List<IGizmo> gizmos) {
		this.gizmos = gizmos;
	}

	public void addGizmo(IGizmo gizmo) {
		gizmos.add(gizmo);
	}

	public void removeGizmo(IGizmo gizmo) {
		gizmos.remove(gizmo);
	}

	public void reset() {

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
	public void addBall(IBall ball) {
		this.balls.add(ball);
	}
}
