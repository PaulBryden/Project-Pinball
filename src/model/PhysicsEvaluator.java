package model;

import physics.Vect;

/**
 * 
 * @author Matt, David
 *
 */
public class PhysicsEvaluator {

	IModel model;
	
	public PhysicsEvaluator(IModel model){
		
		this.model = model;
	}
	
	public void applyGravity(double tickTime) {
		for (IBall ball : model.getBalls()) {
			Vect v = ball.getVelo();
			Vect gravComponent = new Vect(0, model.getGravity() * tickTime);
			ball.setVelo(v.plus(gravComponent));
		}
	}

	public void applyFriction(double tickTime) {
		for (IBall ball : model.getBalls()) {
			Vect v = ball.getVelo();
			double frictionScale = (1 - model.getFrictionMu() * tickTime - model.getFrictionMu2() * v.length() * tickTime);
			ball.setVelo(v.times(frictionScale));
		}
	}

	
}
