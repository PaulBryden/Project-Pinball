package controller;

import view.MainWindow;

import static view.STATE.REMOVE_CONNECT;

public class RemoveConnectionController {
	
    private MainWindow mainWindow;

    public RemoveConnectionController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start() {
        mainWindow.getBoard().setState(REMOVE_CONNECT);
        mainWindow.setStatusLabel("Removing connections. Click a gizmo to begin removing a connected gizmo");
    }
}
