package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.BoardFileHandler;
import model.GameModel;

public class LoadBoardListener implements ActionListener{
    private MainWindow mainWindow;

    public LoadBoardListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        BoardFileHandler file = new BoardFileHandler();

        file.load((GameModel) mainWindow.getBoard().getModel(),
                "spec_save_file.txt");
        mainWindow.revalidate();
        mainWindow.repaint();
    }
}
