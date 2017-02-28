package controller;

import view.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.CUR_GIZMO.RFLIPPER;

public class AddRFlipperListener implements ActionListener {
    private Board board;

    public AddRFlipperListener(Board board) {
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.getMouseListener().setGizmo(RFLIPPER);
    }
}
