package controller;

import view.MainWindow;
import view.SidePanel;

import static view.STATE.KEY_CONNECT;

public class AddKeyTriggerController {
	private MainWindow mainWindow;

	AddKeyTriggerController(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}
	
	public void start() {
		mainWindow.setSidePanel(new SidePanel());
		mainWindow.getBoard().setState(KEY_CONNECT);
		mainWindow.setStatusLabel("Connecting gizmo to key. Please select a gizmo on the board.");
	}

}
