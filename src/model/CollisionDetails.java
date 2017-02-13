package model;

import physics.Vect;

public class CollisionDetails {

	private double tuc;
	private Vect velo;
	private IBall ball;
	private ITrigger trigger;

	public CollisionDetails(double t, Vect v, IBall ball, ITrigger trigger) {
		tuc = t;
		velo = v;
		this.ball = ball;
		this.trigger = trigger;
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

	public ITrigger getTrigger() {
		return trigger;
	}
}
