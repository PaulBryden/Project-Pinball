package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
        try {
			fh.load("spec_save_file.txt");
		} catch (IOException e1) {
			System.out.println("Error reading from file");
			e1.printStackTrace();
		}
        mainWindow.getBoard().reRender();
    }
}
