package controller;

import view.MainWindow;

import static view.STATE.RM_GIZMO_CONNECT;

public class RemoveGizmoTriggerController {
    private MainWindow mainWindow;

    public RemoveGizmoTriggerController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start() {
        mainWindow.getBoard().setState(RM_GIZMO_CONNECT);
        mainWindow.setStatusLabel("Removing gizmo connections. Click a gizmo to begin removing a connected gizmo");
    }
}
