package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadBoardListener implements ActionListener{
    private MainWindow mainWindow;

    public LoadBoardListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.showLoadDialog();
    }
}
