package main;

import javax.swing.SwingUtilities;

import model.GameModel;
import model.IModel;
import view.MainWindow;

public class Main {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				IModel model = new GameModel();
				MainWindow mainWindow = new MainWindow(model);
				mainWindow.build();
				model.addObserver(mainWindow.getBoard());
			}
		});
	}

}
