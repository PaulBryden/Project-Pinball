package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;
import view.SidePanel;

import static view.STATE.MOVE;

public class MoveGizmoListener  implements ActionListener {
	private MainWindow mainWindow;

	public MoveGizmoListener(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
			mainWindow.setSidePanel(new SidePanel());
			mainWindow.getBoard().setState(MOVE);
			mainWindow.setStatusLabel("Moving Gizmo(s). Please click a gizmo on the board");
		}
}
