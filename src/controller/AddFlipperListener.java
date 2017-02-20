package controller;

import view.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFlipperListener implements ActionListener {
	private Board board;

	public AddFlipperListener(Board board) {
		this.board = board;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		board.getMouseListener().setGizmo(BoardMouseListener.CUR_GIZMO.FLIPPER);
//		IFlipper flipper = new LeftFlipper("LF1617", 17, 16);
//		board.addViewGizmo(new FlipperView(flipper));
//		board.getModel().addGizmo(flipper);
//		board.getModel().addKeyPressedTrigger(66, flipper);
//		board.getModel().addKeyReleasedTrigger(66, flipper);
	}
}
