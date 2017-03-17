package controller;

import view.MainWindow;

import static view.STATE.REMOVE;

import view.SidePanel;

public class DeleteGizmoController {
    private MainWindow mainWindow;

    DeleteGizmoController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start() {
        mainWindow.setSidePanel(new SidePanel("Click on a gizmo to delete it."));
        mainWindow.getBoard().setState(REMOVE);
        mainWindow.setStatusLabel("Deleting Gizmo(s). Please click a gizmo on the board to delete it.");
    }
}
