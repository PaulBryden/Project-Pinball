package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import model.IGizmo;
import model.IAbsorber;
public class AbsorberView implements IViewGizmo {
    private IAbsorber gizmo;

    public AbsorberView(IGizmo gizmo){
        this.gizmo = (IAbsorber)gizmo;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(gizmo.getColour());
        g.fillRect((int) gizmo.getExactCoords().get(0).x() * Board.GRID_WIDTH,
                (int) gizmo.getExactCoords().get(0).y() * Board.GRID_WIDTH,
                (int)(gizmo.getExactCoords().get(2).x() - gizmo.getExactCoords().get(0).x()) * Board.GRID_WIDTH,
                (int) (gizmo.getExactCoords().get(2).y() - gizmo.getExactCoords().get(0).y()) * Board.GRID_WIDTH);

    	if(gizmo.getNextBall() != null){
    		Graphics2D g2D = (Graphics2D) g;
    		double radius = gizmo.getNextBall().getRadius();
    		int width = (int) (2 * radius * Board.GRID_WIDTH);

    		g2D.setColor(gizmo.getNextBall().getColour());
    		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    		g2D.fillOval((int) ((gizmo.getExactCoords().get(2).x()-0.3 - radius) * Board.GRID_WIDTH),
    				(int) ((gizmo.getExactCoords().get(2).y()-0.3 - radius) * Board.GRID_WIDTH), width, width);

    	}
    }

    @Override
    public IGizmo getGizmo() {
        return (gizmo);
    }

}
