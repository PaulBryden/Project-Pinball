package controller;

import model.Absorber;
import model.BallGizmo;
import model.CircleGizmo;
import model.IFlipper;
import model.LeftFlipper;
import model.RightFlipper;
import model.SquareGizmo;
import model.TriangleGizmo;
import physics.Vect;
import view.*;

import java.awt.event.MouseEvent;
import java.util.NoSuchElementException;

import static controller.BoardMouseListener.CUR_GIZMO.NONE;
import static controller.BoardMouseListener.STATE.BUILD;

public class BoardMouseListener implements java.awt.event.MouseListener{
    public enum STATE {
        BUILD, RUN, ADD, REMOVE, MOVE
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
        gizmoCoords = null;
        initalAbsorberCoords = null;
    }

    void setState(STATE state){
        this.state = state;
    }

    void setGizmo(CUR_GIZMO gizmo){
        this.gizmo = gizmo;
    }

    public STATE getState(){
        return (state);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Vect coords = new Vect(e.getX() / GRID_WIDTH, e.getY() / GRID_WIDTH);
        String id = coords.x() + "" + coords.y();
        Board board = mainWindow.getBoard();

        switch (state){
            case ADD:
                if(board.isCellEmpty(coords)) {
                    switch (gizmo) {
                        case ABSORBER:
                            if(initalAbsorberCoords == null){
                                initalAbsorberCoords = coords;
                                mainWindow.setStatusLabel("Selected top-left cell of absorber at " + coords);
                            } else {
                                if(coords.x() < initalAbsorberCoords.x() || coords.y() < initalAbsorberCoords.y()) {
                                    mainWindow.setWarningLabel("Invalid cell, you might want to make that the top-left cell, try again");
                                } else {
                                    board.addGizmo(new AbsorberView(new Absorber("A", initalAbsorberCoords, coords.plus(new Vect(1, 1)), board.getModel().getBalls())));
                                    mainWindow.setStatusLabel("Placed Absorber");
                                }
                                initalAbsorberCoords = null;
                            }
                            break;
                        case BALL:
                            board.addBall(new BallGizmo("B", coords.plus(new Vect(0.5, 0.5)), new Vect(13, 17)));
                            mainWindow.setStatusLabel("Placed Ball");
                            break;
                        case CIRCLE:
                            board.addGizmo(new CircleView(new CircleGizmo("C" + id, coords)));
                            mainWindow.setStatusLabel("Placed Circle");
                            break;
                        case LFLIPPER: //TODO: Add user defined key connection
                            IFlipper lFlipper = new LeftFlipper("LF" + id, coords);
                            board.addGizmo(new FlipperView(lFlipper));
                            board.getModel().addKeyPressedTrigger(66, lFlipper);
                            board.getModel().addKeyReleasedTrigger(66, lFlipper);
                            mainWindow.setStatusLabel("Placed Left-Flipper");
                            break;
                        case RFLIPPER:
                            IFlipper rFlipper = new RightFlipper("RF" + id, coords);
                            board.addGizmo(new FlipperView(rFlipper));
                            board.getModel().addKeyPressedTrigger(66, rFlipper);
                            board.getModel().addKeyReleasedTrigger(66, rFlipper);
                            mainWindow.setStatusLabel("Placed Right-Flipper");
                            break;
                        case SQUARE:
                            board.addGizmo(new SquareView(new SquareGizmo("S" + id, coords)));
                            mainWindow.setStatusLabel("Placed Square");
                            break;
                        case TRIANGLE:
                            board.addGizmo(new TriangleView(new TriangleGizmo("T" + id, coords)));
                            mainWindow.setStatusLabel("Placed Triangle");
                            break;
                    }
                } else {
                    mainWindow.setWarningLabel("Cannot place here, this cell is already occupied. Select an empty cell or remove what is in this cell");
                }
                break;
            case REMOVE:
                if(!board.isCellEmpty(coords)){
                    try {
                        board.removeGizmo(coords);
                        mainWindow.setStatusLabel("Removed gizmo");
                    } catch (NoSuchElementException E) {
                        board.removeBall(coords);
                        mainWindow.setStatusLabel("Removed ball");
                    }
                } else {
                    mainWindow.setWarningLabel("Cannot remove from here, this cell is empty. Select an occupied cell");
                }
                break;
            case MOVE:
                if(!board.isCellEmpty(coords)){
                    gizmoCoords = coords;
                    mainWindow.setStatusLabel("Selected gizmo at " + coords);
                } else if(board.isCellEmpty(coords) && gizmoCoords != null){
                    try {
                        board.moveGizmo(gizmoCoords, coords);
                        mainWindow.setStatusLabel("Moved gizmo from " + gizmoCoords + " to " + coords);
                    } catch (NoSuchElementException E) {
                        board.moveBall(gizmoCoords, coords);
                        mainWindow.setStatusLabel("Moved ball from " + gizmoCoords + " to " + coords);
                    }
                    gizmoCoords = null;
                } else {
                    mainWindow.setWarningLabel("Cannot move from here, this cell is empty. Select an occupied cell.");
                }
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
