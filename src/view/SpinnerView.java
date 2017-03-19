package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;
import java.util.List;

import model.IFlipper;
import model.IGizmo;
import model.ISpinner;
import model.Spinner;
import physics.LineSegment;
import physics.Vect;

public class SpinnerView implements IViewGizmo {
	private ISpinner gizmo;
	private static final int GRID_WIDTH = 20;

	public SpinnerView(ISpinner iSpinner){
		this.gizmo = iSpinner;
	}

	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		List<Vect> exactCoords = new LinkedList<>();
		List<LineSegment> lines = gizmo.getAllLineSegments();
		int diameter = (int) (GRID_WIDTH * gizmo.getWidth());
		int x = (int) (GRID_WIDTH * gizmo.getEndCentre1().x() - diameter / 2);
		int y = (int) (GRID_WIDTH * gizmo.getEndCentre1().y() - diameter / 2);
		int[] a;
		int[] b;

		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setColor(gizmo.getColour());
		g2D.fillOval(x, y, diameter, diameter);

		x = (int) (GRID_WIDTH * gizmo.getEndCentre2().x() - diameter / 2);
		y = (int) (GRID_WIDTH * gizmo.getEndCentre2().y() - diameter / 2);

		g2D.fillOval(x, y, diameter, diameter);

		exactCoords.add(lines.get(0).p1());
		exactCoords.add(lines.get(0).p2());
		exactCoords.add(lines.get(1).p2());
		exactCoords.add(lines.get(1).p1());

		a = new int[exactCoords.size()];
		b = new int[exactCoords.size()];

		for (int i = 0; i < exactCoords.size(); i++) {
			a[i] = (int) (GRID_WIDTH * exactCoords.get(i).x());
			b[i] = (int) (GRID_WIDTH * exactCoords.get(i).y());
		}
		
		g2D.fillPolygon(a, b, exactCoords.size());
	}

	@Override
	public IGizmo getGizmo() {
		return (gizmo);
	}

}
