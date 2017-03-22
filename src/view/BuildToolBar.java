package view;

import javax.swing.Box;

import controller.PrimaryActionListener;

class BuildToolBar extends AbstractToolBar {
	
	private static final long serialVersionUID = -3061173893792427738L;

	BuildToolBar(PrimaryActionListener listener){
        super("Build Mode", listener);
    }

	protected void populateButtons() {
		addButton("save", "Save board layout");
		addButton("load", "Load board layout");
		addSeparator();
		addToggleButton("add", "Add gizmo");
		addToggleButton("delete", "Remove gizmo");
		addToggleButton("rotate", "Rotate gizmo");
		addToggleButton("move", "Move gizmo");
		addToggleButton("connect", "Connect gizmos");
		addToggleButton("select", "Select a gizmo");
		addSeparator();
		addToggleButton("settings", "Board settings");
		add(Box.createHorizontalGlue());
		addButton("run_mode", "Enter run mode");
		connectToggleButtons();
	}
}
