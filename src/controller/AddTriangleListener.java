package controller;

import view.Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTriangleListener implements ActionListener{
    private Board board;

    public AddTriangleListener(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        board.getMouseListener().setGizmo(BoardMouseListener.CUR_GIZMO.TRIANGLE);
//        IGizmo triangleGizmo = new TriangleGizmo("T014", 16 ,18);
//        board.addViewGizmo(new TriangleView(triangleGizmo));
//        board.getModel().addGizmo(triangleGizmo);
    }
}