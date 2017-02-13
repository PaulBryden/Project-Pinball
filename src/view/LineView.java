package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

import model.IGizmo;
import observer.IObservable;
import observer.IObserver;

public class LineView implements IViewGizmo, IObserver {

    private IGizmo gizmo;

    public LineView(IGizmo gizmo){
        this.gizmo = gizmo;
    }

    public void paint(Graphics g){
        g.drawLine(250, 49, 350, 49);
        g.setColor(Color.BLUE);
    }

    @Override
    public Graphics GetViewObject() {
        return null;
    }

    @Override
    public void update(IObservable obsv, Object o) {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
