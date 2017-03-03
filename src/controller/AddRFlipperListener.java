package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.CUR_GIZMO.RFLIPPER;

public class AddRFlipperListener implements ActionListener {
    private MainWindow mainWindow;

    public AddRFlipperListener(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().getMouseListener().setGizmo(RFLIPPER);
        mainWindow.setStatusLabel("Placing Right-Flipper");
    }
}
