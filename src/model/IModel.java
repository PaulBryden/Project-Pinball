package model;

import java.awt.Color;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Observer;

import network.Host;
import physics.Vect;

/** 
 * 
 * @author David, Matt, Paul
 *
 */
public interface IModel {

	/**
	 * Execute the main physics loop once. This method will evaluate collisions,
	 * move all items in the model for the the tick time or the time until the
	 * next collision (whichever is shorter), update the velocities of all balls
	 * based on physical parameters and the evaluated collision, and finally
	 * update the view.
	 */
	void tick();

	List<IGizmo> getGizmos();
	
	/**
	 * 
	 * @return A list containing all of the absorbers in the model.
	 */
	List<IAbsorber> getAbsorbers();

	/**
	 * 
	 * @return A list containing all of the flippers in the model.
	 */
	List<IFlipper> getFlippers();

	List<IBall> getBalls();

	void addGizmo(IGizmo gizmo);

	void addBall(IBall ball);

	void removeBall(IBall ball);

	void removeGizmo(IGizmo gizmo);

	/**
	 * Get the Gizmo at the given grid coordinates.
	 * 
	 * @param coords
	 *            A vector representing the grid coordinates.
	 * @return The gizmo at those coordinates, or null if there is none.
	 */
	IGizmo getGizmo(Vect coords);

	/**
	 * Get the ball at the given grid coordinates.
	 * 
	 * @param coords
	 *            A vector representing the grid coordinates.
	 * @return The ball at those coordinates, or null if there is none.
	 */
	IBall getBall(Vect coords);

	/**
	 * 
	 * @param coords
	 *            A vector representing the grid coordinates.
	 * @return True iff there is no ball or gizmo within the given coordinates.
	 */
	boolean isCellEmpty(Vect coords);

	void addObserver(Observer o);

	void processKeyPressedTrigger(int keyChar);

	void processKeyReleasedTrigger(int keyChar);

	void addKeyPressedTrigger(int keyCode, IGizmo gizmo);

	void addKeyReleasedTrigger(int keyCode, IGizmo gizmo);

	boolean isClient();

	void addKeyToSend(String string);

	void reset();

	Color getBackgroundColour();

	void setBackgroundColour(Color colour);

	Map<Integer, KeyTrigger> getKeyPressedTriggers();

	Map<Integer, KeyTrigger> getKeyReleasedTriggers();

	void setBalls(List<IBall> balls);

	void setGizmos(List<IGizmo> gizmos);

	double getGravity();

	double getFrictionMu();

	double getFrictionMu2();

	void setGravity(double gravity);

	void setFrictionMu(double mu);

	void setFrictionMu2(double mu2);

	void setDefaultPhysics();

	void update();

	Deque<String> getKeysToSend();

	void setClient();


	void setHost(Host host);

}
