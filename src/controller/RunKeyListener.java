package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.NoSuchElementException;

import model.IGizmo;
import model.IModel;
import physics.Vect;
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
		if (model.isClient()){
			model.addKeyToSend(e.getKeyCode());
		}else{
		model.processKeyPressedTrigger(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Board board = mainWindow.getBoard();
		Vect gizmoCoords = board.getMouseListener().getGizmoCoords();
		int keyCode = e.getKeyCode();
		char keyChar = e.getKeyChar();
		IGizmo gizmo;

		if(board.getMouseListener().getState().equals(KEY_CONNECT) && gizmoCoords != null){
			try {
				gizmo = board.getGizmo(gizmoCoords);
			} catch (NoSuchElementException E){
				gizmo = board.getBall(gizmoCoords);
			}

			model.addKeyPressedTrigger(keyCode, gizmo);
			model.addKeyReleasedTrigger(keyCode, gizmo);
			mainWindow.setStatusLabel(board.getGizmoName(gizmo) + " connected to the " + keyChar + " key.");
		} else {
			model.processKeyReleasedTrigger(keyCode);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) { }
	
	public static KeyListener createListener(IModel model, MainWindow mainWindow) {
		return new MagicKeyListener(new RunKeyListener(model, mainWindow));
	}

}
