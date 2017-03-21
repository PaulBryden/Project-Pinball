package model;

import physics.Vect;

/**
 * 
 * @author Paul, David
 *
 */
public interface IBall extends IGizmo {
	
	/**
	 * @returns the radius of the ball in the model
	 */
	Vect getVelo();
	
	/**
	 * @param v 
	 * 			the current velocity of the ball as a Vect
	 */
	void setVelo(Vect v);

	/**
	 * @param tickTime
	 * 				the time the ball is to move for 
	 * adjusts the ball position by adding velocity*time to the 
	 */
	void moveForTime(double tickTime);

	/**
	 * 
	 * @param v
	 * 			moves the centre of the ball to position vector v
	 */
	void setCentre(Vect v);

	/**
	 * @returns the current position of the centre of the ball as a vect
	 */
	Vect getCentre();

	/**
	 * @returns the radius of the ball in the model
	 */
	double getRadius();
	
	/**
	 * @param radius 
	 * 				the desired radius of the ball in the model
	 */
	void setRadius(double radius);

}
