package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;

public class ClientDisconnectListener implements ActionListener {
    private MainWindow window;

    public ClientDisconnectListener(MainWindow window){
        this.window=window;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        window.getBoard().getModel().getClient().stopClient();
        window.build();
    }

}