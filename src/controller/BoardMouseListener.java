
package controller;

import model.GizmoFactory;
import model.GizmoFactory.TYPE;
import model.IAbsorber;
import model.IBall;
import model.ICounterGizmo;
import model.IGizmo;
import model.IModel;
import model.ISpinner;
import physics.Vect;
import view.Board;
import view.CUR_GIZMO;
import view.MainWindow;
import view.SelectSidePanel;

import java.awt.event.MouseEvent;

public class BoardMouseListener implements java.awt.event.MouseListener {
	private static final int GRID_WIDTH = 20;
	private MainWindow mainWindow;
	private IModel model;

	public BoardMouseListener(MainWindow mainWindow, IModel model) {
		this.mainWindow = mainWindow;
		this.model = model;
	}

	private void handleAdd(Vect coords, Board board) {
		if (board.getSelectedGizmo() == CUR_GIZMO.RFLIPPER || board.getSelectedGizmo() == CUR_GIZMO.SPINNER) {
			coords = new Vect(coords.x() - 1, coords.y());
		}
		if (model.isCellEmpty(coords)) {
			GizmoFactory gf = new GizmoFactory(model);
			switch (board.getSelectedGizmo()) {
			case ABSORBER:
				Vect initialAbsorberCoords = board.getSelectedGizmoCoords();
				if (initialAbsorberCoords == null) {
					board.setSelectedGizmoCoords(coords);
					mainWindow.setStatusLabel("Selected top-left cell of absorber at " + coordsString(coords));
				} else {
					double leftX = coords.x() < initialAbsorberCoords.x() ? coords.x() : initialAbsorberCoords.x();
					double rightX = coords.x() > initialAbsorberCoords.x() ? coords.x() : initialAbsorberCoords.x();
					double topY = coords.y() < initialAbsorberCoords.y() ? coords.y() : initialAbsorberCoords.y();
					double bottomY = coords.y() > initialAbsorberCoords.y() ? coords.y() : initialAbsorberCoords.y();
					Vect topLeft = new Vect(leftX, topY);
					Vect bottomRight = new Vect(rightX + 1, bottomY + 1);
					addGizmo(gf.getRectangularGizmo(TYPE.Absorber, topLeft, bottomRight));
					board.setSelectedGizmoCoords(null);
				}
				break;
			case COUNTER:
				Vect initialCounterCoords = board.getSelectedGizmoCoords();
				if (initialCounterCoords == null) {
					board.setSelectedGizmoCoords(coords);
					mainWindow.setStatusLabel("Selected top-left cell of counter gizmo at " + coordsString(coords));
				} else {
					double leftX = coords.x() < initialCounterCoords.x() ? coords.x() : initialCounterCoords.x();
					double rightX = coords.x() > initialCounterCoords.x() ? coords.x() : initialCounterCoords.x();
					double topY = coords.y() < initialCounterCoords.y() ? coords.y() : initialCounterCoords.y();
					double bottomY = coords.y() > initialCounterCoords.y() ? coords.y() : initialCounterCoords.y();
					Vect topLeft = new Vect(leftX, topY);
					Vect bottomRight = new Vect(rightX + 1, bottomY + 1);
					addGizmo(gf.getRectangularGizmo(TYPE.Counter, topLeft, bottomRight));
					board.setSelectedGizmoCoords(null);
				}
				break;
			case BALL:
				addBall(gf.getBall(coords.plus(new Vect(0.5, 0.5)), new Vect(13, 17)));
				break;
			case CIRCLE:
				addGizmo(gf.getGizmo(TYPE.Circle, coords));
				break;
			case LFLIPPER:
				addGizmo(gf.getGizmo(TYPE.LeftFlipper, coords));
				break;
			case RFLIPPER:
				addGizmo(gf.getGizmo(TYPE.RightFlipper, coords));
				break;
			case SQUARE:
				addGizmo(gf.getGizmo(TYPE.Square, coords));
				break;
			case TRIANGLE:
				addGizmo(gf.getGizmo(TYPE.Triangle, coords));
				break;
			case SPINNER:
				addGizmo((ISpinner) gf.getGizmo(TYPE.Spinner, coords));
				break;
			default:
				break;
			}
		} else {
			mainWindow.setWarningLabel("Cannot place here, this cell is already occupied. "
					+ "Select an empty cell or remove what is in this cell.");
		}
	}

