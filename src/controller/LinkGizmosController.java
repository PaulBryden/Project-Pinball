package controller;

import view.MainWindow;

import static view.STATE.GIZMO_CONNECT;

public class LinkGizmosController {
	private MainWindow mainWindow;

	LinkGizmosController(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}

	public void start() {
		mainWindow.getBoard().setState(GIZMO_CONNECT);
		mainWindow.setStatusLabel("Connecting gizmo to gizmo. Please select a gizmo on the board.");
	}

}
