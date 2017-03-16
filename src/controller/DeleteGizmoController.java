package controller;

import view.DeleteGizmoToolBar;
import view.MainWindow;

import static view.STATE.REMOVE;

public class DeleteGizmoController {
    private MainWindow mainWindow;

    public DeleteGizmoController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start() {
        if(!(mainWindow.getSideToolBar() instanceof DeleteGizmoToolBar)) {
            mainWindow.addSideToolBar(new DeleteGizmoToolBar());
            mainWindow.getBoard().setState(REMOVE);
            mainWindow.setStatusLabel("Deleting Gizmo(s). Please click a gizmo on the board to delete it.");
        }
    }
}
