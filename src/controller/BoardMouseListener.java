package controller;

import model.BallGizmo;
import model.IBall;
import model.IGizmo;
import model.SquareGizmo;
import view.BallView;
import view.Board;
import view.SquareView;

import java.awt.event.MouseEvent;

public class BoardMouseListener implements java.awt.event.MouseListener{
    public enum STATE {
        IDLE, ADD, REMOVE
    }
    public enum CUR_GIZMO {
        BALL, SQUARE, TRIANGLE, FLIPPER, CIRCLE, ABSORBER, NONE
    }
    private static final int GRID_WIDTH = 20;
    private STATE state;
    private CUR_GIZMO gizmo;
    private Board board;

    public BoardMouseListener(Board board){
        this.board = board;
        state = STATE.IDLE;
        gizmo = CUR_GIZMO.NONE;
    }

    void setState(STATE state){
        this.state = state;
    }

    void setGizmo(CUR_GIZMO gizmo){
        this.gizmo = gizmo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (state){
            case ADD:
                int x = e.getX() / GRID_WIDTH;
                int y = e.getY() / GRID_WIDTH;

                switch (gizmo){
                    case BALL:
                        IBall ballGizmo = new BallGizmo("B", x, y, 13, 17);
                        board.addViewBall(new BallView(ballGizmo));
                        board.getModel().addBall(ballGizmo);
                        break;
                    case SQUARE:
                        IGizmo squareGizmo = new SquareGizmo("S" + x + "" + y, x, y);
                        board.addViewGizmo(new SquareView(squareGizmo));
                        board.getModel().addGizmo(squareGizmo);
                        break;
                }
                break;
        }
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
