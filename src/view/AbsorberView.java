package view;

import java.awt.Graphics;
import java.util.Observable;

import model.IGizmo;
import observer.IObservable;
import observer.IObserver;

public class AbsorberView implements IViewGizmo, IObserver{
    private IGizmo gizmo;
    private static final int GRID_WIDTH = 20;

    public AbsorberView(IGizmo gizmo){
        this.gizmo = gizmo;
    }

    @Override
    public void update(Observable o, Object arg) {
        //UPDATE GRAPHICS OBJECT

    }

    public void paint(Graphics g) {
        g.setColor(gizmo.getColour());
        g.fillRect((int) gizmo.getExactCoords().get(0).x() * GRID_WIDTH,
                (int) gizmo.getExactCoords().get(0).y() * GRID_WIDTH,
                (int)(gizmo.getExactCoords().get(2).x() - gizmo.getExactCoords().get(0).x()) * GRID_WIDTH,
                (int) (gizmo.getExactCoords().get(2).y() - gizmo.getExactCoords().get(0).y()) * GRID_WIDTH);
    }

    @Override
    public void update(IObservable obsv, Object o) {
        // TODO Auto-generated method stub
    }

}
