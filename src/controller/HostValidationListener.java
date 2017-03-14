package controller;

import view.HostDialog;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class HostValidationListener implements DocumentListener{
    private HostDialog hostDialog;
    private boolean valid;

    public HostValidationListener(HostDialog hostDialog){
        this.hostDialog = hostDialog;
        valid = false;
    }

    boolean isValidPort(){
        return (valid);
    }

    private void validate(){
        try {
            Integer.parseInt(hostDialog.getPortText());
            hostDialog.hideWarningLabel();
            valid = true;
        } catch (NumberFormatException e){
            hostDialog.showWarningLabel("That is not a number");
            valid = false;
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
