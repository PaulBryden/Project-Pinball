package view;

import java.awt.Graphics;
import java.util.Observable;

import model.IGizmo;
import observer.IObservable;
import observer.IObserver;

public class SquareView implements IViewGizmo, IObserver {

	private IGizmo gizmo;
	private static final int WIDTH = 20;
	private static final int GRID_WIDTH = 20;

	public SquareView(IGizmo gizmo){
		this.gizmo = gizmo;
	}

	public void paint(Graphics g){
		g.fillRect((int) gizmo.getGridCoords().x() * GRID_WIDTH ,
				(int) gizmo.getGridCoords().y() * GRID_WIDTH, WIDTH, WIDTH);
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
