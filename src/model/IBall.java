package model;

import physics.Vect;

public interface IBall extends ICircle{
	public Vect getVelo();
	public void setVelo(Vect v);
	public void moveForTime(double tickTime);
	public void setCentre(Vect v);

}
