package controller;

import view.MainWindow;
import view.STATE;
import view.SelectSidePanel;

public class SelectGizmoController {

    private MainWindow mainWindow;

    SelectGizmoController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start() {
        if(!(mainWindow.getSidePanel() instanceof SelectSidePanel)) {
            mainWindow.setSidePanel(new SelectSidePanel());
            mainWindow.getBoard().setState(STATE.SELECT);
        }
    }
}