	private void handleRemove(Vect coords, Board board) {
		if (model.getGizmo(coords) != null) {
			removeGizmo(coords);
		} else if (model.getBall(coords) != null) {
			removeBall(coords);
		} else {
			mainWindow.setWarningLabel("Cannot remove from here, this cell is empty. Select an occupied cell");
		}
	}

	private void handleMove(Vect coords, Board board) {
		Vect gizmoCoords = board.getSelectedGizmoCoords();
		if (gizmoCoords == null) { // Select a gizmo
			if (!model.isCellEmpty(coords)) {
				board.setSelectedGizmoCoords(coords);
				gizmoCoords = board.getSelectedGizmoCoords();
				IGizmo gizmo = model.getGizmo(gizmoCoords);
				if (gizmo == null) {
					gizmo = model.getBall(gizmoCoords);
				}
				mainWindow.setStatusLabel("Selected " + getGizmoName(gizmo) + " at " + coordsString(gizmoCoords)
						+ ". Please click a grid cell to move it to");
				mainWindow.getSidePanel()
						.setInstructions("Now click on an empty grid square in order to replace the gizmo.");
			} else {
				mainWindow.setWarningLabel("Empty cell selected.");
			}
		} else { // Move a gizmo
			moveGizmo(gizmoCoords, coords);
		}
	}

	private void handleRotate(Vect coords, Board board) {
		if (!model.isCellEmpty(coords)) {
			try {
				IGizmo gizmo = model.getGizmo(coords);
				if (gizmo instanceof IAbsorber || gizmo instanceof ICounterGizmo) {
					mainWindow.setWarningLabel("Cannot rotate rectangular gizmos");
					return;
				}
				gizmo.rotate(1);
				mainWindow.setStatusLabel(board.getGizmoName(gizmo) + " rotated");
			} catch (NullPointerException e) {
				mainWindow.setWarningLabel("Cannot rotate a ball. Select a gizmo.");
			}
		} else {
			mainWindow.setWarningLabel("Empty cell selected.");
		}
	}

	private void handleGizmoConnect(Vect coords, Board board) {
		Vect gizmoCoords = board.getSelectedGizmoCoords();
		if (!model.isCellEmpty(coords)) {
			if (gizmoCoords == null) {
				board.setSelectedGizmoCoords(coords);
				gizmoCoords = board.getSelectedGizmoCoords();
				try {
					mainWindow.setStatusLabel("Selected " + board.getGizmoName(model.getGizmo(gizmoCoords)) + " at "
							+ coordsString(gizmoCoords) + ". Please select a second gizmo to connect this gizmo to");
				} catch (NullPointerException e) {
					mainWindow.setWarningLabel("Cannot add gizmo connection to ball");
					board.setSelectedGizmoCoords(null);
				}
			} else {
				if (model.getGizmo(gizmoCoords).getGizmosToTrigger().contains(model.getGizmo(coords))) {
					mainWindow.setWarningLabel("Connection already exists between selected gizmos.");
				} else {
					model.getGizmo(gizmoCoords).addGizmoToTrigger(model.getGizmo(coords));
					mainWindow.setStatusLabel("Connected " + board.getGizmoName(model.getGizmo(gizmoCoords)) + " to "
							+ board.getGizmoName(model.getGizmo(coords)));
					board.setSelectedGizmoCoords(null);
				}
			}
		} else {
			mainWindow.setWarningLabel("Empty cell selected.");
		}
	}

