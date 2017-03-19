package controller;

import model.GizmoFactory;
import model.GizmoFactory.TYPE;
import model.IAbsorber;
import model.ICircleGizmo;
import model.ICounterGizmo;
import model.IFlipper;
import model.IGizmo;
import model.IModel;
import physics.Vect;
import view.AbsorberView;
import view.Board;
import view.CUR_GIZMO;
import view.CircleView;
import view.CounterGizmoView;
import view.FlipperView;
import view.MainWindow;
import view.SquareView;
import view.TriangleView;

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
		if (board.getSelectedGizmo() == CUR_GIZMO.RFLIPPER) {
			coords = new Vect(coords.x() - 1, coords.y());
		}
		if (model.isCellEmpty(coords)) {
			GizmoFactory gf = new GizmoFactory(model);
			switch (board.getSelectedGizmo()) {
			case ABSORBER:
				Vect initalAbsorberCoords = board.getSelectedGizmoCoords();
				if (initalAbsorberCoords == null) {
					board.setSelectedGizmoCoords(coords);
					mainWindow.setStatusLabel("Selected top-left cell of absorber at " + coords);
				} else {
					if (coords.x() < initalAbsorberCoords.x() || coords.y() < initalAbsorberCoords.y()) {
						mainWindow.setWarningLabel(
								"Invalid cell, you might want to make that the " + "top-left cell, try again");
					} else {
						board.addGizmo(new AbsorberView(gf.getRectangularGizmo(TYPE.Absorber, initalAbsorberCoords,
								new Vect(coords.x() + 1, coords.y() + 1))));
					}
					board.setSelectedGizmoCoords(null);
				}
				break;
			case COUNTER:
				Vect initalCounterCoords = board.getSelectedGizmoCoords();
				if (initalCounterCoords == null) {
					board.setSelectedGizmoCoords(coords);
					mainWindow.setStatusLabel("Selected top-left cell of counter gizmo at " + coords);
				} else {
					if (coords.x() < initalCounterCoords.x() || coords.y() < initalCounterCoords.y()) {
						mainWindow.setWarningLabel(
								"Invalid cell, you might want to make that the " + "top-left cell, try again");
					} else {
						board.addGizmo(new CounterGizmoView(gf.getRectangularGizmo(TYPE.Counter, initalCounterCoords,
								new Vect(coords.x() + 1, coords.y() + 1)), model.getTextColour()));
					}
					board.setSelectedGizmoCoords(null);
				}
				break;
			case BALL:
				board.addBall(gf.getBall(coords.plus(new Vect(0.5, 0.5)), new Vect(13, 17)));
				break;
			case CIRCLE:
				board.addGizmo(new CircleView((ICircleGizmo) gf.getGizmo(TYPE.Circle, coords)));
				break;
			case LFLIPPER:
				board.addGizmo(new FlipperView((IFlipper) gf.getGizmo(TYPE.LeftFlipper, coords)));
				break;
			case RFLIPPER:
				board.addGizmo(new FlipperView((IFlipper) gf.getGizmo(TYPE.RightFlipper, coords)));
				break;
			case SQUARE:
				board.addGizmo(new SquareView(gf.getGizmo(TYPE.Square, coords)));
				break;
			case TRIANGLE:
				board.addGizmo(new TriangleView(gf.getGizmo(TYPE.Triangle, coords)));
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
			board.removeGizmo(coords);
		} else if (model.getBall(coords) != null) {
			board.removeBall(coords);
		} else {
			mainWindow.setWarningLabel("Cannot remove from here, this cell is empty. Select an occupied cell");
		}
	}

	private void handleMove(Vect coords, Board board) {
		Vect gizmoCoords = board.getSelectedGizmoCoords();
		if (!model.isCellEmpty(coords)) {
			board.setSelectedGizmoCoords(coords);
			gizmoCoords = board.getSelectedGizmoCoords();
			try {
				mainWindow.setStatusLabel("Selected " + board.getGizmoName(model.getGizmo(gizmoCoords)) + " at "
						+ gizmoCoords + ". Please click a grid cell to move it to");
				mainWindow.getSidePanel()
						.setInstructions("Now click on an empty grid square in order to replace the gizmo.");
			} catch (NullPointerException e) {
				mainWindow.setStatusLabel("Selected Ball at " + gizmoCoords);
			}
		} else if (model.isCellEmpty(coords) && gizmoCoords != null) {
			try {
				board.moveGizmo(gizmoCoords, coords);
			} catch (NullPointerException e) {
				board.moveBall(gizmoCoords, coords);
			}
			board.setSelectedGizmoCoords(null);
		} else {
			mainWindow.setWarningLabel("Cannot move from here, this cell is empty. Select an occupied cell.");
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
				mainWindow.setStatusLabel("" + board.getGizmoName(gizmo) + " Rotated");
			} catch (NullPointerException e) {
				mainWindow.setWarningLabel("Cannot rotate a ball. Select a gizmo.");
			}
		} else {
			mainWindow.setWarningLabel("Cannot rotate, this cell is empty. Select an occupied cell.");
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
							+ gizmoCoords + ". Please select a second gizmo to connect this gizmo to");
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
			mainWindow.setWarningLabel("Cannot add gizmo connection, this cell is empty. Select an occupied cell.");
		}
	}

	private void handleKeyConnect(Vect coords, Board board) {
		if (!model.isCellEmpty(coords)) {
			board.setSelectedGizmoCoords(coords);
			Vect gizmoCoords = board.getSelectedGizmoCoords();
			try {
				mainWindow.setStatusLabel("Selected " + board.getGizmoName(model.getGizmo(gizmoCoords)) + " at "
						+ gizmoCoords + ". Please type a key to connect this gizmo to");
				mainWindow.getBuildKeyListener().setListening(true);
			} catch (NullPointerException e) {
				mainWindow.setWarningLabel("Cannot add key connection to ball");
				board.setSelectedGizmoCoords(null);
			}
		} else {
			mainWindow.setWarningLabel("Cannot add key connection, this cell is empty. Select an occupied cell.");
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
							+ gizmoCoords + ". Please select a connected gizmo to remove that connection.");
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
			mainWindow.setWarningLabel("Cannot remove gizmo connection, this cell is empty. Select an occupied cell.");
		}
	}

	private void handleRemoveKeyConnect(Vect coords, Board board) {
		if (!model.isCellEmpty(coords)) {
			board.setSelectedGizmoCoords(coords);
			Vect gizmoCoords = board.getSelectedGizmoCoords();
			try {
				mainWindow.setStatusLabel("Selected " + board.getGizmoName(model.getGizmo(gizmoCoords)) + " at "
						+ gizmoCoords + ". Please type the key you wish to remove.");
				mainWindow.getBuildKeyListener().setListening(true);
			} catch (NullPointerException e) {
				mainWindow.setWarningLabel("Balls do not have connections. Try a gizmo.");
				board.setSelectedGizmoCoords(null);
			}
		} else {
			mainWindow.setWarningLabel("Cannot remove key connection, this cell is empty. Select an occupied cell.");
		}
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
}
