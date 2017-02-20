package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.BoardFileHandler;
import model.IModel;

public class LoadBoardListener implements ActionListener{
    private MainWindow mainWindow;

    public LoadBoardListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	IModel model = mainWindow.getBoard().getModel();
        BoardFileHandler fh = new BoardFileHandler(model);
        model.reset();
        fh.load("spec_save_file.txt");
        mainWindow.getBoard().reRender();
    }
}
