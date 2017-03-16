package controller;

import view.MainWindow;
import view.RemoveConnectionToolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.STATE.RM_KEY_CONNECT;

public class RemoveKeyConnectionListener implements ActionListener{
    private MainWindow mainWindow;

    public RemoveKeyConnectionListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().setState(RM_KEY_CONNECT);
        mainWindow.setStatusLabel("Removing key connections. Click a gizmo to begin removing a connected key.");
    }
}
