package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import java.util.Observable;

import model.IGizmo;
import observer.IObservable;
import observer.IObserver;

public class TriangleView implements IViewGizmo, IObserver{
	private IGizmo gizmo;

	public TriangleView(IGizmo gizmo){
		this.gizmo = gizmo;
	}

	public void paint(Graphics g){
		Graphics2D g2D = (Graphics2D) g;
		Path2D.Double triangle = new Path2D.Double();

		triangle.moveTo(gizmo.getCoords().x(), gizmo.getCoords().y());
		triangle.lineTo(gizmo.getCoords().x() - 10, gizmo.getCoords().y() + 20);
		triangle.lineTo(gizmo.getCoords().x() + 10, gizmo.getCoords().y() + 20);

		triangle.closePath();

		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.fill(triangle);
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
