package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

import model.IGizmo;
import observer.IObservable;
import observer.IObserver;

public class SquareView implements IViewGizmo, IObserver {

	private IGizmo gizmo;

	public SquareView(IGizmo gizmo){
		this.gizmo = gizmo;
	}

	public void paint(Graphics g){
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
