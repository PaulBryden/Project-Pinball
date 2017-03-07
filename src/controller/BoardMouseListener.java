package controller;

import model.Absorber;
import model.BallGizmo;
import model.CircleGizmo;
import model.IGizmo;
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

import static controller.BoardMouseListener.CUR_GIZMO.NONE;
import static controller.BoardMouseListener.STATE.BUILD;

public class BoardMouseListener implements java.awt.event.MouseListener{
    public enum STATE {
        BUILD, RUN, ADD, REMOVE, MOVE, ROTATE, GIZMO_CONNECT, KEY_CONNECT
    }
    public enum CUR_GIZMO {
        BALL, SQUARE, TRIANGLE, LFLIPPER, RFLIPPER, CIRCLE, ABSORBER, NONE
    }
    private static final int GRID_WIDTH = 20;
    private STATE state;
    private CUR_GIZMO gizmo;
    private MainWindow mainWindow;
    private Vect gizmoCoords;
    private Vect initalAbsorberCoords;

    public BoardMouseListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
        state = BUILD;
        gizmo = NONE;
    }

    private void resetStoredCoords(){
        gizmoCoords = null;
        initalAbsorberCoords = null;
    }

    void setState(STATE state){
        resetStoredCoords();
        this.state = state;
    }

    void setGizmo(CUR_GIZMO gizmo){
        resetStoredCoords();
        this.gizmo = gizmo;
    }

    public STATE getState(){
        return (state);
    }

    Vect getGizmoCoords(){
        return (gizmoCoords);
    }

    private void handleAdd(Vect coords, Board board){
        if(board.isCellEmpty(coords)) {
            String id = coords.x() + "" + coords.y();
            switch (gizmo) {
                case ABSORBER:
                    if(initalAbsorberCoords == null){
                        initalAbsorberCoords = coords;
                        mainWindow.setStatusLabel("Selected top-left cell of absorber at " + coords);
                    } else {
                        if(coords.x() < initalAbsorberCoords.x() || coords.y() < initalAbsorberCoords.y()) {
                            mainWindow.setWarningLabel("Invalid cell, you might want to make that the " +
                                    "top-left cell, try again");
                        } else {
                            board.addGizmo(new AbsorberView(new Absorber("A", initalAbsorberCoords,
                                   new Vect(coords.x()+1, coords.y()+1), board.getModel().getBalls())));
                        }
                        initalAbsorberCoords = null;
                    }
                    break;
                case BALL:
                    board.addBall(new BallGizmo("B", coords.plus(new Vect(0.5, 0.5)), new Vect(13, 17)));
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
            mainWindow.setWarningLabel("Cannot place here, this cell is already occupied. " +
                    "Select an empty cell or remove what is in this cell.");
        }
    }

    private void handleRemove(Vect coords, Board board){
        if(!board.isCellEmpty(coords)){
            try {
                board.removeGizmo(coords);
            } catch (NoSuchElementException e) {
                board.removeBall(coords);
            }
        } else {
            mainWindow.setWarningLabel("Cannot remove from here, this cell is empty. Select an occupied cell");
        }
    }

    private void handleMove(Vect coords, Board board){
        if(!board.isCellEmpty(coords)){
            gizmoCoords = coords;
            try {
                mainWindow.setStatusLabel("Selected " + board.getGizmoName(board.getGizmo(gizmoCoords)) +
                        " at " + gizmoCoords + ". Please click a grid cell to move it to");
            } catch (NoSuchElementException e){
                mainWindow.setStatusLabel("Selected Ball at " + gizmoCoords);
            }
        } else if(board.isCellEmpty(coords) && gizmoCoords != null){
            try {
                board.moveGizmo(gizmoCoords, coords);
            } catch (NoSuchElementException e) {
                board.moveBall(gizmoCoords, coords);
            }
            gizmoCoords = null;
        } else {
            mainWindow.setWarningLabel("Cannot move from here, this cell is empty. Select an occupied cell.");
        }
    }

    private void handleRotate(Vect coords, Board board){
        if(!board.isCellEmpty(coords)){
            try {
                IGizmo gizmo = board.getGizmo(coords);
                gizmo.rotate(1);
                mainWindow.setStatusLabel("" + board.getGizmoName(gizmo) + " Rotated");
            } catch (NoSuchElementException e){
                mainWindow.setWarningLabel("Cannot rotate a ball. Select a gizmo.");
            }
        } else {
            mainWindow.setWarningLabel("Cannot rotate, this cell is empty. Select an occupied cell.");
        }
    }

    private void handleGizmoConnect(Vect coords, Board board){
        if(!board.isCellEmpty(coords)){
            if(gizmoCoords == null) {
                gizmoCoords = coords;
                try {
                    mainWindow.setStatusLabel("Selected " + board.getGizmoName(board.getGizmo(gizmoCoords)) +
                            " at " + gizmoCoords + ". Please type a key to connect this gizmo to");
                } catch (NoSuchElementException e) {
                    mainWindow.setStatusLabel("Selected ball at " + gizmoCoords + ". Please type a key to connect this ball to");
                }
            } else {
                board.getGizmo(gizmoCoords).addGizmoToTrigger(board.getGizmo(coords));
                mainWindow.setStatusLabel("Connected " + board.getGizmoName(board.getGizmo(gizmoCoords)) +
                        " to " + board.getGizmoName(board.getGizmo(coords)));
                gizmoCoords = null;
            }
        } else {
            mainWindow.setWarningLabel("Cannot add gizmo connection, this cell is empty. Select an occupied cell.");
        }
    }

    private void handleKeyConnect(Vect coords, Board board){
        if(!board.isCellEmpty(coords)) {
            gizmoCoords = coords;
            try {
                mainWindow.setStatusLabel("Selected " + board.getGizmoName(board.getGizmo(gizmoCoords)) +
                        " at " + gizmoCoords + ". Please type a key to connect this gizmo to");
            } catch (NoSuchElementException e){
                mainWindow.setStatusLabel("Selected ball at " + gizmoCoords + ". Please type a key to connect this ball to");
            }
        } else {
            mainWindow.setWarningLabel("Cannot add key connection, this cell is empty. Select an occupied cell.");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Vect coords = new Vect(e.getX() / GRID_WIDTH, e.getY() / GRID_WIDTH);
        Board board = mainWindow.getBoard();

        switch (state){
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
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
