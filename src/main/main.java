package main;

import javax.swing.SwingUtilities;

import model.BoardFileHandler;
import model.GameModel;
import model.IModel;
import view.MainWindow;

public class main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GameModel model = new GameModel();
				BoardFileHandler file = new BoardFileHandler();

				file.load(model, "spec_save_file.txt");
				MainWindow mainWindow = new MainWindow(model);
				mainWindow.build();
				model.addObserver(mainWindow.getBoard());
			}
		});
	}

}
