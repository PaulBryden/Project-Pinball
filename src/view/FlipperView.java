package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import model.IFlipper;
import observer.IObservable;
import observer.IObserver;
import physics.LineSegment;
import physics.Vect;

public class FlipperView implements IViewGizmo, IObserver{
	private IFlipper gizmo;
	private static final int GRID_WIDTH = 20;

	public FlipperView(IFlipper gizmo){
		this.gizmo = gizmo;
	}

	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		List<Vect> exactCoords = new LinkedList<>();
		List<LineSegment> lines = gizmo.getAllLineSegments();
		int diameter = (int) (GRID_WIDTH * gizmo.getWidth());
		int x = (int) (GRID_WIDTH * gizmo.getPivot().x() - diameter / 2);
		int y = (int) (GRID_WIDTH * gizmo.getPivot().y() - diameter / 2);
		int[] a;
		int[] b;

		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setColor(gizmo.getColour());
		g2D.fillOval(x, y, diameter, diameter);

		x = (int) (GRID_WIDTH * gizmo.getEndCentre().x() - diameter / 2);
		y = (int) (GRID_WIDTH * gizmo.getEndCentre().y() - diameter / 2);

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
	public void update(IObservable obsv, Object o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, Object arg) {
		//update graphic
	}

}
