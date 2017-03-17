package controller;

import view.MainWindow;
import view.MoveGizmoSidePanel;

import static view.STATE.MOVE;

public class MoveGizmoController {
	private MainWindow mainWindow;

	public MoveGizmoController(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}

	public void start() {
		if(!(mainWindow.getSidePanel() instanceof MoveGizmoSidePanel)) {
			mainWindow.setSidePanel(new MoveGizmoSidePanel());
			mainWindow.getBoard().setState(MOVE);
			mainWindow.setStatusLabel("Moving Gizmo(s). Please click a gizmo on the board");
		}
	}
}
