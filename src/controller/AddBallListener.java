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
        IBall ballGizmo = new BallGizmo(34, 16, 17, 1, 1);
        board.addViewGizmo(new BallView(ballGizmo));
        board.getModel().addGizmo(ballGizmo);
    }
}
