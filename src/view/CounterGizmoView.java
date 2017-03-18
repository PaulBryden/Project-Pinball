package view;

import java.awt.Color;
import java.awt.Graphics;

import model.ICounterGizmo;
import model.IGizmo;

public class CounterGizmoView implements IViewGizmo {
	
    private ICounterGizmo gizmo;
    private Color textColour;

    public CounterGizmoView(IGizmo gizmo, Color textColour){
        this.gizmo = (ICounterGizmo) gizmo;
        this.textColour = textColour;
    }

    public void paint(Graphics g) {
        g.setColor(gizmo.getColour());
        int x = (int) gizmo.getExactCoords().get(0).x() * Board.GRID_WIDTH;
        int y = (int) gizmo.getExactCoords().get(0).y() * Board.GRID_WIDTH;
        g.fillRect(x,y,
                (int)(gizmo.getExactCoords().get(2).x() - gizmo.getExactCoords().get(0).x()) * Board.GRID_WIDTH,
                (int) (gizmo.getExactCoords().get(2).y() - gizmo.getExactCoords().get(0).y()) * Board.GRID_WIDTH);
        g.setColor(textColour);
        g.drawString(Integer.toString(gizmo.getCount()), x+5, y+20);
    }

    @Override
    public IGizmo getGizmo() {
        return (gizmo);
    }

}