	private void handleKeyConnect(Vect coords, Board board) {
		if (!model.isCellEmpty(coords)) {
			board.setSelectedGizmoCoords(coords);
			Vect gizmoCoords = board.getSelectedGizmoCoords();
			try {
				mainWindow.setStatusLabel("Selected " + board.getGizmoName(model.getGizmo(gizmoCoords)) + " at "
						+ coordsString(gizmoCoords) + ". Please type a key to connect this gizmo to");
				mainWindow.getBoard().getKeyConnectionList().update(board.getKeyConnections(model.getGizmo(coords)));
				mainWindow.getBuildKeyListener().setListening(true);
			} catch (NullPointerException e) {
				mainWindow.setWarningLabel("Cannot add key connection to ball");
				board.setSelectedGizmoCoords(null);
			}
		} else {
			mainWindow.setWarningLabel("Empty cell selected.");
		}
	}

	private void handleRemoveGizmoConnect(Vect coords, Board board) {
		Vect gizmoCoords = board.getSelectedGizmoCoords();
		if (!model.isCellEmpty(coords)) {
			if (gizmoCoords == null) {
				board.setSelectedGizmoCoords(coords);
				gizmoCoords = board.getSelectedGizmoCoords();
				try {
					mainWindow.setStatusLabel("Selected " + board.getGizmoName(model.getGizmo(gizmoCoords)) + " at "
							+ coordsString(gizmoCoords)
							+ ". Please select a connected gizmo to remove that connection.");
				} catch (NullPointerException e) {
					mainWindow.setWarningLabel("Balls do not have connections. Try a gizmo.");
					board.setSelectedGizmoCoords(null);
				}
			} else {
				try {
					board.removeGizmoConnection(board.getModel().getGizmo(gizmoCoords),
							board.getModel().getGizmo(coords));
					board.setSelectedGizmoCoords(null);
				} catch (NullPointerException e) {
					mainWindow.setWarningLabel("Cannot remove this connection. Click a gizmo.");
				}
			}
		} else {
			mainWindow.setWarningLabel("Empty cell selected.");
		}
	}

	private void handleRemoveKeyConnect(Vect coords, Board board) {
		if (!model.isCellEmpty(coords)) {
			board.setSelectedGizmoCoords(coords);
			Vect gizmoCoords = board.getSelectedGizmoCoords();
			try {
				mainWindow.setStatusLabel("Selected " + board.getGizmoName(model.getGizmo(gizmoCoords)) + " at "
						+ coordsString(gizmoCoords) + ". Please type the key you wish to remove.");
				mainWindow.getBoard().getKeyConnectionList().update(board.getKeyConnections(model.getGizmo(coords)));
				mainWindow.getBuildKeyListener().setListening(true);
			} catch (NullPointerException e) {
				mainWindow.setWarningLabel("Balls do not have connections. Try a gizmo.");
				board.setSelectedGizmoCoords(null);
			}
		} else {
			mainWindow.setWarningLabel("Empty cell selected.");
		}
	}

