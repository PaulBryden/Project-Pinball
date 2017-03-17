package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;

public class HostDisconnectListener implements ActionListener {
    private MainWindow window;

    public HostDisconnectListener(MainWindow window){
        this.window=window;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        window.getBoard().getModel().getHost().disconnect();
    }

}