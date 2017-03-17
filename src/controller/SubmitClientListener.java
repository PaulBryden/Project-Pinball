package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import network.Client;
import view.ClientDialog;
import view.MainWindow;

public class SubmitClientListener implements ActionListener {
	
    private MainWindow mainWindow;
    private ClientDialog clientDialog;

    public SubmitClientListener(MainWindow mainWindow, ClientDialog clientDialog){
        this.mainWindow = mainWindow;
        this.clientDialog = clientDialog;
    }
    
    @Override
	public void actionPerformed(ActionEvent e) {
        if(clientDialog.getTextFieldValidator().isValid() && !clientDialog.getPortText().equals("") && !clientDialog.getIpText().equals("")){
        	clientDialog.getDialog().dispose();
            Client client = new Client(mainWindow,mainWindow.getBoard().getModel(), clientDialog.getIpText(), Integer.parseInt(clientDialog.getPortText()));
            mainWindow.setStatusLabel("Connecting to Host...");
	        Thread newThread = new Thread(client);
	        newThread.start();

        } else {
            clientDialog.showWarningLabel("Please enter values");
        }
    }

}
