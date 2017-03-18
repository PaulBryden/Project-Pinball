package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.List;

import model.IGizmo;
import physics.Vect;

public class TriangleView implements IViewGizmo {

	private IGizmo gizmo;
	private static final int GRID_WIDTH = 20;

	public TriangleView(IGizmo gizmo){
		this.gizmo = gizmo;
	}

	@Override
	public void paint(Graphics g){
		Graphics2D g2D = (Graphics2D) g;
		List<Vect> exactCoords = gizmo.getExactCoords();
		int[] x = new int[exactCoords.size()];
		int[] y = new int[exactCoords.size()];

		for (int i = 0; i < exactCoords.size(); i++) {
			x[i] = (int) (GRID_WIDTH * exactCoords.get(i).x());
			y[i] = (int) (GRID_WIDTH * exactCoords.get(i).y());
		}

		g2D.setColor(gizmo.getColour());
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.fillPolygon(x, y, exactCoords.size());
	}

	@Override
	public IGizmo getGizmo() {
		return (gizmo);
	}

}
