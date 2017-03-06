package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainWindow;
import view.MoveGizmoToolBar;
import view.RotateGizmoToolBar;

import static controller.BoardMouseListener.STATE.MOVE;

public class MoveGizmoListener  implements ActionListener{
	private MainWindow mainWindow;

	public MoveGizmoListener(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(!(mainWindow.getSideToolBar() instanceof MoveGizmoToolBar)) {
			mainWindow.addSideToolBar(new MoveGizmoToolBar());
			mainWindow.getBoard().getMouseListener().setState(MOVE);
			mainWindow.setStatusLabel("Moving Gizmo(s). Please click a gizmo on the board");
		}
	}
}
