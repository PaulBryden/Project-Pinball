package model;

import physics.Geometry.VectPair;
import physics.Vect;

/**
 * 
 * @author Matt, David
 *
 */
public class CollisionDetails {

	private double tuc;
	private Vect velo;
	private IBall ball;
	private IGizmo gizmo;
	private Vect otherBallVelo;

	public CollisionDetails(double t, Vect v, IBall ball, IGizmo gizmo) {
		tuc = t;
		velo = v;
		this.ball = ball;
		this.gizmo = gizmo;
	}
	
	public CollisionDetails(double t, IBall ball, IBall other, VectPair velos) {
		this(t, velos.v1, ball, other);
		this.otherBallVelo = velos.v2;
	}

	public double getTuc() {
		return tuc;
	}

	public Vect getVelo() {
		return velo;
	}
	
	public Vect getOtherBallVelo() {
		return otherBallVelo;
	}
	
	public IBall getBall() {
		return ball;
	}

	public IGizmo getGizmo() {
		return gizmo;
	}
}
