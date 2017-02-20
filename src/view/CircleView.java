package view;

import java.awt.*;
import java.util.Observable;

import model.ICircle;
import observer.IObservable;
import observer.IObserver;

public class CircleView implements IViewGizmo, IObserver{
	protected ICircle gizmo;
	private static final int GRID_WIDTH = 20;

	public CircleView(ICircle gizmo){
		this.gizmo = gizmo;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//UPDATE GRAPHICS OBJECT
	}

	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		double radius = gizmo.getRadius();
		int width = (int) (2 * radius * GRID_WIDTH);

		g2D.setColor(gizmo.getColour());
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.fillOval((int) ((gizmo.getCentre().x() - radius) * GRID_WIDTH),
				(int) ((gizmo.getCentre().y() - radius) * GRID_WIDTH), width, width);
	}

	@Override
	public void update(IObservable obsv, Object o) {
	}

}
