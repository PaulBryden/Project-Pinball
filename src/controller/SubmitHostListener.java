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
        if(hostDialog.getTextFieldValidator().isValidPort()){
            hostDialog.getDialog().dispose();
            mainWindow.getBoard().getModel().startHosting(Integer.parseInt(hostDialog.getPortText()));
        }
    }
}
