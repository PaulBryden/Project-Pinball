package controller;

import view.Board;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.CUR_GIZMO.CIRCLE;

public class AddCircleListener implements ActionListener{
    private MainWindow mainWindow;

    public AddCircleListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().getMouseListener().setGizmo(CIRCLE);
        mainWindow.setStatusLabel("Placing Circle. Please click a grid cell to place it.");
    }
}
