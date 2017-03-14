package controller;

import network.Client;
import view.ClientDialog;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitClientListener implements ActionListener{
    private MainWindow mainWindow;
    private ClientDialog clientDialog;

    public SubmitClientListener(MainWindow mainWindow, ClientDialog clientDialog){
        this.mainWindow = mainWindow;
        this.clientDialog = clientDialog;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(clientDialog.getTextFieldValidator().isValidPort() && !clientDialog.getPortText().equals("") && !clientDialog.getIpText().equals("")){
            clientDialog.getDialog().dispose();
            Client client = new Client(mainWindow.getBoard().getModel(), clientDialog.getIpText(), Integer.parseInt(clientDialog.getPortText()));

            Thread newThread = new Thread(client);
            newThread.start();
            mainWindow.enableClientView();
        } else {
            clientDialog.showWarningLabel("Please enter values");
        }
    }
}
