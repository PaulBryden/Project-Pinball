package mitview;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.BallGizmo;
import model.DavidsGizmo;
import model.GameModel;
import physics.LineSegment;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class Board extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	protected int width;
	protected int height;
	protected GameModel gm;
	private static final int GRID_WIDTH = 25;

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
		
		for (LineSegment wall : gm.getWalls()) {
			g2.drawLine((int) (wall.p1().x() * GRID_WIDTH), (int) (wall.p1().y() * GRID_WIDTH), (int) (wall.p2().x() * GRID_WIDTH), (int) (wall.p2().y() * GRID_WIDTH));
		}
		
		// Draw all the vertical lines
		for (DavidsGizmo gizmo : gm.getGizmos()) {
			g2.setColor(gizmo.getColour());
			g2.fillRect(gizmo.getX() * GRID_WIDTH, gizmo.getY() * GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
		}

		List<BallGizmo> balls = gm.getBalls();
		for (BallGizmo b : balls) {
			if (b != null) {
				g2.setColor(b.getColour());
				int x = (int) ((b.getCentre(0, 0).x() - b.getRadius()) * GRID_WIDTH);
				int y = (int) ((b.getCentre(0, 0).y() - b.getRadius()) * GRID_WIDTH);
				int width = (int) (2 * b.getRadius() * GRID_WIDTH);
				g2.fillOval(x, y, width, width);
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

}
