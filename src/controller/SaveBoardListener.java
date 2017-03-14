package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.BoardFileHandler;
import model.IModel;

public class SaveBoardListener implements ActionListener{
    private MainWindow mainWindow;

    public SaveBoardListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	IModel model = mainWindow.getBoard().getModel();
        BoardFileHandler fh = new BoardFileHandler(model);

        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(fc);
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	String path = fc.getSelectedFile().getAbsolutePath();
        	try {
        		fh.save(path);
        	} catch (IOException e1) {
        		System.out.println("Error saving to file");
        		e1.printStackTrace();
        		JOptionPane.showMessageDialog(mainWindow, "Error saving to file");
        	}
        }
    }
}
