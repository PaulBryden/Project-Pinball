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
        Absorber absorber = new Absorber(34, 0, 19, null);
        board.addViewGizmo(new AbsorberView(absorber, board));
        board.getModel().addGizmo(absorber);
    }
}
