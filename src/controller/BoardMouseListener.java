package controller;

import model.BallGizmo;
import model.CircleGizmo;
import model.IBall;
import model.ICircle;
import model.IFlipper;
import model.IGizmo;
import model.LeftFlipper;
import model.SquareGizmo;
import model.TriangleGizmo;
import physics.Vect;
import view.BallView;
import view.Board;
import view.CircleView;
import view.FlipperView;
import view.SquareView;
import view.TriangleView;

import java.awt.event.MouseEvent;
import java.util.Iterator;

public class BoardMouseListener implements java.awt.event.MouseListener{
    public enum STATE {
        BUILD, RUN, ADD, REMOVE
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
        state = STATE.BUILD;
        gizmo = CUR_GIZMO.NONE;
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

    private boolean isCellEmpty(int x, int y){
        for(IGizmo gizmo : board.getModel().getGizmos()){
            if(gizmo.getGridCoords().equals(new Vect(x, y))){
                return (false);
            }
        }

        for(IBall ball : board.getModel().getBalls()){
            if(ball.getGridCoords().equals(new Vect(x + 0.5, y + 0.5))){
                return (false);
            }
        }

        return (true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX() / GRID_WIDTH;
        int y = e.getY() / GRID_WIDTH;
        String id = x + "" + y;

        switch (state){
            case ADD:
                if(isCellEmpty(x, y)) {
                    switch (gizmo) {
                        case BALL:
                            IBall ballGizmo = new BallGizmo("B", x + 0.5, y + 0.5, 13, 17);
                            board.addViewBall(new BallView(ballGizmo));
                            board.getModel().addBall(ballGizmo);
                            break;
                        case CIRCLE:
                            ICircle circleGizmo = new CircleGizmo("C" + id, x, y);
                            board.addViewGizmo(new CircleView(circleGizmo));
                            board.getModel().addGizmo(circleGizmo);
                            break;
                        case FLIPPER: //TODO: Add user defined key connection
                            IFlipper flipper = new LeftFlipper("LF" + id, x, y);
                            board.addViewGizmo(new FlipperView(flipper));
                            board.getModel().addGizmo(flipper);
                            board.getModel().addKeyPressedTrigger(66, flipper);
                            board.getModel().addKeyReleasedTrigger(66, flipper);
                            break;
                        case SQUARE:
                            IGizmo squareGizmo = new SquareGizmo("S" + id, x, y);
                            board.addViewGizmo(new SquareView(squareGizmo));
                            board.getModel().addGizmo(squareGizmo);
                            break;
                        case TRIANGLE:
                            IGizmo triangleGizmo = new TriangleGizmo("T" + id, x, y);
                            board.addViewGizmo(new TriangleView(triangleGizmo));
                            board.getModel().addGizmo(triangleGizmo);
                            break;
                    }
                }
                break;
            case REMOVE:
                if(!isCellEmpty(x, y)) {
                    boolean removed = false;

                    for (Iterator<IGizmo> iterator = board.getModel().getGizmos().iterator(); iterator.hasNext(); ) {
                        if (iterator.next().getGridCoords().equals(new Vect(x, y))) {
                            iterator.remove();
                            removed = true;
                            break;
                        }
                    }

                    if (!removed) {
                        board.getModel().getBalls().removeIf(
                                ball -> ball.getGridCoords().equals(new Vect(x + 0.5, y + 0.5)));
                    }

                    board.reRender();
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
