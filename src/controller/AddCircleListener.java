package controller;

import view.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCircleListener implements ActionListener{
    private Board board;

    public AddCircleListener(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.getMouseListener().setGizmo(BoardMouseListener.CUR_GIZMO.CIRCLE);
//        ICircle circleGizmo = new CircleGizmo("C910", 9, 10);
//        board.addViewGizmo(new CircleView(circleGizmo));
//        board.getModel().addGizmo(circleGizmo);
    }
}
