package controller;

import view.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.STATE.REMOVE;

public class DeleteGizmoListener implements ActionListener{
    private MainWindow mainWindow;

    public DeleteGizmoListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.addSideToolBar(new JToolBar());
        mainWindow.getBoard().setState(REMOVE);
        mainWindow.setStatusLabel("Deleting Gizmo(s). Please click a gizmo on the board to delete it.");
    }
}
