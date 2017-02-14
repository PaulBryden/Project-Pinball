package model;

import physics.Vect;

public class CollisionDetails {

	private double tuc;
	private Vect velo;
	private IBall ball;
	private IGizmo gizmo;

	public CollisionDetails(double t, Vect v, IBall ball, IGizmo gizmo) {
		tuc = t;
		velo = v;
		this.ball = ball;
		this.gizmo = gizmo;
	}

	public double getTuc() {
		return tuc;
	}

	public Vect getVelo() {
		return velo;
	}
	
	public IBall getBall() {
		return ball;
	}

	public IGizmo getGizmo() {
		return gizmo;
	}
}
