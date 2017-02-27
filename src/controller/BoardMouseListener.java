package controller;

import model.*;
import physics.Vect;
import view.Board;
import view.CircleView;
import view.FlipperView;
import view.SquareView;
import view.TriangleView;

import java.awt.event.MouseEvent;

public class BoardMouseListener implements java.awt.event.MouseListener{
    public enum STATE {
        BUILD, RUN, ADD, REMOVE, MOVE
    }
    public enum CUR_GIZMO {
        BALL, SQUARE, TRIANGLE, FLIPPER, CIRCLE, ABSORBER, NONE
    }
    private static final int GRID_WIDTH = 20;
    private STATE state;
    private CUR_GIZMO gizmo;
    private Board board;
    private Vect gizmoCoords;

    public BoardMouseListener(Board board){
        this.board = board;
        state = STATE.BUILD;
        gizmo = CUR_GIZMO.NONE;
        gizmoCoords = null;
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
        int x = e.getX() / GRID_WIDTH;
        int y = e.getY() / GRID_WIDTH;
        String id = x + "" + y;

        switch (state){
            case ADD:
                if(board.isCellEmpty(x, y)) {
                    switch (gizmo) {
                        case BALL:
                            board.addBall(new BallGizmo("B", x + 0.5, y + 0.5, 13, 17));
                            break;
                        case CIRCLE:
                            board.addGizmo(new CircleView(new CircleGizmo("C" + id, x, y)));
                            break;
                        case FLIPPER: //TODO: Add user defined key connection
                            IFlipper flipper = new LeftFlipper("LF" + id, x, y);
                            board.addGizmo(new FlipperView(flipper));
                            board.getModel().addKeyPressedTrigger(66, flipper);
                            board.getModel().addKeyReleasedTrigger(66, flipper);
                            break;
                        case SQUARE:
                            board.addGizmo(new SquareView(new SquareGizmo("S" + id, x, y)));
                            break;
                        case TRIANGLE:
                            board.addGizmo(new TriangleView(new TriangleGizmo("T" + id, x, y)));
                            break;
                    }
                }
                break;
            case REMOVE:
                if(!board.isCellEmpty(x, y)){
                    board.removeGizmo(x, y);
                }
                break;
            case MOVE:
                if(!board.isCellEmpty(x, y)){
                    gizmoCoords = new Vect(x, y);
                } else if(board.isCellEmpty(x, y) && gizmoCoords != null){
                    board.moveGizmo(gizmoCoords, new Vect(x, y));
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
