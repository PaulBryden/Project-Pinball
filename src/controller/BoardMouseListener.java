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
import view.AbsorberView;
import view.Board;
import view.CircleView;
import view.FlipperView;
import view.SquareView;
import view.TriangleView;

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
    private Board board;
    private Vect gizmoCoords;
    private Vect initalAbsorberCoords;

    public BoardMouseListener(Board board){
        this.board = board;
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

        switch (state){
            case ADD:
                if(board.isCellEmpty(coords)) {
                    switch (gizmo) {
                        case ABSORBER:
                            if(initalAbsorberCoords == null){
                                initalAbsorberCoords = coords;
                            } else {
                                if(coords.x() < initalAbsorberCoords.x() || coords.y() < initalAbsorberCoords.y()) {
                                    System.err.println("FIRST CLICK MUST BE TOP LEFT GRID SQUARE");
                                } else {
                                    board.addGizmo(new AbsorberView(new Absorber("A", initalAbsorberCoords, coords.plus(new Vect(1, 1)), board.getModel().getBalls())));
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
                        case LFLIPPER: //TODO: Add user defined key connection
                            IFlipper lFlipper = new LeftFlipper("LF" + id, coords);
                            board.addGizmo(new FlipperView(lFlipper));
                            board.getModel().addKeyPressedTrigger(66, lFlipper);
                            board.getModel().addKeyReleasedTrigger(66, lFlipper);
                            break;
                        case RFLIPPER:
                            IFlipper rFlipper = new RightFlipper("RF" + id, coords);
                            board.addGizmo(new FlipperView(rFlipper));
                            board.getModel().addKeyPressedTrigger(66, rFlipper);
                            board.getModel().addKeyReleasedTrigger(66, rFlipper);
                            break;
                        case SQUARE:
                            board.addGizmo(new SquareView(new SquareGizmo("S" + id, coords)));
                            break;
                        case TRIANGLE:
                            board.addGizmo(new TriangleView(new TriangleGizmo("T" + id, coords)));
                            break;
                    }
                }
                break;
            case REMOVE:
                if(!board.isCellEmpty(coords)){
                    try {
                        board.removeGizmo(coords);
                    } catch (NoSuchElementException E) {
                        board.removeBall(coords);
                    }
                }
                break;
            case MOVE:
                if(!board.isCellEmpty(coords)){
                    gizmoCoords = coords;
                } else if(board.isCellEmpty(coords) && gizmoCoords != null){
                    try {
                        board.moveGizmo(gizmoCoords, coords);
                    } catch (NoSuchElementException E) {
                        board.moveBall(gizmoCoords, coords);
                    }
                    gizmoCoords = null;
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
