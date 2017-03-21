package controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.IFlipper;
import model.IGizmo;
import model.IModel;
import physics.Vect;
import view.Board;
import view.ConnectionSidePanel;
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
				try {
					if (removeKeyConnection(gizmo, keyCode)) {
						board.determineKeyConnectionsVisibility(gizmoCoords);
						mainWindow.setStatusLabel("The " + keyString + " key has been removed from " + board.getGizmoName(gizmo));
					} else {
						mainWindow.setWarningLabel("That key is not connected to this gizmo");
					}
				} catch (NullPointerException E) {
					mainWindow.setWarningLabel("That key is not connected to this gizmo");
				}
			} else {
				// flippers are triggered on key pressed as well as key released
				// events:
				if (gizmo instanceof IFlipper) {
					model.addKeyPressedTrigger(keyCode, gizmo);
				}
				model.addKeyReleasedTrigger(keyCode, gizmo);
				mainWindow.setStatusLabel(board.getGizmoName(gizmo) + " connected to the " + keyString + " key.");
				determineKeyConnectionsVisibility(gizmo);
			}
		}
		this.setListening(false);
	}

	private void determineKeyConnectionsVisibility(IGizmo gizmo) {
		ConnectionSidePanel csp = (ConnectionSidePanel) mainWindow.getSidePanel();
		if (gizmo == null) {
			csp.setKeyConnectionsVisible(false);
			return;
		}
		csp.setExistingConnectionInfo(gizmo.getID(), getKeyConnections(gizmo));
		csp.setKeyConnectionsVisible(true);
	}

	private String getKeyConnections(IGizmo gizmo){
		List<String> l = new ArrayList<>();
		for(int i : model.getKeyPressedTriggers().keySet()){
			for(IGizmo gizmoToTrigger : model.getKeyPressedTriggers().get(i).getGizmosToTrigger()){
				if(gizmoToTrigger.equals(gizmo)){
					l.add(KeyEvent.getKeyText(i));
				}
			}
		}

		for(int i : model.getKeyReleasedTriggers().keySet()){
			for (IGizmo gizmoToTrigger : model.getKeyReleasedTriggers().get(i).getGizmosToTrigger()){
				if(gizmoToTrigger.equals(gizmo) && !l.contains(KeyEvent.getKeyText(i))){
					l.add(KeyEvent.getKeyText(i));
				}
			}
		}

		if(l.isEmpty())
			return "[None]";
		return String.join(", ", l);
	}

	private boolean removeKeyConnection(IGizmo gizmo, int keyCode) {
		boolean removed = false;

		for (Iterator<IGizmo> iterator = model.getKeyReleasedTriggers().get(keyCode).getGizmosToTrigger().iterator(); iterator.hasNext();){
			if(iterator.next().equals(gizmo)) {
				iterator.remove();
				removed = true;
			}
		}

		if(gizmo instanceof IFlipper && removed)
			model.getKeyPressedTriggers().get(keyCode).getGizmosToTrigger().removeIf(gizmo1 -> gizmo1.equals(gizmo));

		return (removed);
	}

	@Override
	protected void magicKeyTyped(KeyEvent e) {
		// Do nothing when key is typed
	}

}
