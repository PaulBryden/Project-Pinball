package controller;

import view.HostDialog;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ValidationListener implements DocumentListener{
    private HostDialog hostDialog;
    private boolean valid;

    public ValidationListener(HostDialog hostDialog){
        this.hostDialog = hostDialog;
        valid = false;
    }

    boolean isValidPort(){
        return (valid);
    }

    private void validate(){
        try {
            hostDialog.hideWarningLabel();
            valid = true;
        } catch (NumberFormatException e){
            hostDialog.showWarningLabel();
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
