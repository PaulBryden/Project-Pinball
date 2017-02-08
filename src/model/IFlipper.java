package model;

public interface IFlipper extends IGizmo{
	public float[][] getCoords();
	public void setCoords(float[][] coords); //Remember to update the shape references.
	public void setFlipperSpeed(float x);
	public float getFlipperSpeed();
}
