package controller;

import view.MainWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.STATE.KEY_CONNECT;

public class AddKeyTriggerListener implements ActionListener{
	private MainWindow mainWindow;

	public AddKeyTriggerListener(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		mainWindow.addSideToolBar(new JToolBar());
		mainWindow.getBoard().getMouseListener().setState(KEY_CONNECT);
		mainWindow.setStatusLabel("Connecting gizmo to key. Please select a gizmo on the board.");
	}

}
