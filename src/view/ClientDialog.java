package view;

import controller.ClientValidationListener;
import controller.SubmitClientListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import static java.awt.Color.RED;
import static java.awt.GridBagConstraints.HORIZONTAL;

public class ClientDialog {
    private MainWindow mainWindow;
    private JDialog dialog;
    private JTextField portTextField;
    private JTextField ipTextField;
    private JButton button;
    private ClientValidationListener validator;
    private JLabel warning;
    private JLabel portLabel;
    private JLabel ipLabel;

    ClientDialog(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        dialog = new JDialog();
        portTextField = new JTextField();
        ipTextField = new JTextField();
        button = new JButton("Continue");
        validator = new ClientValidationListener(this);
        warning = new JLabel("");
        portLabel = new JLabel("Please enter port for host connection");
        ipLabel = new JLabel("Please enter ip for host connection");
        warning.setVisible(false);
    }

    void build(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = HORIZONTAL;

        dialog.setTitle("Client");
        dialog.setLayout(new GridBagLayout());
        dialog.setResizable(true);
        dialog.setModal(true);

        warning.setForeground(RED);
        button.addActionListener(new SubmitClientListener(mainWindow, this));
        portTextField.setColumns(4);
        ipTextField.setColumns(4);
        portTextField.getDocument().addDocumentListener(validator);
        ipTextField.getDocument().addDocumentListener(validator);

        dialog.add(portLabel, constraints);

        constraints.gridx = 1;
        dialog.add(portTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        dialog.add(ipLabel, constraints);

        constraints.gridx = 1;
        dialog.add(ipTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        dialog.add(button, constraints);

        constraints.gridy = 3;
        dialog.add(warning, constraints);

        dialog.setPreferredSize(new Dimension(350, 150));
        dialog.pack();
        dialog.setVisible(true);
    }

    public String getPortText(){
        return (portTextField.getText().trim());
    }

    public String getIpText(){
        return (ipTextField.getText().trim());
    }

    public ClientValidationListener getTextFieldValidator(){
        return (validator);
    }

    public JDialog getDialog(){
        return (dialog);
    }

    public void showWarningLabel(String message){
        warning.setText(message);
        warning.setVisible(true);
        dialog.revalidate();
        dialog.repaint();
    }

    public void hideWarningLabel() {
        warning.setVisible(false);
        dialog.revalidate();
        dialog.repaint();
    }
}
