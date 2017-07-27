package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.CUR_GIZMO.TRIANGLE;

public class AddTriangleListener implements ActionListener{
    private MainWindow mainWindow;

    public AddTriangleListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().setSelectedGizmo(TRIANGLE);
        mainWindow.setStatusLabel("Placing Triangle. Please click a grid cell to place it.");
    }
}