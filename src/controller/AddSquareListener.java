package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.Board.CUR_GIZMO.SQUARE;

public class AddSquareListener implements ActionListener{
    private MainWindow mainWindow;

    public AddSquareListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().setSelectedGizmo(SQUARE);
        mainWindow.setStatusLabel("Placing Square. Please click a grid cell to place it.");
    }
}
