package controller;

import view.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.STATE.REMOVE_CONNECT;

public class RemoveConnectionListener implements ActionListener{
    private MainWindow mainWindow;

    public RemoveConnectionListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().setState(REMOVE_CONNECT);
        mainWindow.addSideToolBar(new JToolBar());
        mainWindow.setStatusLabel("Removing connections. Click a gizmo to begin removing a connected gizmo");
    }
}
