package model;

import physics.Vect;

/**
 * 
 * Factory class for creating gizmos. Gizmos can be created using getGizmo
 * together with a TYPE enum. Absorbers and balls have their own factory
 * methods, as they require different parameters. All gizmos can be given a
 * predefined ID. Otherwise the factory will generate a new ID based on the
 * coordinates of the gizmo.
 * 
 * @author David
 *
 */
public class GizmoFactory {

	public enum TYPE {
		Square, Circle, Triangle, LeftFlipper, RightFlipper, Absorber, Ball, Spinner, Counter

	}

	private IModel model;

	/**
	 * 
	 * @param model
	 *            The model is required in order to create functioning
	 *            absorbers, as well as to generate correct IDs for balls.
	 */
	public GizmoFactory(IModel model) {
		this.model = model;
	}

	/**
	 * 
	 * @param type
	 *            The type of gizmo requested
	 * @param v
	 *            The coordinates
	 * @return The gizmo, or null if none could be constructed.
	 */
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
		case Spinner:
			return new Spinner("SP" + generateCoordString(v), v);
		default:
			return null;
		}
	}

	/**
	 * 
	 * @param type
	 *            The type of gizmo requested
	 * @param id
	 *            A unique ID
	 * @param v
	 *            The coordinates
	 * @return The gizmo, or null if none could be constructed.
	 */
	public IGizmo getGizmo(TYPE type, String id, Vect v) {
		switch (type) {
		case Square:
			return new SquareGizmo(id, v);
		case Circle:
			return new CircleGizmo(id, v);
		case Triangle:
			return new TriangleGizmo(id, v);
		case LeftFlipper:
			return new LeftFlipper(id, v);
		case RightFlipper:
			return new RightFlipper(id, v);
		case Spinner:
			return new Spinner(id, v);
		default:
			return null;
		}
	}

	/**
	 * Factory method for creating rectangularg gizmos such as absorbers and
	 * counters.
	 * 
	 * @param v1
	 *            Top left corner
	 * @param v2
	 *            Bottom right corner
	 * @return The absorber
	 */
	public IGizmo getRectangularGizmo(TYPE type, Vect v1, Vect v2) {
		switch (type) {
		case Absorber:
			return new Absorber(model, "A" + generateCoordString(v1), v1, v2);
		case Counter:
			return new CounterGizmo("N" + generateCoordString(v1), v1, v2);
		default:
			return null;
		}
	}

	/**
	 * Factory method for creating rectangularg gizmos such as absorbers and
	 * counters.
	 * 
	 * @param id
	 *            A unique ID
	 * @param v1
	 *            Top left corner
	 * @param v2
	 *            Bottom right corner
	 * @return The gizmo
	 */
	public IGizmo getRectangularGizmo(TYPE type, String id, Vect v1, Vect v2) {
		switch (type) {
		case Absorber:
			return new Absorber(model, id, v1, v2);
		case Counter:
			return new CounterGizmo(id, v1, v2);
		default:
			return null;
		}
	}

	/**
	 * 
	 * @param pos
	 *            Centre of the ball
	 * @param velo
	 *            Velocity of the ball
	 * @return The ball
	 */
	public IBall getBall(Vect pos, Vect velo) {
		return getBall("B" + zeroLeftPad(model.getBalls().size()), pos, velo);
	}

	/**
	 * 
	 * @param id
	 *            A unique ID
	 * @param pos
	 *            Centre of the ball
	 * @param velo
	 *            Velocity of the ball
	 * @return The ball
	 */
	public IBall getBall(String id, Vect pos, Vect velo) {
		return new BallGizmo(id, pos, velo);
	}

	/**
	 * 
	 * @param v
	 *            A set of coordinates
	 * @return A unique string representing those coordinates
	 */
	private static String generateCoordString(Vect v) {
		int x = (int) Math.round(v.x());
		int y = (int) Math.round(v.y());
		return zeroLeftPad(x) + zeroLeftPad(y);
	}

	/**
	 * Returns a string, left-padded with a zero if i has one decimal digit.
	 * 
	 * @param i
	 *            An integer to pad - should be one or two digits (i.e. 0 <= i <
	 *            100)
	 * @return A 2-character string
	 */
	private static String zeroLeftPad(int i) {
		String s = Integer.toString(i);
		if (s.length() < 2)
			s = "0" + s;
		return s;
	}

}
