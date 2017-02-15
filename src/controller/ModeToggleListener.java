package controller;

import model.IModel;
import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModeToggleListener implements ActionListener {
    private MainWindow mainWindow;

    public ModeToggleListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.toggleView();
    }
}
