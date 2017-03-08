package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientBoardListener implements ActionListener{
    private MainWindow mainWindow;

    public ClientBoardListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	Thread newThread = new Thread(
    	    	(Runnable) mainWindow.getBoard().getModel());
    	newThread.start();
    	
    }
}
