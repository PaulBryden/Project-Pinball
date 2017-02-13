package view;

import java.awt.*;
import java.util.Observable;

import model.ICircle;
import observer.IObservable;
import observer.IObserver;

public class CircleView implements IViewGizmo, IObserver{
	protected ICircle gizmo;
	private static final int RADIUS = 20;

	public CircleView(ICircle gizmo){
		this.gizmo = gizmo;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//UPDATE GRAPHICS OBJECT
		
	}

	@Override
	public Graphics GetViewObject() {
		// TODO Auto-generated method stub
		return null;
	}

	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;

		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.fillOval(((int) gizmo.getGridCoords().x() - (RADIUS/2)), ((int) gizmo.getGridCoords().x() - (RADIUS/2)), RADIUS, RADIUS);
	}

	@Override
	public void update(IObservable obsv, Object o) {
		// TODO Auto-generated method stub
	}

}
