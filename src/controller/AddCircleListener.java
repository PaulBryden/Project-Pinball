package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.CUR_GIZMO.CIRCLE;

public class AddCircleListener implements ActionListener{
    private MainWindow mainWindow;

    public AddCircleListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().setSelectedGizmo(CIRCLE);
        mainWindow.setStatusLabel("Placing Circle. Please click a grid cell to place it.");
    }
}
