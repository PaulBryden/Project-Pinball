package view;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import model.IBall;
import model.IGizmo;

public class BallView implements IViewGizmo {
	
	private IBall ball;
	
    public BallView(IBall ball){
    	this.ball = ball;
    }

	public IGizmo getGizmo() {
		return ball;
	}
	
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		double radius = ball.getRadius();
		int width = (int) (2 * radius * Board.GRID_WIDTH);

		g2D.setColor(ball.getColour());
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.fillOval((int) ((ball.getCentre().x() - radius) * Board.GRID_WIDTH),
				(int) ((ball.getCentre().y() - radius) * Board.GRID_WIDTH), width, width);
	}
}
