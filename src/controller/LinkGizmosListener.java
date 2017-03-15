package controller;

import view.MainWindow;

import javax.swing.JToolBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.STATE.GIZMO_CONNECT;

public class LinkGizmosListener implements ActionListener{
	private MainWindow mainWindow;

	public LinkGizmosListener(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainWindow.addSideToolBar(new JToolBar());
		mainWindow.getBoard().setState(GIZMO_CONNECT);
		mainWindow.setStatusLabel("Connecting gizmo to gizmo. Please select a gizmo on the board.");
	}

}
