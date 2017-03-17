package controller;

import view.MainWindow;
import view.SidePanel;

import static view.STATE.REMOVE;

public class DeleteGizmoController {
    private MainWindow mainWindow;

    DeleteGizmoController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start() {
        mainWindow.setSidePanel(new SidePanel());
        mainWindow.getBoard().setState(REMOVE);
        mainWindow.setStatusLabel("Deleting Gizmo(s). Please click a gizmo on the board to delete it.");
    }
}
