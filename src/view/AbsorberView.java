package view;

import java.awt.Graphics;

import model.IGizmo;

public class AbsorberView implements IViewGizmo {
    private IGizmo gizmo;
    private static final int GRID_WIDTH = 20;

    public AbsorberView(IGizmo gizmo){
        this.gizmo = gizmo;
    }

    public void paint(Graphics g) {
        g.setColor(gizmo.getColour());
        g.fillRect((int) gizmo.getExactCoords().get(0).x() * GRID_WIDTH,
                (int) gizmo.getExactCoords().get(0).y() * GRID_WIDTH,
                (int)(gizmo.getExactCoords().get(2).x() - gizmo.getExactCoords().get(0).x()) * GRID_WIDTH,
                (int) (gizmo.getExactCoords().get(2).y() - gizmo.getExactCoords().get(0).y()) * GRID_WIDTH);
    }

    @Override
    public IGizmo getGizmo() {
        return (gizmo);
    }

}
