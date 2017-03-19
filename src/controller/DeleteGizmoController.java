package controller;

import view.MainWindow;

import static view.STATE.REMOVE;

import view.DeleteGizmoSidePanel;

public class DeleteGizmoController {
	
    private MainWindow mainWindow;
    private PrimaryActionListener listener;
    
    DeleteGizmoController(MainWindow mainWindow, PrimaryActionListener listener){
        this.mainWindow = mainWindow;
        this.listener = listener;
    }

    public void start() {
        mainWindow.setSidePanel(new DeleteGizmoSidePanel(listener));
        mainWindow.getBoard().setState(REMOVE);
        mainWindow.setStatusLabel("Deleting Gizmo(s). Please click a gizmo on the board to delete it.");
    }
}
