package controller;

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
        board.addViewGizmo(new AbsorberView(null, board));
    }
}
