package controller;

import model.IModel;
import view.MainWindow;

public class DeleteAllGizmosController {
	
	private MainWindow mainWindow;
	
	public DeleteAllGizmosController(MainWindow mainWindow) {
			this.mainWindow = mainWindow;
	}
	
	public void start() {
		IModel model = mainWindow.getModel();
		model.reset();
		mainWindow.getBoard().reRender();
	}

}
