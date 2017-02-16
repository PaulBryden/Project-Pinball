package view;

import java.awt.Graphics;
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
		List<Vect> exactCoords = new LinkedList<>();
		List<LineSegment> lines = gizmo.getAllLineSegments();
		int diameter = (int) (GRID_WIDTH * gizmo.getWidth());
		int x = (int) (GRID_WIDTH * gizmo.getPivot().x() - diameter / 2);
		int y = (int) (GRID_WIDTH * gizmo.getPivot().y() - diameter / 2);
		int[] a;
		int[] b;

		g.fillOval(x, y, diameter, diameter);

		x = (int) (GRID_WIDTH * gizmo.getEndCentre().x() - diameter / 2);
		y = (int) (GRID_WIDTH * gizmo.getEndCentre().y() - diameter / 2);

		g.fillOval(x, y, diameter, diameter);

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

		g.fillPolygon(a, b, exactCoords.size());
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
