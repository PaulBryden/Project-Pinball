package model;

import physics.LineSegment;
import physics.Vect;

public interface IWall extends IGizmo {
	
	public LineSegment getLine();
	
	public Vect p1();

	public Vect p2();

}
