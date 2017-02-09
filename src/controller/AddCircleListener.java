package controller;

import view.Board;
import view.CircleView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCircleListener implements ActionListener{
    private Board board;

    public AddCircleListener(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.addViewGizmo(new CircleView(null));
    }
}
