package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.util.Observable;

import model.IFlipper;
import model.IGizmo;
import observer.IObservable;
import observer.IObserver;

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
		Graphics2D g2D = (Graphics2D) g;

		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.fill(new RoundRectangle2D.Float(((int) gizmo.getGridCoords().x() * GRID_WIDTH),
				((int) gizmo.getGridCoords().y() * GRID_WIDTH), WIDTH, HEIGHT, HEIGHT, HEIGHT));
		//double x, double y, double w, double h, double arcWidth, double arcHeight
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
