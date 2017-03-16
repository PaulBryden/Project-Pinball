
package view;

import javax.swing.Box;

import controller.PrimaryActionListener;

class BuildToolBar extends AbstractToolBar {
	
	private static final long serialVersionUID = -3061173893792427738L;

	BuildToolBar(MainWindow mainWindow, PrimaryActionListener listener){
        super("Build Mode", mainWindow, listener);
    }

	protected void populateButtons() {
		makeButton("save", "Save board layout");
		makeButton("load", "Load board layout");
		addSeparator();
		makeButton("add", "Add gizmo");
		makeButton("delete", "Remove gizmo");
		makeButton("rotate", "Rotate gizmo");
		makeButton("move", "Move gizmo");
		addSeparator();
		makeButton("connect", "Connect gizmos");
		makeButton("key", "Add key connection");
		addSeparator();
		makeButton("settings", "Board settings");
		add(Box.createHorizontalGlue());
		makeButton("run_mode", "Enter run mode");
	}
}
