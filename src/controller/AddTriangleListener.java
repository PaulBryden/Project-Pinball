package controller;

import model.IGizmo;
import model.TriangleGizmo;
import view.Board;
import view.TriangleView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddTriangleListener implements ActionListener{
    private Board board;

    public AddTriangleListener(Board board){
        this.board = board;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IGizmo triangleGizmo = new TriangleGizmo(79, 50 ,50);
        board.addViewGizmo(new TriangleView(triangleGizmo));
        board.getModel().addGizmo(triangleGizmo);
    }
}
