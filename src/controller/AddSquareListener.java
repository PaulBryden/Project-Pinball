package controller;

import model.IGizmo;
import model.SquareGizmo;
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
        IGizmo squareGizmo = new SquareGizmo("S1618", 16, 18);
        board.addViewGizmo(new SquareView(squareGizmo));
        board.getModel().addGizmo(squareGizmo);
    }
}
