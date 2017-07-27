package model;

import physics.Vect;

/**
 * 
 * @author David, Paul
 *
 */
public interface IAbsorber extends IGizmo {

	IBall getNextBall();

	void absorbBall(IBall ball);

	Vect getBottomRightCoords();

	void fireBall();

	void updateFiring();
	
}
