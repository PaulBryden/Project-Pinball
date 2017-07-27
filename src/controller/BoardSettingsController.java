
package controller;

import view.BoardSettingsSidePanel;
import view.MainWindow;

import static view.STATE.*;

public class BoardSettingsController {
	private MainWindow mainWindow;

	BoardSettingsController(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}

	public void start() {
		mainWindow.setSidePanel(new BoardSettingsSidePanel(mainWindow));
		mainWindow.getBoard().setState(BUILD);
		mainWindow.setStatusLabel("Adjust Sliders to change gravity and friction.");
	}

}
