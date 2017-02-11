package model;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;

public class GameModel extends Observable {

	private GizmoList listOfGizmos;
	private List<IGizmo> gizmos;
	private List<BallGizmo> balls;
	private List<LineSegment> walls;
	private boolean pauseGame = false;
	private static final double TICK_TIME = 0.02; // in seconds

	public GameModel() {
		//listOfGizmos = new GizmoList();
		gizmos = new LinkedList<>();
		balls = new LinkedList<>();
		walls = new LinkedList<>();
		walls.add(new LineSegment(0, 0, 0, 20));
		walls.add(new LineSegment(0, 0, 20, 0));
		walls.add(new LineSegment(20, 0, 20, 20));
		walls.add(new LineSegment(0, 20, 20, 20));
		gizmos.add(new SquareGizmo(1,3, 5));
		gizmos.add(new SquareGizmo(2,18, 18));
		gizmos.add(new SquareGizmo(3,13, 10));
		gizmos.add(new SquareGizmo(4,13, 19));
		gizmos.add(new SquareGizmo(5,14, 2));
		gizmos.add(new SquareGizmo(6,4, 16));
		gizmos.add(new SquareGizmo(7,5, 16));
		gizmos.add(new SquareGizmo(8,6, 16));
		gizmos.add(new SquareGizmo(9, 7, 16));
		balls.add(new BallGizmo(0.3, 10, 10, 11, 9, 0));
	}

	public void tick() {
		// Evaluate collisions for all items in Gizmolist
		CollisionDetails collision = evaluateCollisions();
		// Use smallest tick time until next collision.
		double tick = (collision == null) ? TICK_TIME : collision.getTuc();
		// Move all items based on that tick time
		for (BallGizmo ball : balls) {
			ball.moveForTime(tick);
		}
		// Resolve collision
		if (collision != null) {
			collision.getBall().setVelo(collision.getVelo());
		}
		// TODO Apply friction and gravity here
		// Trigger any gizmos that have been collided with
		if (collision != null && collision.getGizmo() != null)
			collision.getGizmo().triggerConnectedGizmos();
		// Update view
		setChanged();
		notifyObservers();
	}

	public GizmoList getGizmoList() {
		return listOfGizmos;
	}

	public void updateGizmoList(GizmoList gizmos) {
		this.listOfGizmos = gizmos;
	}

	public void addGizmo(IGizmo gizmo) {
		listOfGizmos.addGizmo(gizmo);
	}

	public void removeGizmo(IGizmo gizmo) {

		listOfGizmos.removeGizmo(gizmo);

	}

	public void reset() {

	}

	public List<BallGizmo> getBalls() {
		return balls;
	}

	public List<IGizmo> getGizmos() {
		return gizmos;
	}
	
	public List<LineSegment> getWalls() {
		return walls;
	}

	private CollisionDetails evaluateCollisions() {
		CollisionDetails collision = null;
		CollisionDetails cd;
		for (BallGizmo ball : balls) {
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
			for (LineSegment wall : walls) {
				cd = evaluateCollisionWithStaticLine(ball, wall, null);
				if (cd != null && (collision == null || cd.getTuc() < collision.getTuc()) && cd.getTuc() < TICK_TIME)
					collision = cd;
			}
		}
		return collision;
	}

	private CollisionDetails evaluateCollisionWithStaticCircle(BallGizmo ball, Circle circle, IGizmo gizmo) {
		Circle ballCircle = ball.getAllCircles().get(0);
		double tuc = Geometry.timeUntilCircleCollision(circle, ballCircle, ball.getVelo());
		if (tuc == Double.POSITIVE_INFINITY)
			return null;
		return new CollisionDetails(tuc,
				Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ball.getVelo()), ball, gizmo);
	}

	private CollisionDetails evaluateCollisionWithStaticLine(BallGizmo ball, LineSegment line, IGizmo gizmo) {
		Circle ballCircle = ball.getAllCircles().get(0);
		double tuc = Geometry.timeUntilWallCollision(line, ballCircle, ball.getVelo());
		if (tuc == Double.POSITIVE_INFINITY)
			return null;
		return new CollisionDetails(tuc, Geometry.reflectWall(line, ball.getVelo()), ball, gizmo);
	}
}
