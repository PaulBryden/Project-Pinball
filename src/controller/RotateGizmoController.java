package controller;

import view.MainWindow;
import view.SidePanel;

import static view.STATE.ROTATE;

public class RotateGizmoController {
    private MainWindow mainWindow;

    RotateGizmoController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start() {
        mainWindow.setSidePanel(new SidePanel());
        mainWindow.getBoard().setState(ROTATE);
        mainWindow.setStatusLabel("Rotating Gizmo(s). Please click a gizmo on the board to rotate it.");
    }
}
