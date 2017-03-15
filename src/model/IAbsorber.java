package model;

import physics.Vect;

public interface IAbsorber extends IGizmo {

	IBall getNextBall();

	void absorbBall(IBall ball);

	Vect getBottomRightCoords();
	
}
