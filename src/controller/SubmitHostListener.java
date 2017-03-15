package controller;

import view.HostDialog;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            hostDialog.getDialog().dispose();
            mainWindow.setStatusLabel("Host: Awaiting client connection");
            mainWindow.getBoard().getModel().startHosting(Integer.parseInt(hostDialog.getPortText()));
            mainWindow.setStatusLabel("Host: Connected to client");
        } else {
            hostDialog.showWarningLabel("Please enter a Port");
        }
    }
}
