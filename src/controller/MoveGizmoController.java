package controller;

import view.MainWindow;
import view.MoveGizmoToolBar;

import static view.STATE.MOVE;

public class MoveGizmoController {
	private MainWindow mainWindow;

	public MoveGizmoController(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}

	public void start() {
		if(!(mainWindow.getSideToolBar() instanceof MoveGizmoToolBar)) {
			mainWindow.addSideToolBar(new MoveGizmoToolBar());
			mainWindow.getBoard().setState(MOVE);
			mainWindow.setStatusLabel("Moving Gizmo(s). Please click a gizmo on the board");
		}
	}
}
