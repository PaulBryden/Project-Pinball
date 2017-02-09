package view;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.Observable;

import javax.swing.JPanel;

import model.IPolygon;
import observer.IObservable;
import observer.IObserver;

public class TriangleView implements IViewGizmo, IObserver{
	private IPolygon gizmo;
	private JPanel board;

	public TriangleView(JPanel board, IPolygon gizmo){
		this.gizmo=gizmo;
		this.board=board;
	}

	public void paint(Graphics g){
		Graphics2D g2D = (Graphics2D) g;
		Path2D.Double triangle = new Path2D.Double();

		triangle.moveTo(50, 50);
		triangle.lineTo(40, 70);
		triangle.lineTo(60, 70);
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
