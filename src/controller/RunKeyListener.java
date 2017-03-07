package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.NoSuchElementException;

import model.IBall;
import model.IGizmo;
import model.IModel;
import view.Board;
import view.MainWindow;

import static controller.BoardMouseListener.STATE.KEY_CONNECT;

public class RunKeyListener implements KeyListener {

	private IModel model;
	private MainWindow mainWindow;

	private RunKeyListener(IModel model, MainWindow mainWindow) {
		this.model = model;
		this.mainWindow = mainWindow;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Board board = mainWindow.getBoard();
		int keyCode = e.getKeyCode();
		char keyChar = e.getKeyChar();

		if(board.getMouseListener().getState().equals(KEY_CONNECT)){
			try {
				IGizmo gizmo = board.getGizmo(board.getMouseListener().getGizmoCoords());
				model.addKeyPressedTrigger(keyCode, gizmo);
				model.addKeyReleasedTrigger(keyCode, gizmo);
				mainWindow.setStatusLabel(board.getGizmoName(gizmo) + " connected to the " + keyChar + " key.");
			} catch (NoSuchElementException E){
				IBall ball = board.getBall(board.getMouseListener().getGizmoCoords());
				model.addKeyPressedTrigger(keyCode, ball);
				model.addKeyReleasedTrigger(keyCode, ball);
				mainWindow.setStatusLabel("Ball connected to the " + keyChar + " key.");
			}
		} else {
			model.processKeyPressedTrigger(keyCode);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		model.processKeyReleasedTrigger(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) { }
	
	public static KeyListener createListener(IModel model, MainWindow mainWindow) {
		return new MagicKeyListener(new RunKeyListener(model, mainWindow));
	}

}
