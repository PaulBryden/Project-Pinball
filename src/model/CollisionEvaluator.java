package model;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import physics.Geometry.VectPair;

/**
 * 
 * Class for evaluating and resolving collisions.
 * 
 * @author Matt, David
 *
 */
public class CollisionEvaluator {

	private IModel model;
	private CollisionDetails collision;

	public CollisionEvaluator(IModel model) {
		this.model = model;
	}

	/**
	 * Return the time until the next collision, the time until a flipper stops
	 * moving, or the regular tick time, whichever is shortest. This method
	 * expects evaluate() to have been called first.
	 * 
	 * @return The evaluated tick time
	 */
	public double getTickTime() {
		// Use smallest tick time until next collision.
		double tick = (collision == null) ? Constants.TICK_TIME : collision
				.getTuc();
		// Check if flipper will stop moving within the tick time.
		for (IFlipper flipper : model.getFlippers()) {
			double tus = flipper.timeUntilStatic();
			if (tus < tick && tus > Constants.FLOAT_MARGIN) {
				collision = null;
				tick = tus;
			}
		}
		return tick;
	}

	/**
	 * Get the collision previously found by evaluate().
	 * 
	 * @return The collision details for the next collision, or null if there is
	 *         no collision within the next tick time
	 */
	public CollisionDetails getCollision() {
		return this.collision;
	}

	/**
	 * Update the velocities of balls based on the collision found by
	 * evaluate(), and trigger any gizmo that has been collided with.
	 */
	public void resolveCollision() {
		if (collision != null) {
			if (collision.getGizmo() instanceof IBall) {
				IBall other = (IBall) collision.getGizmo();
				resolveSingleCollision(other, collision.getBall(),
						collision.getOtherBallVelo());
			}
			resolveSingleCollision(collision.getBall(), collision.getGizmo(),
					collision.getVelo());
		}
		// Trigger any gizmos that have been collided with
		if (collision != null && collision.getGizmo() != null) {
			collision.getGizmo().onCollision(collision.getBall());
			collision.getGizmo().triggerConnectedGizmos();
		}
	}

	private void resolveSingleCollision(IBall ball, IGizmo gizmo, Vect velo) {
		double coeff = gizmo.getCoefficientOfReflection();
		Vect initialVelo = ball.getVelo();
		velo = Geometry.applyReflectionCoeff(initialVelo, velo, coeff);
		velo = (velo.length() > Constants.MIN_VELOCITY) ? velo : Vect.ZERO;

		/*
		 * double velox = (Math.abs(velo.x()) > -0.1) ? velo.x() : 0.0; double
		 * veloy = (Math.abs(velo.y()) > Constants.MIN_VELOCITY) ? velo.y() :
		 * 0.0; velo = new Vect(velox,veloy);
		 */
		ball.setVelo(velo);
	}

	/**
	 * Evaluate all possible collisions, and find the first collision to occur
	 * within the next tick time. CollisionDetails corresponding to the
	 * collision can then be accessed using getCollision(). Collision will be
	 * null if no collisions occur within the next tick time, as defined in
	 * Constants.
	 */
	public void evaluate() {
		collision = null;
		CollisionDetails cd;
		for (IBall ball : model.getBalls()) {
			for (IGizmo gizmo : model.getGizmos()) {
				if (gizmo.isStatic()) {
					for (Circle circle : gizmo.getAllCircles()) {
						cd = evaluateCollisionWithStaticCircle(ball, circle,
								gizmo);
						collision = soonerCollision(cd, collision);
					}
					for (LineSegment line : gizmo.getAllLineSegments()) {
						cd = evaluateCollisionWithStaticLine(ball, line, gizmo);
						collision = soonerCollision(cd, collision);
					}
				}
			}
			for (IFlipper flipper : model.getFlippers()) {
				if (!flipper.isStatic()) {
					for (Circle circle : flipper.getAllCircles()) {
						cd = evaluateCollisionWithRotatingCircle(ball, circle,
								flipper);
						collision = soonerCollision(cd, collision);
					}
					for (LineSegment line : flipper.getAllLineSegments()) {
						cd = evaluateCollisionWithRotatingLine(ball, line,
								flipper);
						collision = soonerCollision(cd, collision);
					}
				}
			}
			for (ISpinner spinner : model.getSpinners()) {
				for (Circle circle : spinner.getAllCircles()) {
					cd = evaluateCollisionWithRotatingCircle(ball, circle,
							spinner);
					collision = soonerCollision(cd, collision);
				}
				for (LineSegment line : spinner.getAllLineSegments()) {
					cd = evaluateCollisionWithRotatingLine(ball, line, spinner);
					collision = soonerCollision(cd, collision);
				}

			}
			for (IBall other : model.getBalls()) {
				if (!ball.equals(other)) {
					cd = evaluateCollisionWithBall(ball, other);
					collision = soonerCollision(cd, collision);
				}
			}
		}
	}

