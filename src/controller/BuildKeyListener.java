package controller;

import java.awt.event.KeyEvent;
import java.util.NoSuchElementException;

import model.IFlipper;
import model.IGizmo;
import model.IModel;
import physics.Vect;
import view.Board;
import view.MainWindow;

public class BuildKeyListener extends AbstractKeyListener {

	public BuildKeyListener(IModel model, MainWindow mainWindow) {
		super(model, mainWindow);
	}

	@Override
	protected void magicKeyPressed(KeyEvent e) {
		// Do nothing when key is pressed
	}

	@Override
	protected void magicKeyReleased(KeyEvent e) {
		Board board = mainWindow.getBoard();
		Vect gizmoCoords = board.getSelectedGizmoCoords();
		int keyCode = e.getKeyCode();
		char keyChar = e.getKeyChar();
		IGizmo gizmo;
		if (gizmoCoords != null) {
			try {
				gizmo = board.getModel().getGizmo(gizmoCoords);
			} catch (NoSuchElementException E) {
				gizmo = model.getBall(gizmoCoords);
			}
			// flippers are triggered on key pressed as well as key released
			// events:
			if (gizmo instanceof IFlipper) {
				model.addKeyPressedTrigger(keyCode, gizmo);
			}
			model.addKeyReleasedTrigger(keyCode, gizmo);
			mainWindow.setStatusLabel(board.getGizmoName(gizmo) + " connected to the " + keyChar + " key.");
		}
		this.setListening(false);
	}

	@Override
	protected void magicKeyTyped(KeyEvent e) {
		// Do nothing when key is typed
	}

}
