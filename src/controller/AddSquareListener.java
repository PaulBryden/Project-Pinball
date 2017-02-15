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

    //TODO: Implement ID generation system and get coords for inital render from mouse click
    @Override
    public void actionPerformed(ActionEvent e) {
        IGizmo squareGizmo = new SquareGizmo("S00", 0, 0);
        board.addViewGizmo(new SquareView(squareGizmo));
        board.getModel().addGizmo(squareGizmo);
    }
}
