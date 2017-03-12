package model;

import java.awt.Color;

public final class Constants {
	
	public static final double FLOAT_MARGIN = 0.0000001;

	public static final double TICK_TIME = 0.01; // in seconds
	public static final double DEFAULT_GRAVITY = 25.0;
	public static final double MIN_VELOCITY = DEFAULT_GRAVITY * TICK_TIME + 0.1;
	public static final double DEFAULT_MU = 0.025; // Lpersecond
	public static final double DEFAULT_MU2 = 0.025; // per L.
	public static final double DEFAULT_COR = 1; // default coefficient of reflection
	
	public static final Color SQUARE_DEFAULT_COLOUR = Color.RED;
	public static final Color CIRCLE_DEFAULT_COLOUR = Color.GREEN;
	public static final Color TRIANGLE_DEFAULT_COLOUR = Color.CYAN;
	public static final Color ABSORBER_DEFAULT_COLOUR = Color.MAGENTA;
	public static final Color FLIPPER_DEFAULT_COLOUR = Color.YELLOW;
	public static final Color BALL_DEFAULT_COLOUR = Color.BLUE;
	public static final Color BACKGROUND_DEFAULT_COLOUR = Color.BLACK;

}
