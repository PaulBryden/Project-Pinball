package model;

import physics.Vect;

public class PhysicsEvaluator {

	GameModel model;
	
	public PhysicsEvaluator(GameModel model){
		
		this.model = model;
	}
	
	public void applyGravity(double tickTime) {
		for (IBall ball : model.getBalls()) {
			Vect v = ball.getVelo();
			Vect gravComponent = new Vect(0, Constants.GRAVITY * tickTime);
			ball.setVelo(v.plus(gravComponent));
		}
	}

	public void applyFriction(double tickTime) {
		for (IBall ball : model.getBalls()) {
			Vect v = ball.getVelo();
			double frictionScale = (1 - Constants.MU * tickTime - Constants.MU2 * v.length() * tickTime);
			ball.setVelo(v.times(frictionScale));
		}
	}

	
}
