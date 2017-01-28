package model;

public interface ICircle extends IGizmo{
	public float getRadius();
	public void setRadius(float radius);
	public void setCentre(float x, float y);
	public float[] getCentre(float x, float y);
	public boolean isBall();
}
