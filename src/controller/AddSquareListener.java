package controller;

import view.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddSquareListener implements ActionListener{
    private Board board;

    public AddSquareListener(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.getMouseListener().setGizmo(BoardMouseListener.CUR_GIZMO.SQUARE);
//        IGizmo squareGizmo = new SquareGizmo("S1618", 16, 18);
//        board.addViewGizmo(new SquareView(squareGizmo));
//        board.getModel().addGizmo(squareGizmo);
    }
}
