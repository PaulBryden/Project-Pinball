package view;

import java.awt.*;
import java.util.Observable;

import model.ICircle;
import model.IGizmo;
import observer.IObservable;
import observer.IObserver;

public class AbsorberView implements IViewGizmo, IObserver{
    private ICircle gizmo;
    private Board board;

    public AbsorberView(ICircle gizmo, Board board){
        this.gizmo = gizmo;
        this.board = board;
    }

    @Override
    public void update(Observable o, Object arg) {
        //UPDATE GRAPHICS OBJECT

    }

    @Override
    public Graphics GetViewObject() {
        // TODO Auto-generated method stub
        return null;
    }

    public void paint(Graphics g) {
        g.fillRect(0, board.getY() + 303, board.getWidth(), 30);
    }

    @Override
    public void setGizmo(IGizmo gizmo) {

    }

    @Override
    public void update(IObservable obsv, Object o) {
        // TODO Auto-generated method stub
    }

}
