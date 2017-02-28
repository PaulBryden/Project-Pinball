package controller;

import view.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.CUR_GIZMO.ABSORBER;

public class AddAbsorberListener implements ActionListener{
    private Board board;

    public AddAbsorberListener(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.getMouseListener().setGizmo(ABSORBER);
    }
}
