package main;

import javax.swing.SwingUtilities;

import model.IModel;
import model.ModelFactory;
import view.MainWindow;

public class Main {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
            IModel model = ModelFactory.getModel();
            MainWindow mainWindow = new MainWindow(model);
            mainWindow.build();
            model.addObserver(mainWindow.getBoard());
        });
	}

}
