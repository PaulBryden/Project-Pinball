package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.HostDialog;
import view.MainWindow;

import model.BoardFileHandler;
import network.Host;

public class SubmitHostListener implements ActionListener{
	
    private MainWindow mainWindow;
    private HostDialog hostDialog;

    public SubmitHostListener(MainWindow mainWindow, HostDialog hostDialog){
        this.mainWindow = mainWindow;
        this.hostDialog = hostDialog;
    }
    
    @Override
	public void actionPerformed(ActionEvent e) {
        if(hostDialog.getTextFieldValidator().isValid() && !hostDialog.getPortText().equals("")){
            mainWindow.setStatusLabel("Host: Awaiting client connection");
            hostDialog.getDialog().dispose();
            Host host = new Host(mainWindow,new BoardFileHandler(mainWindow.getBoard().getModel()),
                    mainWindow.getBoard().getModel(),Integer.parseInt(hostDialog.getPortText()));
            Thread newThread = new Thread(host);
            newThread.start();
        } else {
            hostDialog.showWarningLabel("Please enter a Port");
        }
    }
}
