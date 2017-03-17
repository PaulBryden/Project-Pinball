package controller;

import view.MainWindow;
import view.RemoveConnectionToolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.STATE.BUILD;

public class RemoveConnectionController {
    private MainWindow mainWindow;

    public RemoveConnectionController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start(){
        if(!(mainWindow.getSidePanel() instanceof RemoveConnectionToolbar)){
            mainWindow.setSidePanel(new RemoveConnectionToolbar(mainWindow));
            mainWindow.getBoard().setState(BUILD);
            mainWindow.setStatusLabel("Removing Connection(s). Select which type of connection you would like to remove.");
        }
    }
}
