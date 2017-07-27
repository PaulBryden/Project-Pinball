package controller;

import view.MainWindow;
import view.AddGizmoSidePanel;

import static view.STATE.ADD;

public class AddGizmoController {
    private MainWindow mainWindow;

    AddGizmoController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start() {
        if(!(mainWindow.getSidePanel() instanceof AddGizmoSidePanel)) {
            mainWindow.setSidePanel(new AddGizmoSidePanel(mainWindow));
            mainWindow.getBoard().setState(ADD);
            mainWindow.setStatusLabel("Placing Gizmo(s). Please select a gizmo from the left.");
        }
    }
}
