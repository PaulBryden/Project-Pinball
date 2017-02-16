package main;

import javax.swing.SwingUtilities;

import model.GameModel;
import view.MainWindow;

public class main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            GameModel model = new GameModel();
            MainWindow mainWindow = new MainWindow(model);
            mainWindow.build();
            model.addObserver(mainWindow.getBoard());
        });
	}

}
