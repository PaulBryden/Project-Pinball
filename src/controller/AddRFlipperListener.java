package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.Board.CUR_GIZMO.RFLIPPER;

public class AddRFlipperListener implements ActionListener {
    private MainWindow mainWindow;

    public AddRFlipperListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().setSelectedGizmo(RFLIPPER);
        mainWindow.setStatusLabel("Placing Right-Flipper. Please click a grid cell to place it.");
    }
}