	private CollisionDetails evaluateCollisionWithRotatingCircle(IBall ball,
			Circle circle, ISpinner spinner) {
		Circle ballCircle = ball.getAllCircles().get(0);
		double tuc = Geometry.timeUntilRotatingCircleCollision(circle,
				spinner.getPivot(), spinner.getAngularVelocity(), ballCircle,
				ball.getVelo());
		if (tuc == Double.POSITIVE_INFINITY)
			return null;
		Vect newVelo = Geometry.reflectRotatingCircle(circle,
				spinner.getPivot(), spinner.getAngularVelocity(), ballCircle,
				ball.getVelo());
		return new CollisionDetails(tuc, ball, newVelo, spinner);
	}

	private CollisionDetails evaluateCollisionWithRotatingLine(IBall ball,
			LineSegment line, ISpinner spinner) {
		Circle ballCircle = ball.getAllCircles().get(0);
		double tuc = Geometry.timeUntilRotatingWallCollision(line,
				spinner.getPivot(), spinner.getAngularVelocity(), ballCircle,
				ball.getVelo());
		if (tuc == Double.POSITIVE_INFINITY)
			return null;
		Vect newVelo = Geometry.reflectRotatingWall(line, spinner.getPivot(),
				spinner.getAngularVelocity(), ballCircle, ball.getVelo());
		return new CollisionDetails(tuc, ball, newVelo, spinner);
	}

	/**
	 * Return the sooner of two collisions, or null if neither collision occurs
	 * within the next tick time.
	 * 
	 * @param cd1
	 *            Collision 1
	 * @param cd2
	 *            Collision 2
	 * @return The sooner of the two collisions, provided at least one of them
	 *         occurs within the next tick time.
	 */
	private CollisionDetails soonerCollision(CollisionDetails cd1,
			CollisionDetails cd2) {
		if (cd1 != null && cd1.getTuc() > Constants.TICK_TIME)
			cd1 = null;
		if (cd2 != null && cd2.getTuc() > Constants.TICK_TIME)
			cd2 = null;
		if (cd1 == null)
			return cd2;
		if (cd2 == null)
			return cd1;
		return (cd1.getTuc() < cd2.getTuc()) ? cd1 : cd2;
	}

	private CollisionDetails evaluateCollisionWithStaticCircle(IBall ball,
			Circle circle, IGizmo gizmo) {
		Circle ballCircle = ball.getAllCircles().get(0);
		double tuc = Geometry.timeUntilCircleCollision(circle, ballCircle,
				ball.getVelo());
		if (tuc == Double.POSITIVE_INFINITY)
			return null;
		Vect newVelo = Geometry.reflectCircle(circle.getCenter(),
				ballCircle.getCenter(), ball.getVelo());
		return new CollisionDetails(tuc, ball, newVelo, gizmo);
	}

	private CollisionDetails evaluateCollisionWithBall(IBall ball,
			IBall otherBall) {
		Circle ballCircle = ball.getAllCircles().get(0);
		Circle otherBallCircle = otherBall.getAllCircles().get(0);
		double tuc = Geometry.timeUntilBallBallCollision(otherBallCircle,
				otherBall.getVelo(), ballCircle, ball.getVelo());
		if (tuc == Double.POSITIVE_INFINITY)
			return null;
		VectPair velos = Geometry.reflectBalls(ballCircle.getCenter(), 1,
				ball.getVelo(), otherBallCircle.getCenter(), 1,
				otherBall.getVelo());
		return new CollisionDetails(tuc, ball, otherBall, velos);
	}

	private CollisionDetails evaluateCollisionWithRotatingCircle(IBall ball,
			Circle circle, IFlipper flipper) {
		Circle ballCircle = ball.getAllCircles().get(0);
		double tuc = Geometry.timeUntilRotatingCircleCollision(circle,
				flipper.getPivot(), flipper.getAngularVelocity(), ballCircle,
				ball.getVelo());
		if (tuc == Double.POSITIVE_INFINITY)
			return null;
		Vect newVelo = Geometry.reflectRotatingCircle(circle,
				flipper.getPivot(), flipper.getAngularVelocity(), ballCircle,
				ball.getVelo());
		return new CollisionDetails(tuc, ball, newVelo, flipper);
	}

	private CollisionDetails evaluateCollisionWithStaticLine(IBall ball,
			LineSegment line, IGizmo gizmo) {
		Circle ballCircle = ball.getAllCircles().get(0);
		double tuc = Geometry.timeUntilWallCollision(line, ballCircle,
				ball.getVelo());
		if (tuc == Double.POSITIVE_INFINITY)
			return null;
		Vect newVelo = Geometry.reflectWall(line, ball.getVelo());
		return new CollisionDetails(tuc, ball, newVelo, gizmo);
	}

	private CollisionDetails evaluateCollisionWithRotatingLine(IBall ball,
			LineSegment line, IFlipper flipper) {
		Circle ballCircle = ball.getAllCircles().get(0);
		double tuc = Geometry.timeUntilRotatingWallCollision(line,
				flipper.getPivot(), flipper.getAngularVelocity(), ballCircle,
				ball.getVelo());
		if (tuc == Double.POSITIVE_INFINITY)
			return null;
		Vect newVelo = Geometry.reflectRotatingWall(line, flipper.getPivot(),
				flipper.getAngularVelocity(), ballCircle, ball.getVelo());
		return new CollisionDetails(tuc, ball, newVelo, flipper);
	}
}
