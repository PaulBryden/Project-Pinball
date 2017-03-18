package model;

import java.awt.Color;

/**
 * 
 * This class contains global constants for the model, such as default values
 * for physics constants and default colour schemes.
 * 
 * @author David
 *
 */
public final class Constants {

	// Margin to be used when comparing floats for equality:
	public static final double FLOAT_MARGIN = 0.0000001;

	public static final double TICK_TIME = 0.01; // in seconds
	public static final double DEFAULT_GRAVITY = 25.0; // L per sec ^ 2
	public static final double MIN_VELOCITY = DEFAULT_GRAVITY * TICK_TIME + 0.1;
	public static final double DEFAULT_MU = 0.025; // L per sec
	public static final double DEFAULT_MU2 = 0.025; // per L
	public static final double DEFAULT_COR = 1; // default coeff. of reflection

	public static final Color SQUARE_DEFAULT_COLOUR = Color.RED;
	public static final Color CIRCLE_DEFAULT_COLOUR = Color.GREEN;
	public static final Color TRIANGLE_DEFAULT_COLOUR = Color.CYAN;
	public static final Color ABSORBER_DEFAULT_COLOUR = Color.MAGENTA;
	public static final Color COUNTER_GIZMO_DEFAULT_COLOUR = Color.WHITE;
	public static final Color FLIPPER_DEFAULT_COLOUR = Color.YELLOW;
	public static final Color BALL_DEFAULT_COLOUR = Color.BLUE;
	public static final Color BACKGROUND_DEFAULT_COLOUR = Color.BLACK;
	public static final Color FOREGROUND_DEFAULT_COLOUR = Color.RED;

}
