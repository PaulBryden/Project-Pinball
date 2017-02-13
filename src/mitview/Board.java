package mitview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.GameModel;
import model.IBall;
import model.IFlipper;
import model.IGizmo;
import model.IWall;
import model.SquareGizmo;
import physics.LineSegment;
import physics.Vect;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Board extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected GameModel gm;
	private static final int GRID_WIDTH = 35;

	public Board(int w, int h, GameModel m) {
		// Observe changes in Model
		m.addObserver(this);
		width = GRID_WIDTH * 20;
		height = GRID_WIDTH * 20;
		gm = m;
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	// Fix onscreen size
	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		
		for (IWall wall : gm.getWalls()) {
			g2.drawLine((int) (wall.p1().x() * GRID_WIDTH), (int) (wall.p1().y() * GRID_WIDTH), (int) (wall.p2().x() * GRID_WIDTH), (int) (wall.p2().y() * GRID_WIDTH));
		}
		
		// Draw all the vertical lines
		for (IGizmo gizmo : gm.getGizmos()) {
			if (gizmo instanceof SquareGizmo)
				drawSquareGizmo((SquareGizmo) gizmo, g2);
			else if (gizmo instanceof IFlipper)
				drawFlipper((IFlipper) gizmo, g2);
		}

		List<IBall> balls = gm.getBalls();
		for (IBall b : balls) {
			if (b != null) {
				g2.setColor(b.getColour());
				int x = (int) ((b.getCentre().x() - b.getRadius()) * GRID_WIDTH);
				int y = (int) ((b.getCentre().y() - b.getRadius()) * GRID_WIDTH);
				int width = (int) (2 * b.getRadius() * GRID_WIDTH);
				g2.fillOval(x, y, width, width);
			}
		}
	}
	
	private void drawSquareGizmo(SquareGizmo gizmo, Graphics2D g) {
		g.setColor(gizmo.getColour());
		g.fillRect((int)gizmo.getGridCoords().x() * GRID_WIDTH, (int)gizmo.getGridCoords().y()  * GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
	}
	
	private void drawFlipper(IFlipper flipper, Graphics2D g) {
		g.setColor(flipper.getColour());
		int d = (int) (GRID_WIDTH * flipper.getWidth());
		int x = (int) (GRID_WIDTH * flipper.getPivot().x() - d/2);
		int y = (int) (GRID_WIDTH * flipper.getPivot().y() - d/2);
		g.fillOval(x, y, d, d);
		x = (int) (GRID_WIDTH * flipper.getEndCentre().x() - d/2);
		y = (int) (GRID_WIDTH * flipper.getEndCentre().y() - d/2);
		g.fillOval(x, y, d, d);
		List<Vect> l = new LinkedList<>();
		List<LineSegment> lines = flipper.getAllLineSegments();
		l.add(lines.get(0).p1());
		l.add(lines.get(0).p2());
		l.add(lines.get(1).p2());
		l.add(lines.get(1).p1());
		drawPolygon(l, g);
	}
	
	private void drawPolygon(List<Vect> l, Graphics2D g) {
		int[] x = new int[l.size()];
		int[] y = new int[l.size()];
		for (int i = 0; i < l.size(); i++) {
			x[i] = (int) (GRID_WIDTH * l.get(i).x());
			y[i] = (int) (GRID_WIDTH * l.get(i).y());
		}
		g.fillPolygon(x, y, l.size());
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

}
