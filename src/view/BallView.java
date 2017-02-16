package view;

import java.awt.*;
import java.util.Observable;

import model.IBall;
import model.IGizmo;
import observer.IObservable;
import observer.IObserver;

public class BallView implements IViewGizmo, IObserver{
    protected IBall gizmo;
    private static final int GRID_WIDTH = 20;

    public BallView(IBall gizmo){
        this.gizmo = gizmo;
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
        Graphics2D g2D = (Graphics2D) g;
        double radius = gizmo.getRadius();
        int width = (int) (2 * radius * GRID_WIDTH);

        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2D.fillOval((int) ((gizmo.getCentre().x() - radius) * GRID_WIDTH),
                (int) ((gizmo.getCentre().y() - radius) * GRID_WIDTH), width, width);
    }

    @Override
    public void setGizmo(IGizmo gizmo) {
        this.gizmo = (IBall) gizmo;
    }

    @Override
    public void update(IObservable obsv, Object o) {
    }

}
