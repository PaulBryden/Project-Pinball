package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.CUR_GIZMO.LFLIPPER;

public class AddLFlipperListener implements ActionListener {
	private MainWindow mainWindow;

	public AddLFlipperListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainWindow.getBoard().getMouseListener().setGizmo(LFLIPPER);
		mainWindow.setStatusLabel("Adding Left-Flipper");
	}
}
