package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.STATE.RM_GIZMO_CONNECT;

public class RemoveGizmoConnectionListener implements ActionListener{
    private MainWindow mainWindow;

    public RemoveGizmoConnectionListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().setState(RM_GIZMO_CONNECT);
        mainWindow.setStatusLabel("Removing gizmo connections. Click a gizmo to begin removing a connected gizmo");
    }
}
