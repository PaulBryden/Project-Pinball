package controller;

import view.MainWindow;

import javax.swing.JToolBar;

import static view.STATE.KEY_CONNECT;

public class AddKeyTriggerController {
	private MainWindow mainWindow;

	public AddKeyTriggerController(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}
	
	public void start() {
		mainWindow.addSideToolBar(new JToolBar());
		mainWindow.getBoard().setState(KEY_CONNECT);
		mainWindow.setStatusLabel("Connecting gizmo to key. Please select a gizmo on the board.");
	}

}
