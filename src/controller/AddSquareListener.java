package controller;

import view.Board;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.CUR_GIZMO.SQUARE;

public class AddSquareListener implements ActionListener{
    private MainWindow mainWindow;

    public AddSquareListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().getMouseListener().setGizmo(SQUARE);
        mainWindow.setStatusLabel("Placing Square. Please click a grid cell to place it.");
    }
}
