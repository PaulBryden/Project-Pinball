package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Observable;

import model.IFlipper;
import observer.IObservable;
import observer.IObserver;

public class FlipperView implements IViewGizmo, IObserver{
	private IFlipper gizmo;

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

		g2D.fill(new RoundRectangle2D.Float(300, 300, 40, 10, 10, 10));
	}

	@Override
	public void update(IObservable obsv, Object o) {
		// TODO Auto-generated method stub
		
	}

}
