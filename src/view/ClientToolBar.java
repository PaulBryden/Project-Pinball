package view;

import controller.PrimaryActionListener;

public class ClientToolBar extends AbstractToolBar {

	private static final long serialVersionUID = -6104434962760638746L;

	ClientToolBar(MainWindow mainWindow, PrimaryActionListener listener) {
		super("Client Mode", mainWindow, listener);
	}
	
	@Override
	protected void populateButtons() {
		makeButton("run", "Run game");
		makeButton("pause", "Pause game");
		makeButton("disconnect", "Disconnect");
		disableButton("pause");
	}

}
