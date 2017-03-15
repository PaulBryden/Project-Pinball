package model;

import physics.Vect;

/**
 * 
 * Class for evaluating physical calculations such as friction and gravity. The
 * physics constants used for this will be taken from the IModel provided in the
 * constructor.
 * 
 * @author Matt, David
 *
 */
public class PhysicsEvaluator {

	IModel model;

	/**
	 * 
	 * @param model
	 *            The model to evaluate
	 */
	public PhysicsEvaluator(IModel model) {
		this.model = model;
	}

	/**
	 * 
	 * Calculate and apply the amount of gravity to all balls in the model.
	 * 
	 * @param tickTime
	 *            The amount of time passed since the last tick
	 */
	public void applyGravity(double tickTime) {
		for (IBall ball : model.getBalls()) {
			Vect v = ball.getVelo();
			Vect gravComponent = new Vect(0, model.getGravity() * tickTime);
			ball.setVelo(v.plus(gravComponent));
		}
	}

	/**
	 * 
	 * Calculate and apply the amount of friction to all balls in the model.
	 * 
	 * @param tickTime
	 *            The amount of time passed since the last tick
	 */
	public void applyFriction(double tickTime) {
		for (IBall ball : model.getBalls()) {
			Vect v = ball.getVelo();
			double frictionScale = (1 - model.getFrictionMu() * tickTime
					- model.getFrictionMu2() * v.length() * tickTime);
			ball.setVelo(v.times(frictionScale));
		}
	}

}
