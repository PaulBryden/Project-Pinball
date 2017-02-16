package controller;

import model.IFlipper;
import model.LeftFlipper;
import view.Board;
import view.FlipperView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddFlipperListener implements ActionListener{
    private Board board;

    public AddFlipperListener(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IFlipper flipper = new LeftFlipper("LF1617", 10, 15);
        board.addViewGizmo(new FlipperView(flipper));
        board.getModel().addGizmo(flipper);
        board.getModel().addKeyTrigger('b', flipper);
    }
}
