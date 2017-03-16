package controller;

import view.MainWindow;
import view.RotateGizmoToolBar;

import static view.STATE.ROTATE;

public class RotateGizmoController {
    private MainWindow mainWindow;

    public RotateGizmoController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start() {
        if(!(mainWindow.getSideToolBar() instanceof RotateGizmoToolBar)) {
            mainWindow.addSideToolBar(new RotateGizmoToolBar());
            mainWindow.getBoard().setState(ROTATE);
            mainWindow.setStatusLabel("Rotating Gizmo(s). Please click a gizmo on the board to rotate it.");
        }
    }
}
