package controller;

import view.MainWindow;

import static view.STATE.ROTATE;

import view.SidePanel;

public class RotateGizmoController {
    private MainWindow mainWindow;

    RotateGizmoController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start() {
        mainWindow.setSidePanel(new SidePanel("Click on a gizmo to rotate it."));
        mainWindow.getBoard().setState(ROTATE);
        mainWindow.setStatusLabel("Rotating Gizmo(s). Please click a gizmo on the board to rotate it.");
    }
}
