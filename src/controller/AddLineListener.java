package controller;

import view.Board;
import view.LineView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddLineListener implements ActionListener{
    private Board board;

    public AddLineListener(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.addViewGizmo(new LineView(null));
    }
}
