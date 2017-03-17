package view;

import javax.swing.Box;

import controller.PrimaryActionListener;

class BuildToolBar extends AbstractToolBar {
	
	private static final long serialVersionUID = -3061173893792427738L;

	BuildToolBar(MainWindow mainWindow, PrimaryActionListener listener){
        super("Build Mode", mainWindow, listener);
    }

	protected void populateButtons() {
		addButton("save", "Save board layout");
		addButton("load", "Load board layout");
		addSeparator();
		addButton("add", "Add gizmo");
		addButton("delete", "Remove gizmo");
		addButton("rotate", "Rotate gizmo");
		addButton("move", "Move gizmo");
		addSeparator();
		addButton("connect", "Connect gizmos");
		addButton("key", "Add key connection");
		addSeparator();
		addButton("settings", "Board settings");
		add(Box.createHorizontalGlue());
		addButton("run_mode", "Enter run mode");
	}
}
