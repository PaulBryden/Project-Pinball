package controller;

import view.MainWindow;
import view.SidePanel;

import static view.STATE.GIZMO_CONNECT;

public class LinkGizmosController {
	private MainWindow mainWindow;

	public LinkGizmosController(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}

	public void start() {
		mainWindow.setSidePanel(new SidePanel());
		mainWindow.getBoard().setState(GIZMO_CONNECT);
		mainWindow.setStatusLabel("Connecting gizmo to gizmo. Please select a gizmo on the board.");
	}

}
