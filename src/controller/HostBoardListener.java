package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HostBoardListener implements ActionListener{
    private MainWindow mainWindow;

    public HostBoardListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	mainWindow.getBoard().getModel().startHosting(1003);
    	
    }
}
