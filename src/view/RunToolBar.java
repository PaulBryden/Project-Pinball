package view;

import model.IModel;

import javax.swing.JButton;
import java.util.Map;

class RunToolBar extends AbstractToolBar {

	private static final long serialVersionUID = 9159488944045570471L;

	private Map<String, JButton> buttons;

	RunToolBar(MainWindow mainWindow, IModel model) {
		super("Run Mode");
	}
	
	protected void populateButtons() {
		makeButton("load", "Load a board layout");
		addSeparator();
		makeButton("run", "Run game");
		makeButton("pause", "Pause game");
		makeButton("tick", "Tick for one frame");
		addSeparator();
		makeButton("build_mode", "Enter build mode");
	}
	
	void stop() {
		buttons.get("pause").doClick();
	}
}
