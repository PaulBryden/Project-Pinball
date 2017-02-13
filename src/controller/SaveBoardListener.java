package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveBoardListener implements ActionListener{
    private MainWindow mainWindow;

    public SaveBoardListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        mainWindow.showSaveDialog();
    }
}
