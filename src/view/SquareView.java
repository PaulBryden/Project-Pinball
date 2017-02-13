package view;

import java.awt.Graphics;
import java.util.Observable;

import model.IGizmo;
import observer.IObservable;
import observer.IObserver;

public class SquareView implements IViewGizmo, IObserver {

	private IGizmo gizmo;
	private int width;
	private int height;

	public SquareView(IGizmo gizmo, int width, int height){
		this.gizmo = gizmo;
		this.width = width;
		this.height = height;
	}

	public void paint(Graphics g){
		g.fillRect((int) gizmo.getCoords().x(),(int) gizmo.getCoords().y(), width ,height);
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
