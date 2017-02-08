package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;

public class GameModel {

	private GizmoList listOfGizmos;
	private List<BallGizmo> balls;
	private boolean pauseGame = false;
	private static final double FLOAT_MARGIN = 0.0000001;

	public GameModel() {
		listOfGizmos = new GizmoList();
	}

	public void tick() {
		// Evaluate collisions for all items in Gizmolist
		// Use smallest tick time until next collision.
		// Move all items based on that tick time
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

	private List<CollisionDetails> evaluateCollisions() {
		List<CollisionDetails> details = new LinkedList<>();
		double tuc;
		for (BallGizmo ball : balls) {
			CollisionDetails cd;
			for (IGizmo gizmo : listOfGizmos.returnGizmoList()) {
				evaluateCollisions(ball, gizmo);
			}
		}

		return details;
	}

	private List<CollisionDetails> evaluateCollisions(BallGizmo ball, IGizmo gizmo) {
		if (gizmo.isStatic())
			return evaluateCollisionsWithStaticGizmo(ball, gizmo);
		else
			return null;
		// TODO Add collisions with balls and flippers
	}

	private List<CollisionDetails> evaluateCollisionsWithStaticGizmo(BallGizmo ball, IGizmo gizmo) {
		List<CollisionDetails> details = new LinkedList<>();
		double tuc = -1;
		Circle ballCircle = ball.getAllCircles().get(0);
		for (Circle circle : gizmo.getAllCircles()) {
			double newTuc = Geometry.timeUntilCircleCollision(circle, ballCircle, ball.getVelo());
			if (newTuc < tuc - FLOAT_MARGIN || (tuc < 0 && newTuc != Double.POSITIVE_INFINITY)) {
				tuc = newTuc;
				details.clear();
				details.add(new CollisionDetails(newTuc,
						Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ball.getVelo())));
			} else if (newTuc < tuc + FLOAT_MARGIN) {
				details.add(new CollisionDetails(newTuc,
						Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ball.getVelo())));
			}
		}
		for (LineSegment line : gizmo.getAllLineSegments()) {
			double newTuc = Geometry.timeUntilWallCollision(line, ballCircle, ball.getVelo());
			if (newTuc < tuc - FLOAT_MARGIN || (tuc < 0 && newTuc != Double.POSITIVE_INFINITY)) {
				tuc = newTuc;
				details.clear();
				details.add(new CollisionDetails(newTuc, Geometry.reflectWall(line, ball.getVelo())));
			} else if (newTuc < tuc + FLOAT_MARGIN) {
				details.add(new CollisionDetails(newTuc, Geometry.reflectWall(line, ball.getVelo())));
			}
		}
		return details;
	}

}
