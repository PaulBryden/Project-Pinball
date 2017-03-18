package controller;

import static view.CUR_GIZMO.COUNTER;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;

public class AddCounterListener implements ActionListener{
	
    private MainWindow mainWindow;

    public AddCounterListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.getBoard().setSelectedGizmo(COUNTER);
        mainWindow.setStatusLabel("Placing Counter gizmo. Please click a grid cell to place it.");
    }
    
}
