package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.BoardMouseListener;
import model.Absorber;
import model.CircleGizmo;
import model.IBall;
import model.ICircle;
import model.IFlipper;
import model.IGizmo;
import model.IModel;
import model.LeftFlipper;
import model.RightFlipper;
import model.SquareGizmo;
import model.TriangleGizmo;
import physics.Vect;

import static controller.BoardMouseListener.STATE.RUN;

public class Board extends JPanel implements Observer {
	
	private IModel model;
	private List<IViewGizmo> viewGizmos;
	private List<IViewGizmo> viewBalls;
	private BoardMouseListener mouseListener;
	private static final int GRID_WIDTH = 20;

	public Board(IModel model) {
		super();
		this.model = model;
		model.addObserver(this);
		viewGizmos = new LinkedList<>();
		viewBalls = new LinkedList<>();
		mouseListener = new BoardMouseListener(this);

		addMouseListener(mouseListener);
		setBackground(model.getBackgroundColour());
//		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(
//				EtchedBorder.LOWERED, Color.BLACK, Color.BLACK)));
		setSize(new Dimension(400, 400));
		setPreferredSize(getSize());
		setMinimumSize(getSize());
		setMaximumSize(getSize());
	}

	public IModel getModel(){
		return (model);
	}

	public BoardMouseListener getMouseListener(){
		return (mouseListener);
	}

	public void addGizmo(IViewGizmo gizmo){
		viewGizmos.add(gizmo);
		model.addGizmo(gizmo.getGizmo());
		reRender();
	}

	public void addBall(IBall ball){
		viewBalls.add(new BallView(ball));
		model.addBall(ball);
		reRender();
	}

	public void removeGizmo(int x, int y){
		for (Iterator<IGizmo> iterator = model.getGizmos().iterator(); iterator.hasNext(); ) {
			if (iterator.next().getGridCoords().equals(new Vect(x, y))) {
				iterator.remove();
				return;
			}
		}

		model.getBalls().removeIf(ball -> ball.getGridCoords().equals(new Vect(x + 0.5, y + 0.5)));
	}

	public void moveGizmo(Vect oldCoords, Vect newCoords){
		for(IGizmo gizmo : model.getGizmos()){
			if(gizmo.getGridCoords().equals(oldCoords)){
				gizmo.setGridCoords(newCoords);
			}
		}
	}

	private void drawGrid(Graphics g) {
		int coord;

		g.setColor(Color.WHITE);

		for(int i = 0; i < GRID_WIDTH; i++) {
			coord = i * GRID_WIDTH;

			g.drawLine(coord, 0, coord, getHeight());
			g.drawLine(0, coord, getWidth(), coord);
		}
	}

	public boolean isCellEmpty(int x, int y){
		for(IGizmo gizmo : model.getGizmos()){
			if(gizmo.getGridCoords().equals(new Vect(x, y))){
				return (false);
			}
		}

		for(IBall ball : model.getBalls()){
			if(ball.getGridCoords().equals(new Vect(x + 0.5, y + 0.5))){
				return (false);
			}
		}

		return (true);
	}

    @Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		List<IGizmo> gizmos = model.getGizmos();
		List<IBall> balls = model.getBalls();

		viewGizmos.clear();
		viewBalls.clear();

		for(IGizmo gizmo : gizmos){
			if(gizmo instanceof TriangleGizmo)
				viewGizmos.add(new TriangleView(gizmo));
			else if(gizmo instanceof SquareGizmo)
				viewGizmos.add(new SquareView(gizmo));
			else if(gizmo instanceof LeftFlipper || gizmo instanceof RightFlipper)
				viewGizmos.add(new FlipperView((IFlipper) gizmo));
			else if(gizmo instanceof CircleGizmo)
				viewGizmos.add(new CircleView((ICircle) gizmo));
			else if(gizmo instanceof Absorber)
				viewGizmos.add(new AbsorberView(gizmo));
		}

		for(IBall ball : balls){
			viewBalls.add(new BallView(ball));
		}

		if(!mouseListener.getState().equals(RUN))
			drawGrid(g);

		for(IViewGizmo viewGizmo : viewGizmos) {
			viewGizmo.paint(g);
		}

		for(IViewGizmo viewBall : viewBalls) {
			viewBall.paint(g);
		}
    }

    public void reRender(){
		revalidate();
		repaint();
	}

	@Override
	public void update(Observable o, Object arg) {
		reRender();
	}
}
