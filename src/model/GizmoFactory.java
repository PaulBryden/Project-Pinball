package model;

import physics.Vect;

public class GizmoFactory {
	
	public enum TYPE {Square, Circle, Triangle, LeftFlipper, RightFlipper, Absorber}
	
	private IModel model;
	
	public GizmoFactory(IModel model) {
		this.model = model;
	}
	
	public IGizmo getGizmo(TYPE type, Vect v) {
		switch (type) {
		case Square:
			return new SquareGizmo("S" + generateCoordString(v), v);
		case Circle:
			return new CircleGizmo("C" + generateCoordString(v), v);
		case Triangle:
			return new TriangleGizmo("T" + generateCoordString(v), v);
		case LeftFlipper:
			return new LeftFlipper("LF" + generateCoordString(v), v);
		case RightFlipper:
			return new RightFlipper("RF" + generateCoordString(v), v);
		default:
			return null;
		}
	}
	
	public IAbsorber getAbsorber(Vect v1, Vect v2) {
		return new Absorber(model, "A" + generateCoordString(v1), v1, v2);
	}
	
	public IBall getBall(Vect pos, Vect velo) {
		return new BallGizmo("B" + zeroLeftPad(model.getBalls().size()), pos, velo);
	}
	
	private static String generateCoordString(Vect v) {
		int x = (int) Math.round(v.x());
		int y = (int) Math.round(v.y());
		return zeroLeftPad(x) + zeroLeftPad(y);
	}
	
	private static String zeroLeftPad(int i) {
		String s = Integer.toString(i);
		if (s.length() < 2) 
			s = "0" + s;
		return s;
	}

}
