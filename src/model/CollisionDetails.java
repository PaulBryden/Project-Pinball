package model;

import physics.Geometry.VectPair;
import physics.Vect;

/**
 * 
 * Collision details contain the details of a single collision between a ball
 * and one other object (which may also be a ball). It contains methods for
 * accessing the time until collision, the velocity of the ball(s) resulting
 * from the collision, and the ball and gizmo involved in the collision.
 * 
 * @author Matt, David
 *
 */
public class CollisionDetails {

	private double tuc;
	private Vect v;
	private IBall ball;
	private IGizmo gizmo;
	private Vect otherBallV;

	public CollisionDetails(double t, IBall ball, Vect v, IGizmo gizmo) {
		tuc = t;
		this.v = v;
		this.ball = ball;
		this.gizmo = gizmo;
	}

	public CollisionDetails(double t, IBall ball, IBall other, VectPair vs) {
		this(t, ball, vs.v1, other);
		this.otherBallV = vs.v2;
	}

	public double getTuc() {
		return tuc;
	}

	public Vect getVelo() {
		return v;
	}

	public Vect getOtherBallVelo() {
		return otherBallV;
	}

	public IBall getBall() {
		return ball;
	}

	public IGizmo getGizmo() {
		return gizmo;
	}
}
