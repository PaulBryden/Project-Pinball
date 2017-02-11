package model;

public interface IFlipper extends IGizmo{
	public void setCoords(float[][] coords); //Remember to update the shape references.
	public void setFlipperSpeed(float x);
	public float getFlipperSpeed();
	public void moveForTime(double tickTime);
}
