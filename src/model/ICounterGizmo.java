package model;

import physics.Vect;

public interface ICounterGizmo extends IGizmo {
	
	int getCount();
	
	Vect getBottomRightCoords();

	void increment();

	void reset();

}
