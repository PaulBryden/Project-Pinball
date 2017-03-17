package view;

import javax.swing.Box;

import controller.PrimaryActionListener;

class RunToolBar extends AbstractToolBar {

	private static final long serialVersionUID = 9159488944045570471L;

	RunToolBar(PrimaryActionListener listener) {
		super("Run Mode", listener);
	}
	
	@Override
	protected void populateButtons() {
		addButton("load", "Load a board layout");
		addSeparator();
		addButton("run", "Run game");
		addButton("pause", "Pause game");
		addButton("tick", "Tick for one frame");
		add(Box.createHorizontalGlue());
		addButton("build_mode", "Enter build mode");
		disableButton("pause");
	}
	
}
