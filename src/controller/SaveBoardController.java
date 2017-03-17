package controller;

import view.MainWindow;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.BoardFileHandler;
import model.IModel;

public class SaveBoardController {
	
    private MainWindow mainWindow;

    public SaveBoardController(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }

    public void start() {
    	IModel model = mainWindow.getBoard().getModel();
        BoardFileHandler fh = new BoardFileHandler(model);

        JFileChooser fc = new JFileChooser(".") {
        	@Override
        	public void approveSelection() {
        		File file = getSelectedFile();
        		if (file.exists() && getDialogType() == SAVE_DIALOG) {
        			int result = JOptionPane.showConfirmDialog(this, "File already exists. Overwrite?", "Existing file", JOptionPane.YES_NO_CANCEL_OPTION);
        			switch (result) {
        			case JOptionPane.YES_OPTION:
        				super.approveSelection();
        				return;
        			case JOptionPane.NO_OPTION:
        			case JOptionPane.CLOSED_OPTION:
        				return;
        			case JOptionPane.CANCEL_OPTION:
        				cancelSelection();
        				return;
        			}
        		}
        		super.approveSelection();
        	}
        };
        
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
