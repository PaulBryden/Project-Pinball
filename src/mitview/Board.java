package mitview;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.GameModel;
import model.IBall;
import model.IGizmo;
import model.IWall;

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
			g2.setColor(gizmo.getColour());
			g2.fillRect((int)gizmo.getCoords().x() * GRID_WIDTH, (int)gizmo.getCoords().y()  * GRID_WIDTH, GRID_WIDTH, GRID_WIDTH);
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

	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}

}
