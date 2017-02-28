package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.*;

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

	private IGizmo getGizmo(Vect coords){
		for(IGizmo gizmo : model.getGizmos()){
			Vect gizmoCoords = gizmo.getGridCoords();
			if(gizmoCoords != null && gizmoCoords.equals(coords)) return (gizmo);
		}

		throw new NoSuchElementException("Gizmo not found");
	}

	private IBall getBall(Vect coords){
		for(IBall ball : model.getBalls()){
			if(ball.getGridCoords().equals(new Vect(coords.x() + 0.5, coords.y() + 0.5))) return (ball);
		}

		throw new NoSuchElementException("Ball not found");
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

	public void removeGizmo(Vect coords){
		try {
			model.getGizmos().remove(getGizmo(coords));
			return;
		} catch (NoSuchElementException ignored){}

		model.getBalls().remove(getBall(coords));

	}

	public void moveGizmo(Vect oldCoords, Vect newCoords){
		try {
			getGizmo(oldCoords).setGridCoords(newCoords);
		} catch (NoSuchElementException ignored){}

		getBall(oldCoords).setGridCoords(new Vect(newCoords.x() + 0.5, newCoords.y() + 0.5));
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

	public boolean isCellEmpty(Vect coords){
		try {
			getGizmo(coords);
			return (false);
		} catch (NoSuchElementException ignored){}

		try {
			getBall(coords);
			return (false);
		} catch (NoSuchElementException e){
			return (true);
		}
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
