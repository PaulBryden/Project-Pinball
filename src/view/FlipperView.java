package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

import model.IFlipper;
import model.IGizmo;
import observer.IObservable;
import observer.IObserver;
import physics.LineSegment;
import physics.Vect;

public class FlipperView implements IViewGizmo, IObserver{
	private IFlipper gizmo;
	private static final int WIDTH = 40;
	private static final int HEIGHT = 10;
	private static final int GRID_WIDTH = 20;

	public FlipperView(IFlipper gizmo){
		this.gizmo = gizmo;
	}

	@Override
	public void update(Observable o, Object arg) {
		//update graphic
	}

	@Override
	public Graphics GetViewObject() {
		// TODO Auto-generated method stub
		return null;
	}

	public void paint(Graphics g) {
		int d = (int) (GRID_WIDTH * gizmo.getWidth());
		int x = (int) (GRID_WIDTH * gizmo.getPivot().x() - d / 2);
		int y = (int) (GRID_WIDTH * gizmo.getPivot().y() - d / 2);
		g.fillOval(x, y, d, d);
		x = (int) (GRID_WIDTH * gizmo.getEndCentre().x() - d / 2);
		y = (int) (GRID_WIDTH * gizmo.getEndCentre().y() - d / 2);
		g.fillOval(x, y, d, d);
		List<Vect> l = new LinkedList<>();
		List<LineSegment> lines = gizmo.getAllLineSegments();
		l.add(lines.get(0).p1());
		l.add(lines.get(0).p2());
		l.add(lines.get(1).p2());
		l.add(lines.get(1).p1());

		int[] a = new int[l.size()];
		int[] b = new int[l.size()];
		for (int i = 0; i < l.size(); i++) {
			a[i] = (int) (GRID_WIDTH * l.get(i).x());
			b[i] = (int) (GRID_WIDTH * l.get(i).y());
		}
		g.fillPolygon(a, b, l.size());
	}

	@Override
	public void setGizmo(IGizmo gizmo) {
		this.gizmo = (IFlipper) gizmo;
	}

	@Override
	public void update(IObservable obsv, Object o) {
		// TODO Auto-generated method stub
		
	}

}
