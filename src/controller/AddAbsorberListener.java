package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.CUR_GIZMO.ABSORBER;

public class AddAbsorberListener implements ActionListener{
    private MainWindow mainWindow;

    public AddAbsorberListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().getMouseListener().setGizmo(ABSORBER);
        mainWindow.setStatusLabel("Placing Absorber");
    }
}
