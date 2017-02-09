package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

import model.IPolygon;
import observer.IObservable;
import observer.IObserver;

public class SquareView implements IViewGizmo, IObserver {

	private IPolygon gizmo;

	public SquareView(IPolygon gizmo){
		this.gizmo = gizmo;
	}

	public void paint(Graphics g){
		g.drawRect(100,200,20,20);
		g.setColor(Color.BLUE);
		g.fillRect(100,200,20,20);
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
