package controller;

import view.MainWindow;

import java.io.IOException;
import java.util.InputMismatchException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import model.BoardFileHandler;
import model.IModel;

public class LoadBoardController {

	private MainWindow mainWindow;

	LoadBoardController(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public void start() {
		IModel model = mainWindow.getBoard().getModel();
		JFileChooser fc = new JFileChooser(".");
		int returnVal = fc.showOpenDialog(mainWindow);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			BoardFileHandler fh = new BoardFileHandler(model);
			model.reset();
			String path = fc.getSelectedFile().getAbsolutePath();
			try {
				fh.load(path);
				mainWindow.getBoard().reRender();
			} catch (IOException | InputMismatchException e1) {
				JOptionPane.showMessageDialog(mainWindow, "Error reading from file");
			}
		}
	}
}
