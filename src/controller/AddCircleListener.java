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
        ICircle circleGizmo;
        for(int x = 0; x < 20; x ++){
            for(int y = 0; y < 20; y++){
                circleGizmo = new CircleGizmo(11, x, y);
                board.addViewGizmo(new CircleView(circleGizmo));
                board.getModel().addGizmo(circleGizmo);
            }
        }
    }
}
