package mitview;

import javax.swing.UIManager;

import model.BoardFileHandler;
import model.GameModel;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Main {

	public static void main(String[] args) {
		try {
			// Use the platform look and feel
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("Look and Feel error in Main");
		}

		GameModel model = new GameModel();

		RunGui gui = new RunGui(model);
		gui.createAndShowGUI();
	}
}
