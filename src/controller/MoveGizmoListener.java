package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;

import javax.swing.JToolBar;

import static view.STATE.MOVE;

public class MoveGizmoListener  implements ActionListener {
	private MainWindow mainWindow;

	public MoveGizmoListener(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			mainWindow.addSideToolBar(new JToolBar());
			mainWindow.getBoard().setState(MOVE);
			mainWindow.setStatusLabel("Moving Gizmo(s). Please click a gizmo on the board");
		}
}
