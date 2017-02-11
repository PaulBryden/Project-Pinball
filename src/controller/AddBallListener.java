package controller;

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
        board.addViewGizmo(new BallView(null));
    }
}
