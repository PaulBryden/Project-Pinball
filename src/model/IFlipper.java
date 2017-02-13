package model;

public interface IFlipper extends IGizmo{
	public void setFlipperSpeed(double speed);
	public double getFlipperSpeed();
	public boolean isRight();
}
