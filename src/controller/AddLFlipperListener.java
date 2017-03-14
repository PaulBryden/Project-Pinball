package controller;

import view.MainWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.Board.CUR_GIZMO.LFLIPPER;

public class AddLFlipperListener implements ActionListener {
	private MainWindow mainWindow;

	public AddLFlipperListener(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainWindow.getBoard().setSelectedGizmo(LFLIPPER);
		mainWindow.setStatusLabel("Placing Left-Flipper. Please click a grid cell to place it.");
	}
}
