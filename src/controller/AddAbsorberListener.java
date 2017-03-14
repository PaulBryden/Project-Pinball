package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.Board.CUR_GIZMO.ABSORBER;

public class AddAbsorberListener implements ActionListener{
    private MainWindow mainWindow;

    public AddAbsorberListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().setSelectedGizmo(ABSORBER);
        mainWindow.setStatusLabel("Placing Absorber. Please click a grid cell to place it.");
    }
}
