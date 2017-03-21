package controller;

import view.MainWindow;
import view.SidePanel;

import static view.STATE.MOVE;

import view.Board;

public class MoveGizmoController {
	private MainWindow mainWindow;

	MoveGizmoController(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public void start() {
		Board board = mainWindow.getBoard();
			mainWindow.setSidePanel(new SidePanel(
					"Click on a gizmo in order to move it, then click again on the grid square you want to move it to."));
			board.setState(MOVE);
			mainWindow.setStatusLabel("Moving Gizmo(s). Please click a gizmo on the board");
	}
}
