package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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

	public void addViewGizmo(IViewGizmo gizmo){
		viewGizmos.add(gizmo);
		reRender();
	}

	public void addViewBall(IViewGizmo ball){
		viewBalls.add(ball);
		reRender();
	}

	public void removeViewGizmo(IViewGizmo gizmo){
		viewGizmos.remove(gizmo);
		reRender();
	}

	public void removeViewBall(IViewGizmo ball){
		viewBalls.remove(ball);
		reRender();
	}

	private void drawGrid(Graphics g) {
		int gridSize = getWidth() / GRID_WIDTH;
		int x;
		int y;

		g.setColor(Color.WHITE);

		for (int i = 0; i < GRID_WIDTH; i++) {
			for (int j = 0; j < GRID_WIDTH; j++) {
				x = i * gridSize;
				y = j * gridSize;

				g.drawLine(x, 0, x, getSize().height);
				g.drawLine(0, y, getSize().width, y);
			}
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

		if(!mouseListener.getState().equals(BoardMouseListener.STATE.RUN))
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
