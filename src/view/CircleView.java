package view;

import java.awt.*;

import model.CircleGizmo;
import model.IGizmo;

public class CircleView implements IViewGizmo {
	
	protected CircleGizmo gizmo;

	public CircleView(CircleGizmo gizmo) {
		this.gizmo = gizmo;
	}

	public IGizmo getGizmo() {
		return (gizmo);
	}

	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		double radius = gizmo.getRadius();
		int width = (int) (2 * radius * Board.GRID_WIDTH);

		g2D.setColor(gizmo.getColour());
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.fillOval((int) ((gizmo.getCentre().x() - radius) * Board.GRID_WIDTH),
				(int) ((gizmo.getCentre().y() - radius) * Board.GRID_WIDTH), width, width);
	}

}
