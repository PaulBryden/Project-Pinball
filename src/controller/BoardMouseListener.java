package controller;

import model.*;
import physics.Vect;
import view.*;

import java.awt.event.MouseEvent;

//                                if(x < initalAbsorberCoords.x() && y < initalAbsorberCoords.y()) { //TOP-LEFT
//                                    board.addGizmo(new AbsorberView(new Absorber("A", new Vect(x, y), new Vect(initalAbsorberCoords.x() + 1, initalAbsorberCoords.y() + 1), board.getModel().getBalls())));
//                                    System.out.println("FIRST\n==================");
//                                } else if(x < initalAbsorberCoords.x() && y > initalAbsorberCoords.y()){ //BOTTOM-LEFT
//                                    board.addGizmo(new AbsorberView(new Absorber("A", new Vect(x, y + 1), new Vect(initalAbsorberCoords.x() + 1, initalAbsorberCoords.y()), board.getModel().getBalls())));
//                                    System.out.println("SECOND\n===============");
//                                } else if(x > initalAbsorberCoords.x() && y < initalAbsorberCoords.y()){ //TOP-RIGHT
//                                    board.addGizmo(new AbsorberView(new Absorber("A", new Vect(initalAbsorberCoords.x(), initalAbsorberCoords.y() + 1), new Vect(x + 1, y), board.getModel().getBalls())));
//                                    System.out.println("THIRD\n===============");
//                                } else if(x < initalAbsorberCoords.x()) { // LEFT
//                                    board.addGizmo(new AbsorberView(new Absorber("A", new Vect(x, y), new Vect(initalAbsorberCoords.x() + 1, initalAbsorberCoords.y() + 1), board.getModel().getBalls())));
//                                    System.out.println("FOURTH\n===============");
//                                } else if(y < initalAbsorberCoords.y()) { //UP
//                                    board.addGizmo(new AbsorberView(new Absorber("A", new Vect(x, y), new Vect(initalAbsorberCoords.x() + 1, initalAbsorberCoords.y() + 1), board.getModel().getBalls())));
//                                    System.out.println("FIFTH\n===============");
//                                } else { //DOWN, RIGHT, BOTTOM-RIGHT, NEUTRAL,
//                                    board.addGizmo(new AbsorberView(new Absorber("A", initalAbsorberCoords,  new Vect(x + 1, y + 1), board.getModel().getBalls())));
//                                    System.out.println("SIXTH\n===============");
//                                }

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
        state = STATE.BUILD;
        gizmo = CUR_GIZMO.NONE;
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
        int x = e.getX() / GRID_WIDTH;
        int y = e.getY() / GRID_WIDTH;
        String id = x + "" + y;

        switch (state){
            case ADD:
                if(board.isCellEmpty(x, y)) {
                    switch (gizmo) {
                        case ABSORBER:
                            if(initalAbsorberCoords == null){
                                initalAbsorberCoords = new Vect(x, y);
                            } else { //simplify some more
                                if((x < initalAbsorberCoords.x() && y <= initalAbsorberCoords.y()) || (y < initalAbsorberCoords.y() && x <= initalAbsorberCoords.x())) { //LEFT, UP, TOP-LEFT
                                    board.addGizmo(new AbsorberView(new Absorber("A", new Vect(x, y), new Vect(initalAbsorberCoords.x() + 1, initalAbsorberCoords.y() + 1), board.getModel().getBalls())));
                                } else if(x < initalAbsorberCoords.x() && y > initalAbsorberCoords.y()){ //BOTTOM-LEFT
                                    board.addGizmo(new AbsorberView(new Absorber("A", new Vect(x, y + 1), new Vect(initalAbsorberCoords.x() + 1, initalAbsorberCoords.y()), board.getModel().getBalls())));
                                } else if(x > initalAbsorberCoords.x() && y < initalAbsorberCoords.y()){ //TOP-RIGHT
                                    board.addGizmo(new AbsorberView(new Absorber("A", new Vect(initalAbsorberCoords.x(), initalAbsorberCoords.y() + 1), new Vect(x + 1, y), board.getModel().getBalls())));
                                } else { //DOWN, RIGHT, BOTTOM-RIGHT, NEUTRAL
                                    board.addGizmo(new AbsorberView(new Absorber("A", initalAbsorberCoords,  new Vect(x + 1, y + 1), board.getModel().getBalls())));
                                }
                                initalAbsorberCoords = null;
                            }
                            break;
                        case BALL:
                            board.addBall(new BallGizmo("B", x + 0.5, y + 0.5, 13, 17));
                            break;
                        case CIRCLE:
                            board.addGizmo(new CircleView(new CircleGizmo("C" + id, x, y)));
                            break;
                        case LFLIPPER: //TODO: Add user defined key connection
                            IFlipper lFlipper = new LeftFlipper("LF" + id, x, y);
                            board.addGizmo(new FlipperView(lFlipper));
                            board.getModel().addKeyPressedTrigger(66, lFlipper);
                            board.getModel().addKeyReleasedTrigger(66, lFlipper);
                            break;
                        case RFLIPPER:
                            IFlipper rFlipper = new RightFlipper("RF" + id, x, y);
                            board.addGizmo(new FlipperView(rFlipper));
                            board.getModel().addKeyPressedTrigger(66, rFlipper);
                            board.getModel().addKeyReleasedTrigger(66, rFlipper);
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
