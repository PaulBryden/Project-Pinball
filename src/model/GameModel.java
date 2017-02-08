package model;

import java.util.List;
import java.util.Observable;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;

public class GameModel extends Observable {

	private GizmoList listOfGizmos;
	private List<BallGizmo> balls;
	private List<LineSegment> walls;
	private boolean pauseGame = false;
	private static final double TICK_TIME = 0.02; // in seconds
	
	public GameModel() {
		listOfGizmos = new GizmoList();
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

	private CollisionDetails evaluateCollisions() {
		CollisionDetails collision = null;
		CollisionDetails cd;
		for (BallGizmo ball : balls) {
			for (IGizmo gizmo : listOfGizmos.returnGizmoList()) {
				if (gizmo.isStatic()) {
					for (Circle circle : gizmo.getAllCircles()) {
						cd = evaluateCollisionWithStaticCircle(ball, circle, gizmo);
						if (cd.getTuc() < collision.getTuc() && cd.getTuc() < TICK_TIME)
							collision = cd;
					}
					for (LineSegment line : gizmo.getAllLineSegments()) {
						cd = evaluateCollisionWithStaticLine(ball, line, gizmo);
						if (cd.getTuc() < collision.getTuc() && cd.getTuc() < TICK_TIME)
							collision = cd;
					}
				}
			}
			for (LineSegment wall : walls) {
				cd = evaluateCollisionWithStaticLine(ball, wall, null);
				if (cd.getTuc() < collision.getTuc() && cd.getTuc() < TICK_TIME)
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
