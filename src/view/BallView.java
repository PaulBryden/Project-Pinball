package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
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
        double radius = gizmo.getRadius();
        int width = (int) (2 * radius * GRID_WIDTH);

        g.fillOval((int) ((gizmo.getCentre().x() - radius) * GRID_WIDTH),
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
