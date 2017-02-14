package controller;

import model.CircleGizmo;
import model.ICircle;
import view.Board;
import view.CircleView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCircleListener implements ActionListener{
    private Board board;

    public AddCircleListener(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ICircle circleGizmo = new CircleGizmo(11, 9, 10);
        board.addViewGizmo(new CircleView(circleGizmo));
        board.getModel().addGizmo(circleGizmo);
    }
}
