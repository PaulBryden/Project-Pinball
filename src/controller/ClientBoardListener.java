package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import network.Client;

public class ClientBoardListener implements ActionListener{
    private MainWindow mainWindow;

    public ClientBoardListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	Client client = new Client(mainWindow.getBoard().getModel(),"localhost",1003);
 
    	Thread newThread = new Thread(client);
    	newThread.start();
    	mainWindow.enableClientView();
    }
}
