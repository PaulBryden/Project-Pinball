package controller;

import model.Absorber;
import model.BallGizmo;
import model.CircleGizmo;
import model.IGizmo;
import model.IModel;
import model.LeftFlipper;
import model.RightFlipper;
import model.SquareGizmo;
import model.TriangleGizmo;
import physics.Vect;
import view.AbsorberView;
import view.Board;
import view.CircleView;
import view.FlipperView;
import view.MainWindow;
import view.SquareView;
import view.TriangleView;

import java.awt.event.MouseEvent;
import java.util.NoSuchElementException;

public class BoardMouseListener implements java.awt.event.MouseListener {
	private static final int GRID_WIDTH = 20;
	private MainWindow mainWindow;
	private IModel model;

	public BoardMouseListener(MainWindow mainWindow, IModel model) {
		this.mainWindow = mainWindow;
		this.model = model;
	}

	private void handleAdd(Vect coords, Board board) {
		if (model.isCellEmpty(coords)) {
			String id = coords.x() + "" + coords.y();
			switch (board.getSelectedGizmo()) {
			case ABSORBER:
				Vect initalAbsorberCoords = board.getInitalAbsorberCoords();
				if (initalAbsorberCoords == null) {
					board.setInitalAbsorberCoords(coords);
					mainWindow.setStatusLabel("Selected top-left cell of absorber at " + coords);
				} else {
					if (coords.x() < initalAbsorberCoords.x() || coords.y() < initalAbsorberCoords.y()) {
						mainWindow.setWarningLabel(
								"Invalid cell, you might want to make that the " + "top-left cell, try again");
					} else {
						board.addGizmo(new AbsorberView(new Absorber(model, "A" + id, initalAbsorberCoords,
								new Vect(coords.x() + 1, coords.y() + 1))));
					}
					board.setInitalAbsorberCoords(null);
				}
				break;
			case BALL:
				board.addBall(new BallGizmo("B" + id, coords.plus(new Vect(0.5, 0.5)), new Vect(13, 17)));
				break;
			case CIRCLE:
				board.addGizmo(new CircleView(new CircleGizmo("C" + id, coords)));
				break;
			case LFLIPPER:
				board.addGizmo(new FlipperView(new LeftFlipper("LF" + id, coords)));
				break;
			case RFLIPPER:
				board.addGizmo(new FlipperView(new RightFlipper("RF" + id, coords)));
				break;
			case SQUARE:
				board.addGizmo(new SquareView(new SquareGizmo("S" + id, coords)));
				break;
			case TRIANGLE:
				board.addGizmo(new TriangleView(new TriangleGizmo("T" + id, coords)));
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
		Vect gizmoCoords = board.getGizmoCoords();
		if (!model.isCellEmpty(coords)) {
			board.setGizmoCoords(coords);
			gizmoCoords = board.getGizmoCoords();
			try {
				mainWindow.setStatusLabel("Selected " + board.getGizmoName(model.getGizmo(gizmoCoords)) + " at "
						+ gizmoCoords + ". Please click a grid cell to move it to");
			} catch (NoSuchElementException e) {
				mainWindow.setStatusLabel("Selected Ball at " + gizmoCoords);
			}
		} else if (model.isCellEmpty(coords) && gizmoCoords != null) {
			try {
				board.moveGizmo(gizmoCoords, coords);
			} catch (NoSuchElementException e) {
				board.moveBall(gizmoCoords, coords);
			}
			board.setGizmoCoords(null);
		} else {
			mainWindow.setWarningLabel("Cannot move from here, this cell is empty. Select an occupied cell.");
		}
	}

	private void handleRotate(Vect coords, Board board) {
		if (!model.isCellEmpty(coords)) {
			try {
				IGizmo gizmo = model.getGizmo(coords);
				gizmo.rotate(1);
				mainWindow.setStatusLabel("" + board.getGizmoName(gizmo) + " Rotated");
			} catch (NoSuchElementException e) {
				mainWindow.setWarningLabel("Cannot rotate a ball. Select a gizmo.");
			}
		} else {
			mainWindow.setWarningLabel("Cannot rotate, this cell is empty. Select an occupied cell.");
		}
	}

	private void handleGizmoConnect(Vect coords, Board board) {
		Vect gizmoCoords = board.getGizmoCoords();
		if (!model.isCellEmpty(coords)) {
			if (gizmoCoords == null) {
				board.setGizmoCoords(coords);
				gizmoCoords = board.getGizmoCoords();
				try {
					mainWindow.setStatusLabel("Selected " + board.getGizmoName(model.getGizmo(gizmoCoords)) + " at "
							+ gizmoCoords + ". Please type a key to connect this gizmo to");
				} catch (NoSuchElementException e) {
					mainWindow.setStatusLabel(
							"Selected ball at " + gizmoCoords + ". Please type a key to connect this ball to");
				}
			} else {
				model.getGizmo(gizmoCoords).addGizmoToTrigger(model.getGizmo(coords));
				mainWindow.setStatusLabel("Connected " + board.getGizmoName(model.getGizmo(gizmoCoords)) + " to "
						+ board.getGizmoName(model.getGizmo(coords)));
				board.setGizmoCoords(null);
			}
		} else {
			mainWindow.setWarningLabel("Cannot add gizmo connection, this cell is empty. Select an occupied cell.");
		}
	}

	private void handleKeyConnect(Vect coords, Board board) {
		if (!model.isCellEmpty(coords)) {
			board.setGizmoCoords(coords);
			Vect gizmoCoords = board.getGizmoCoords();
			try {
				mainWindow.setStatusLabel("Selected " + board.getGizmoName(model.getGizmo(gizmoCoords)) + " at "
						+ gizmoCoords + ". Please type a key to connect this gizmo to");
			} catch (NoSuchElementException e) {
				mainWindow.setStatusLabel(
						"Selected ball at " + gizmoCoords + ". Please type a key to connect this ball to");
			}
		} else {
			mainWindow.setWarningLabel("Cannot add key connection, this cell is empty. Select an occupied cell.");
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
