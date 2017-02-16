package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Observable;

import model.IGizmo;
import observer.IObservable;
import observer.IObserver;
import physics.Vect;

public class TriangleView implements IViewGizmo, IObserver{
	private IGizmo gizmo;
	private static final int GRID_WIDTH = 20;

	public TriangleView(IGizmo gizmo){
		this.gizmo = gizmo;
	}

	public void paint(Graphics g){
		List<Vect> exactCoords = gizmo.getExactCoords();
		int[] x = new int[exactCoords.size()];
		int[] y = new int[exactCoords.size()];

		for (int i = 0; i < exactCoords.size(); i++) {
			x[i] = (int) (GRID_WIDTH * exactCoords.get(i).x());
			y[i] = (int) (GRID_WIDTH * exactCoords.get(i).y());
		}

		g.fillPolygon(x, y, exactCoords.size());
	}

	@Override
	public void setGizmo(IGizmo gizmo) {
		this.gizmo = gizmo;
	}

	@Override
	public Graphics GetViewObject() {
		return null;
	}

	@Override
	public void update(IObservable obsv, Object o) {

	}

	@Override
	public void update(Observable o, Object arg) {

	}

}
