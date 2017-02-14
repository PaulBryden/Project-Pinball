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
        IFlipper flipper = new LeftFlipper(78, 16, 17);
        board.addViewGizmo(new FlipperView(flipper));
        board.getModel().addGizmo(flipper);
    }
}
