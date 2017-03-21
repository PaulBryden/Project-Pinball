package controller;

import view.ConnectionSidePanel;
import view.MainWindow;
import view.STATE;

public class ConnectionPanelController {

	private MainWindow mainWindow;
	private PrimaryActionListener listener;

	ConnectionPanelController(MainWindow mainWindow, PrimaryActionListener listener){
		this.mainWindow = mainWindow;
		this.listener = listener;
	}

	public void start() {
		if(!(mainWindow.getSidePanel() instanceof ConnectionSidePanel)) {
			mainWindow.setSidePanel(new ConnectionSidePanel(listener, mainWindow));
			mainWindow.getBoard().setState(STATE.GIZMO_CONNECT_MENU);
		}
	}
}
