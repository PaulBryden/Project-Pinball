package controller;

import view.MainWindow;

import javax.swing.JToolBar;

import static view.STATE.GIZMO_CONNECT;

public class LinkGizmosController {
	private MainWindow mainWindow;

	public LinkGizmosController(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}

	public void start() {
		mainWindow.addSideToolBar(new JToolBar());
		mainWindow.getBoard().setState(GIZMO_CONNECT);
		mainWindow.setStatusLabel("Connecting gizmo to gizmo. Please select a gizmo on the board.");
	}

}
