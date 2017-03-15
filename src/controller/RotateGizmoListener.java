package controller;

import view.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.STATE.ROTATE;

public class RotateGizmoListener implements ActionListener{
    private MainWindow mainWindow;

    public RotateGizmoListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.addSideToolBar(new JToolBar());
        mainWindow.getBoard().setState(ROTATE);
        mainWindow.setStatusLabel("Rotating Gizmo(s). Please click a gizmo on the board to rotate it.");
    }
}
