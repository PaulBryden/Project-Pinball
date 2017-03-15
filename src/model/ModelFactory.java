package model;

/**
 * 
 * @author David
 *
 */
public final class ModelFactory {
	
	public static IModel getModel() {
		return new GameModel();
	}

}
