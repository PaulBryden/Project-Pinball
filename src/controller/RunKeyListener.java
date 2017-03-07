package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.IGizmo;
import model.IModel;
import view.Board;
import view.MainWindow;

import static controller.BoardMouseListener.STATE.KEY_CONNECT;

public class RunKeyListener implements KeyListener {

	private IModel model;
	private MainWindow mainWindow;

	public RunKeyListener(IModel model, MainWindow mainWindow) {
		this.model = model;
		this.mainWindow = mainWindow;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Board board = mainWindow.getBoard();
		int keyCode = e.getKeyCode();
		System.out.println(e.getKeyCode());

		if(board.getMouseListener().getState().equals(KEY_CONNECT)){
			IGizmo gizmo =  board.getGizmo(board.getMouseListener().getGizmoCoords());
			board.getModel().addKeyPressedTrigger(keyCode, gizmo);
            board.getModel().addKeyReleasedTrigger(keyCode, gizmo);
            mainWindow.setStatusLabel("Gizmo connected to key");
		} else {
			model.processKeyPressedTrigger(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		model.processKeyReleasedTrigger(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) { }
	
	public static final KeyListener createListener(IModel model, MainWindow mainWindow) {
		return new MagicKeyListener(new RunKeyListener(model, mainWindow));
	}

}
