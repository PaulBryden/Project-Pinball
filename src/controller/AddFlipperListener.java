package controller;

import view.Board;
import view.FlipperView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFlipperListener implements ActionListener{
    private Board board;

    public AddFlipperListener(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.addViewGizmo(new FlipperView(null));
    }
}
