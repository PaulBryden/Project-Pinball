package controller;

import model.BallGizmo;
import model.IBall;
import view.BallView;
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
        //TODO: Get proper velocity vector
        IBall ballGizmo = new BallGizmo("B", 16, 17, 1, 1);
        board.addViewBall(new BallView(ballGizmo));
        board.getModel().addBall(ballGizmo);
    }
}
