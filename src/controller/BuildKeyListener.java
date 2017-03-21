package controller;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.IFlipper;
import model.IGizmo;
import model.IModel;
import model.KeyTrigger;
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
		char keyChar = e.getKeyChar();
		IGizmo gizmo;

		if (gizmoCoords != null) {
			gizmo = board.getModel().getGizmo(gizmoCoords);
			if(board.getState().equals(RM_KEY_CONNECT)){
				removeKeyConnections(model.getKeyPressedTriggers(), gizmo);
				removeKeyConnections(model.getKeyReleasedTriggers(), gizmo);
				mainWindow.setStatusLabel("The " + keyChar + " key has been removed from " + board.getGizmoName(gizmo));
				determineKeyConnectionsVisibility(gizmo, board);
			} else {
				// flippers are triggered on key pressed as well as key released
				// events:
				if (gizmo instanceof IFlipper) {
					model.addKeyPressedTrigger(keyCode, gizmo);
				}
				model.addKeyReleasedTrigger(keyCode, gizmo);
				mainWindow.setStatusLabel(board.getGizmoName(gizmo) + " connected to the " + keyChar + " key.");
			}
		}
		this.setListening(false);
	}

	public void determineKeyConnectionsVisibility(IGizmo gizmo, Board board) {
		ConnectionSidePanel csp = (ConnectionSidePanel) mainWindow.getSidePanel();
		if (gizmo == null) {
			csp.setKeyConnectionsVisible(false);
			return;
		}
		csp.setExistingConnectionInfo(gizmo.getID(), getKeyConnections(gizmo));
		csp.setKeyConnectionsVisible(true);
	}

	public String getKeyConnections(IGizmo gizmo){
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
	
	private void removeKeyConnections(Map<Integer, KeyTrigger> map, IGizmo gizmo) {
		for (KeyTrigger keyTrigger : map.values()) {
			keyTrigger.getGizmosToTrigger().removeIf(gizmo1 -> gizmo1.equals(gizmo));
		}
	}

	@Override
	protected void magicKeyTyped(KeyEvent e) {
		// Do nothing when key is typed
	}

}
