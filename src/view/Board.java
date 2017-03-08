package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

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
	private MainWindow mainWindow;
	private IModel model;
	private List<IViewGizmo> viewGizmos;
	private List<IViewGizmo> viewBalls;
	private BoardMouseListener mouseListener;
	private static final int GRID_WIDTH = 20;

	public Board(MainWindow mainWindow, IModel model) {
		super();
		this.mainWindow = mainWindow;
		this.model = model;
		model.addObserver(this);
		viewGizmos = new LinkedList<>();
		viewBalls = new LinkedList<>();
		mouseListener = new BoardMouseListener(mainWindow, model);

		addMouseListener(mouseListener);
		setBackground(model.getBackgroundColour());
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

	public String getGizmoName(IGizmo gizmo){
		switch (gizmo.getID().charAt(0)){
			case ('A'): return ("Absorber");
			case ('C'): return ("Circle");
			case ('L'): return ("Left-Flipper");
			case ('R'): return ("Right-Flipper");
			case ('S'): return ("Square");
			case ('T'): return ("Triangle");
			default: return ("Ball");
		}
	}

	public void addGizmo(IViewGizmo gizmo){
		viewGizmos.add(gizmo);
		model.addGizmo(gizmo.getGizmo());
		mainWindow.setStatusLabel(getGizmoName(gizmo.getGizmo()) + " Placed");
		reRender();
	}

	public void addBall(IBall ball){
		viewBalls.add(new BallView(ball));
		model.addBall(ball);
		mainWindow.setStatusLabel("Ball Placed");
		reRender();
	}

	public void removeGizmo(Vect coords){
		IGizmo gizmo = model.getGizmo(coords);

		model.getGizmos().remove(gizmo);
		mainWindow.setStatusLabel(getGizmoName(gizmo) + " Removed");
	}

	public void removeBall(Vect coords){
		model.getBalls().remove(model.getBall(coords));
		mainWindow.setStatusLabel("Ball Removed");
	}

	public void moveGizmo(Vect oldCoords, Vect newCoords){
		IGizmo gizmo = model.getGizmo(oldCoords);

		gizmo.setGridCoords(newCoords);
		mainWindow.setStatusLabel("Moved " + getGizmoName(gizmo) + " from " + oldCoords + " to " + newCoords);
	}

	public void moveBall(Vect oldCoords, Vect newCoords){
		model.getBall(oldCoords).setGridCoords(newCoords.plus(new Vect(0.5, 0.5)));
		mainWindow.setStatusLabel("Moved Ball from " + oldCoords + " to " + newCoords);
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

		viewBalls.addAll(balls.stream().map(BallView::new).collect(Collectors.toList()));

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
