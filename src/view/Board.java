package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import model.*;

public class Board extends JPanel implements Observer {
	
	private IModel model;
	private List<IViewGizmo> viewGizmos;
	private List<IViewGizmo> viewBalls;

	public Board(IModel model) {
		super();
		this.model = model;
		model.addObserver(this);
		viewGizmos = new LinkedList<>();
		viewBalls = new LinkedList<>();
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(
				EtchedBorder.RAISED, Color.BLACK, Color.BLACK)));
		setBackground(Color.BLACK);
		setSize(new Dimension(400, 400));
		setPreferredSize(getSize());
		setMinimumSize(getSize());
		setMaximumSize(getSize());
	}

	public IModel getModel(){
		return (model);
	}

	public void addViewGizmo(IViewGizmo gizmo){
		viewGizmos.add(gizmo);
		revalidate();
		repaint();
	}

	public void addViewBall(IViewGizmo ball){
		viewBalls.add(ball);
		revalidate();
		repaint();
	}

	public void removeViewGizmo(IViewGizmo gizmo){
		viewGizmos.remove(gizmo);
		revalidate();
		repaint();
	}

	public void removeViewBall(IViewGizmo ball){
		viewBalls.remove(ball);
		revalidate();
		repaint();
	}

    @Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		List<IGizmo> gizmos = model.getGizmos();
		List<IBall> balls = model.getBalls();

		//If load
		if((viewGizmos.isEmpty() && viewBalls.isEmpty()) && (!gizmos.isEmpty() || !balls.isEmpty())){
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
					viewGizmos.add(new AbsorberView(gizmo, this));
			}

			for(IBall ball : balls){
				viewBalls.add(new BallView(ball));
			}
		}

		for (int i = 0; i < viewGizmos.size(); i++) {
			viewGizmos.get(i).setGizmo(gizmos.get(i));
			viewGizmos.get(i).paint(g);
		}

		for (int i = 0; i < viewBalls.size(); i++) {
			viewBalls.get(i).setGizmo(balls.get(i));
			viewBalls.get(i).paint(g);
		}
    }

	@Override
	public void update(Observable o, Object arg) {
		revalidate();
		repaint();
	}
}
