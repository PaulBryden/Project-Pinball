package view;

import java.awt.*;
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
        Graphics2D g2D = (Graphics2D) g;

        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.drawLine(250, 49, 350, 49);
        g2D.setColor(Color.BLUE);
    }

    @Override
    public void setGizmo(IGizmo gizmo) {

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
