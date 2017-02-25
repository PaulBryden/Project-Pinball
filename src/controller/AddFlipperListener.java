package controller;

import view.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static controller.BoardMouseListener.CUR_GIZMO.FLIPPER;

public class AddFlipperListener implements ActionListener {
	private Board board;

	public AddFlipperListener(Board board) {
		this.board = board;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		board.getMouseListener().setGizmo(FLIPPER);
	}
}
