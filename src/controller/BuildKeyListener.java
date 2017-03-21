package controller;

import java.awt.event.KeyEvent;

import model.IFlipper;
import model.IGizmo;
import model.IModel;
import physics.Vect;
import view.Board;
import view.MainWindow;

import static view.STATE.RM_KEY_CONNECT;

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
		String keyString = KeyEvent.getKeyText(keyCode);
		IGizmo gizmo;

		if (gizmoCoords != null) {
			gizmo = board.getModel().getGizmo(gizmoCoords);
			if(board.getState().equals(RM_KEY_CONNECT)){
				board.removeKeyConnection(gizmo, keyCode);
				mainWindow.setStatusLabel("The " + keyString + " key has been removed from " + board.getGizmoName(gizmo));
			} else {
				// flippers are triggered on key pressed as well as key released
				// events:
				if (gizmo instanceof IFlipper) {
					model.addKeyPressedTrigger(keyCode, gizmo);
				}
				model.addKeyReleasedTrigger(keyCode, gizmo);
				mainWindow.setStatusLabel(board.getGizmoName(gizmo) + " connected to the " + keyString + " key.");
			}
		}
		this.setListening(false);
	}

	@Override
	protected void magicKeyTyped(KeyEvent e) {
		// Do nothing when key is typed
	}

}
