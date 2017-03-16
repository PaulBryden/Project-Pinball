package controller;

import view.MainWindow;
import view.RotateGizmoSidePanel;

import static view.STATE.ROTATE;

public class RotateGizmoController {
    private MainWindow mainWindow;

    public RotateGizmoController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start() {
        if(!(mainWindow.getSidePanel() instanceof RotateGizmoSidePanel)) {
            mainWindow.setSidePanel(new RotateGizmoSidePanel());
            mainWindow.getBoard().setState(ROTATE);
            mainWindow.setStatusLabel("Rotating Gizmo(s). Please click a gizmo on the board to rotate it.");
        }
    }
}
