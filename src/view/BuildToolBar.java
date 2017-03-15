package view;

import controller.*;

class BuildToolBar extends AbstractToolBar {
	
    BuildToolBar(MainWindow mainWindow){
        super("Build Mode");
    }

	protected void populateButtons() {
		makeButton("save", "Save board layout");
		makeButton("load", "Load board layout");
		addSeparator();
		makeButton("add", "Add gizmo");
		makeButton("delete", "Remove gizmo");
		makeButton("rotate", "Rotate gizmo");
		makeButton("move", "Move a gizmo");
		addSeparator();
		makeButton("connect", "Connect gizmos");
		makeButton("key", "Add key connection");
		addSeparator();
		makeButton("run_mode", "Enter run mode");
	}
}
