package controller;

import view.Board;
import view.SquareView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSquareListener implements ActionListener{
    private Board board;

    public AddSquareListener(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.addViewGizmo(new SquareView(null));
    }
}
