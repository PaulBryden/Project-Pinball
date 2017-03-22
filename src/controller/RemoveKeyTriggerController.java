package controller;

import view.MainWindow;

import static view.STATE.RM_KEY_CONNECT;

public class RemoveKeyTriggerController {
    private MainWindow mainWindow;

    RemoveKeyTriggerController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start() {
        mainWindow.getBoard().setState(RM_KEY_CONNECT);
        mainWindow.setStatusLabel("Removing key connections. Click a gizmo to begin.");
	}
    
}
