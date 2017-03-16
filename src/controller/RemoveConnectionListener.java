package controller;

import view.MainWindow;
import view.RemoveConnectionToolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.STATE.BUILD;

public class RemoveConnectionListener implements ActionListener{
    private MainWindow mainWindow;

    public RemoveConnectionListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(mainWindow.getSideToolBar() instanceof RemoveConnectionToolbar)){
            mainWindow.addSideToolBar(new RemoveConnectionToolbar(mainWindow));
            mainWindow.getBoard().setState(BUILD);
            mainWindow.setStatusLabel("Removing Connection(s). Select which type of connection you would like to remove.");
        }
    }
}
