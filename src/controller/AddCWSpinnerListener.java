package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.CUR_GIZMO.SPINNER;

public class AddCWSpinnerListener implements ActionListener {
    private MainWindow mainWindow;

    public AddCWSpinnerListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().setSelectedGizmo(SPINNER);
        mainWindow.setStatusLabel("Placing Spinner. Please click a grid cell to place it.");
    }
}
