package controller;

import view.ClientDialog;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ClientValidationListener implements DocumentListener{
    private ClientDialog clientDialog;
    private boolean valid;

    public ClientValidationListener(ClientDialog clientDialog){
        this.clientDialog = clientDialog;
        valid = false;
    }

    boolean isValid(){
        return (valid);
    }

    private void validate(){
        boolean validPort = true;
        boolean validIP = true;

        try {
            Integer.parseInt(clientDialog.getPortText());
            clientDialog.hideWarningLabel();
        } catch (NumberFormatException e){
            validPort = false;
        }

        String ipText = clientDialog.getIpText();

        if(!ipText.equals("localhost")){
            String[] parts = clientDialog.getIpText().split("\\.");

            if (parts.length == 4 && ipText.charAt(ipText.length() - 1) != '.') {
                for (String part : parts) {
                    int partlength = part.trim().length();
                    if (partlength <= 0 || partlength > 3) {
                        validIP = false;
                        break;
                    }
                }
            } else {
                validIP = false;
            }
        }

        if(validPort && validIP){
            valid = true;
        } else {
            valid = false;
            if(!validPort && !validIP){
                clientDialog.showWarningLabel("Port is not a number and IP are invalid");
            } else if(!validPort){
                clientDialog.showWarningLabel("Port is not a number");
            } else {
                clientDialog.showWarningLabel("IP is invalid");
            }
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        validate();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        validate();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        validate();
    }
}
