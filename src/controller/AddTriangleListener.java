package controller;

import view.Board;
import view.TriangleView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTriangleListener implements ActionListener{
    private Board board;

    public AddTriangleListener(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.addViewGizmo(new TriangleView(null));
    }
}
