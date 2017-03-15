package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.NoSuchElementException;

import model.IFlipper;
import model.IGizmo;
import model.IModel;
import physics.Vect;
import view.Board;
import view.MainWindow;

import static view.STATE.KEY_CONNECT;

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
			model.addKeyToSend(" Pressed "+e.getKeyCode()+" /n");
		}else{
		model.processKeyPressedTrigger(e.getKeyCode());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (model.isClient()){
			model.addKeyToSend(" Released "+e.getKeyCode()+" /n");
			System.out.println("Firing Key Released Event");
		}else{
		Board board = mainWindow.getBoard();
		Vect gizmoCoords = board.getSelectedGizmoCoords();
		int keyCode = e.getKeyCode();
		char keyChar = e.getKeyChar();
		IGizmo gizmo;

		if (board.getState().equals(KEY_CONNECT) && gizmoCoords != null) {
			try {
				gizmo = board.getModel().getGizmo(gizmoCoords);
			} catch (NoSuchElementException E) {
				gizmo = model.getBall(gizmoCoords);
			}
			if (gizmo instanceof IFlipper) {
				model.addKeyPressedTrigger(keyCode, gizmo);
			}
			model.addKeyReleasedTrigger(keyCode, gizmo);
			mainWindow.setStatusLabel(board.getGizmoName(gizmo) + " connected to the " + keyChar + " key.");
		} else {
			model.processKeyReleasedTrigger(keyCode);
		}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public static KeyListener createListener(IModel model, MainWindow mainWindow) {
		return new MagicKeyListener(new RunKeyListener(model, mainWindow));
	}

}
