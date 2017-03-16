package view;

import javax.swing.Box;
import javax.swing.JButton;

import controller.PrimaryActionListener;

import java.util.Map;

class RunToolBar extends AbstractToolBar {

	private static final long serialVersionUID = 9159488944045570471L;

	RunToolBar(MainWindow mainWindow, PrimaryActionListener listener) {
		super("Run Mode", mainWindow, listener);
	}
	
	protected void populateButtons() {
		makeButton("load", "Load a board layout");
		addSeparator();
		makeButton("run", "Run game");
		makeButton("pause", "Pause game");
		makeButton("tick", "Tick for one frame");
		add(Box.createHorizontalGlue());
		makeButton("build_mode", "Enter build mode");
		disableButton("pause");
	}
	
}
