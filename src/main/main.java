package main;

import model.GameModel;
import view.MainWindow;

public class main {
	public static void main(String[] args) {
		GameModel gameloop = new GameModel();
		MainWindow mainWindow = new MainWindow();

		mainWindow.build();
	}

}
