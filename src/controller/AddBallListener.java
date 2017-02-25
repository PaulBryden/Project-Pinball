package controller;

import view.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.CUR_GIZMO.BALL;

public class AddBallListener implements ActionListener{
    private Board board;

    public AddBallListener(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.getMouseListener().setGizmo(BALL);
    }
}