	private void handleSelect(Vect coords, Board board) {
		IGizmo gizmo = null;
		if (!model.isCellEmpty(coords)) {
			gizmo = model.getGizmo(coords);
			if (gizmo == null)
				gizmo = model.getBall(coords);
		}
		if (gizmo != null)
			mainWindow.setSidePanel(new SelectSidePanel(gizmo, mainWindow));
		else
			mainWindow.setSidePanel(new SelectSidePanel());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Vect coords = new Vect(e.getX() / GRID_WIDTH, e.getY() / GRID_WIDTH);
		Board board = mainWindow.getBoard();

		switch (board.getState()) {
		case ADD:
			handleAdd(coords, board);
			break;
		case REMOVE:
			handleRemove(coords, board);
			break;
		case MOVE:
			handleMove(coords, board);
			break;
		case ROTATE:
			handleRotate(coords, board);
			break;
		case SELECT:
			handleSelect(coords, board);
			break;
		case GIZMO_CONNECT:
			handleGizmoConnect(coords, board);
			break;
		case KEY_CONNECT:
			handleKeyConnect(coords, board);
			break;
		case RM_GIZMO_CONNECT:
			handleRemoveGizmoConnect(coords, board);
			break;
		case RM_KEY_CONNECT:
			handleRemoveKeyConnect(coords, board);
			break;
		default:
			break;
		}

		board.reRender();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	private String getGizmoName(IGizmo gizmo) {
		switch (gizmo.getID().charAt(0)) {
		case ('B'):
			return ("Ball");
		case ('A'):
			return ("Absorber");
		case ('C'):
			return ("Circle");
		case ('L'):
			return ("Left flipper");
		case ('R'):
			return ("Right flipper");
		case ('S'):
			return ("Square");
		case ('N'):
			return ("Counter gizmo");
		case ('P'):
			return ("Spinner gizmo");
		case ('T'):
			return ("Triangle");
		default:
			return ("Unknown");
		}
	}

	private void addGizmo(IGizmo gizmo) {
		Vect coords = gizmo.getGridCoords();
		if (!checkCellPlacementValid(gizmo, coords)) {
			return;
		}
		for (int x = (int) coords.x(); x < (int) coords.x() + gizmo.getGridWidth(); x++) {
			for (int y = (int) coords.y(); y < (int) coords.y() + gizmo.getGridHeight(); y++) {
				Vect cell = new Vect(x, y);
				if (!checkCellPlacementValid(gizmo, cell))
					return;
			}
		}
		model.addGizmo(gizmo);
		mainWindow.setStatusLabel(getGizmoName(gizmo) + " placed");
		mainWindow.getBoard().reRender();
	}

	private void addBall(IBall ball) {
		model.addBall(ball);
		mainWindow.setStatusLabel("Ball Placed");
		mainWindow.getBoard().reRender();
	}

	private void removeGizmo(Vect coords) {
		IGizmo gizmo = getGizmoOrBall(coords);
		if (gizmo instanceof IBall) {
			model.removeBall((IBall) gizmo);
			mainWindow.setStatusLabel("Ball removed");
		} else {
			model.removeGizmo(gizmo);
			mainWindow.setStatusLabel(getGizmoName(gizmo) + " removed");
		}
	}

	private void removeBall(Vect coords) {
		model.getBalls().remove(model.getBall(coords));
		mainWindow.setStatusLabel("Ball removed");
	}

	private void moveGizmo(Vect oldCoords, Vect newCoords) {
		if (oldCoords.equals(newCoords)) {
			mainWindow.setWarningLabel("Gizmo is already in this location.");
			return;
		}
		IGizmo gizmo = getGizmoOrBall(oldCoords);
		if (!checkCellPlacementValid(gizmo, newCoords)) {
			return;
		}
		for (int x = (int) newCoords.x(); x < (int) newCoords.x() + gizmo.getGridWidth(); x++) {
			for (int y = (int) newCoords.y(); y < (int) newCoords.y() + gizmo.getGridHeight(); y++) {
				Vect cell = new Vect(x, y);
				if (!checkCellPlacementValid(gizmo, cell))
					return;
			}
		}
		if (gizmo instanceof IBall) {
			newCoords = newCoords.plus(new Vect(0.5, 0.5));
		}
		gizmo.setGridCoords(newCoords);
		mainWindow.setStatusLabel(
				"Moved " + getGizmoName(gizmo) + " from " + coordsString(oldCoords) + " to " + coordsString(newCoords));
		mainWindow.getBoard().setSelectedGizmoCoords(null);
	}

	private IGizmo getGizmoOrBall(Vect coords) {
		IGizmo gizmo = model.getGizmo(coords);
		if (gizmo == null) {
			gizmo = model.getBall(coords);
		}
		return gizmo;
	}

	private boolean checkCellPlacementValid(IGizmo gizmo, Vect cell) {
		if (!model.isCellEmpty(cell) && !getGizmoOrBall(cell).equals(gizmo)) {
			mainWindow.setWarningLabel("Cannot place " + getGizmoName(gizmo) + " in a non-empty cell.");
			mainWindow.getBoard().setSelectedGizmoCoords(null);
			return false;
		} else if (cell.x() > 19 || cell.y() > 19 || cell.x() < 0 || cell.y() < 0) {
			mainWindow.setWarningLabel("Cannot place " + getGizmoName(gizmo) + " outside of board.");
			mainWindow.getBoard().setSelectedGizmoCoords(null);
			return false;
		}
		return true;
	}

	private String coordsString(Vect coords) {
		return "(" + (int) coords.x() + ", " + (int) coords.y() + ")";
	}
}
