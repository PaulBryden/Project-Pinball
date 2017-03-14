package controller;

import com.sun.jmx.snmp.InetAddressAcl;
import view.ClientDialog;
import view.HostDialog;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class ClientValidationListener implements DocumentListener{
    private ClientDialog clientDialog;
    private boolean valid;

    public ClientValidationListener(ClientDialog clientDialog){
        this.clientDialog = clientDialog;
        valid = false;
    }

    boolean isValidPort(){
        return (valid);
    }

    private void validate(){
        try {
            Integer.parseInt(clientDialog.getPortText());
            clientDialog.hideWarningLabel();
            valid = true;
        } catch (NumberFormatException e){
            clientDialog.showWarningLabel("That is not a number");
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
