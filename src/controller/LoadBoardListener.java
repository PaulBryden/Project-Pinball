package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
        JFileChooser fc = new JFileChooser(".");
        int returnVal = fc.showOpenDialog(fc);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	String path = fc.getSelectedFile().getAbsolutePath();
        	try {
        		fh.load(path);
        		mainWindow.getBoard().reRender();
        	} catch (IOException e1) {
        		System.out.println("Error reading from file");
        		e1.printStackTrace();
        		JOptionPane.showMessageDialog(mainWindow, "Error reading from file");
        	}
        }
        
    }
}
