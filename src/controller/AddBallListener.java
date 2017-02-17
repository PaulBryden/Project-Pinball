package controller;

import view.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBallListener implements ActionListener{
    private Board board;

    public AddBallListener(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.getMouseListener().setGizmo(BoardMouseListener.CUR_GIZMO.BALL);
//        IBall ballGizmo = new BallGizmo("B", 18, 3, 1, 1);
//        board.addViewBall(new BallView(ballGizmo));
//        board.getModel().addBall(ballGizmo);
    }
}
