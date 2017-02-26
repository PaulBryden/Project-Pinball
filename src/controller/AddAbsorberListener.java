package controller;

import model.Absorber;
import view.AbsorberView;
import view.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAbsorberListener implements ActionListener{
    private Board board;

    public AddAbsorberListener(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.addGizmo(new AbsorberView(new Absorber("A", 0, 19, 1, 20, board.getModel().getBalls())));
    }
}
