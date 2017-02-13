package model;

import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;

public class GameModel extends Observable implements IModel {

	private List<IGizmo> gizmos;
	private List<IBall> balls;
	private List<IWall> walls;
	private boolean pauseGame = false;
	private IFlipper flipper;
	private static final double TICK_TIME = 0.01; // in seconds

	public GameModel() {
		//listOfGizmos = new GizmoList();
		gizmos = new LinkedList<>();
		balls = new LinkedList<>();
		walls = new LinkedList<>();
		walls.add(new Wall(0, 0, 0, 20));
		walls.add(new Wall(0, 0, 20, 0));
		walls.add(new Wall(20, 0, 20, 20));
		walls.add(new Wall(0, 20, 20, 20));
		gizmos.add(new SquareGizmo(1, 3, 5));
		gizmos.add(new SquareGizmo(2,18, 18));
		gizmos.add(new SquareGizmo(3,13, 10));
		gizmos.add(new SquareGizmo(4,13, 19));
		gizmos.add(new SquareGizmo(5,14, 2));
		gizmos.add(new SquareGizmo(6,4, 16));
		gizmos.add(new SquareGizmo(7,5, 16));
		gizmos.add(new SquareGizmo(8,6, 16));
		gizmos.add(new SquareGizmo(9, 7, 16));
		flipper = new LeftFlipper(1, 10, 2);
		gizmos.add(flipper);
		balls.add(new BallGizmo(10, 10, 11, 13, 17));
	}

	public void tick() {
		// Evaluate collisions for all items in Gizmolist
		CollisionDetails collision = evaluateCollisions();
		// Use smallest tick time until next collision.
		double tick = (collision == null) ? TICK_TIME : collision.getTuc();
		// Move all items based on that tick time
		for (IBall ball : balls) {
			ball.moveForTime(tick);
		}
		// Resolve collision
		if (collision != null) {
			collision.getBall().setVelo(collision.getVelo());
		}
		// TODO Apply friction and gravity here
		// Trigger any gizmos that have been collided with
		if (collision != null && collision.getTrigger() != null)
			collision.getTrigger().triggerConnectedGizmos();
		// Update view
		for (IGizmo gizmo : gizmos) {
			if (gizmo instanceof IFlipper) {
				((IFlipper) gizmo).moveForTime(tick);
			}
		}
		setChanged();
		notifyObservers();
	}
	
	public double getTickTime() {
		return TICK_TIME;
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
			for (IGizmo gizmo : gizmos) { // .returnGizmoList()) {
				if (gizmo.isStatic()) {
					for (Circle circle : gizmo.getAllCircles()) {
						cd = evaluateCollisionWithStaticCircle(ball, circle, gizmo);
						if (cd != null && (collision == null || cd.getTuc() < collision.getTuc()) && cd.getTuc() < TICK_TIME)
							collision = cd;
					}
					for (LineSegment line : gizmo.getAllLineSegments()) {
						cd = evaluateCollisionWithStaticLine(ball, line, gizmo);
						if (cd != null && (collision == null || cd.getTuc() < collision.getTuc()) && cd.getTuc() < TICK_TIME)
							collision = cd;
					}
				}
			}
			for (IWall wall : walls) {
				cd = evaluateCollisionWithStaticLine(ball, wall.getLine(), wall);
				if (cd != null && (collision == null || cd.getTuc() < collision.getTuc()) && cd.getTuc() < TICK_TIME)
					collision = cd;
			}
		}
		return collision;
	}

	private CollisionDetails evaluateCollisionWithStaticCircle(IBall ball, Circle circle, ITrigger trigger) {
		Circle ballCircle = ball.getAllCircles().get(0);
		double tuc = Geometry.timeUntilCircleCollision(circle, ballCircle, ball.getVelo());
		if (tuc == Double.POSITIVE_INFINITY)
			return null;
		return new CollisionDetails(tuc,
				Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ball.getVelo()), ball, trigger);
	}

	private CollisionDetails evaluateCollisionWithStaticLine(IBall ball, LineSegment line, ITrigger trigger) {
		Circle ballCircle = ball.getAllCircles().get(0);
		double tuc = Geometry.timeUntilWallCollision(line, ballCircle, ball.getVelo());
		if (tuc == Double.POSITIVE_INFINITY)
			return null;
		return new CollisionDetails(tuc, Geometry.reflectWall(line, ball.getVelo()), ball, trigger);
	}

	@Override
	public void handleKeyEvent(KeyEvent e) {
		if (e.getKeyChar() == 'f')
			flipper.performActions();
	}
}
