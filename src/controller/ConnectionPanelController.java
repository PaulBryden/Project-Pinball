package controller;

import view.ConnectionSidePanel;
import view.MainWindow;

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
		}
	}

}
