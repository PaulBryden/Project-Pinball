package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import controller.BoardMouseListener;
import model.IAbsorber;
import model.IBall;
import model.ICircleGizmo;
import model.IFlipper;
import model.IGizmo;
import model.IModel;
import model.ISquareGizmo;
import model.ITriangleGizmo;
import model.KeyTrigger;
import physics.Vect;

import static view.CUR_GIZMO.NONE;
import static view.STATE.BUILD;
import static view.STATE.RUN;

public class Board extends JPanel implements Observer {

	private static final long serialVersionUID = -4998929957953130907L;
	private MainWindow mainWindow;
	private IModel model;
	private List<IViewGizmo> viewGizmos;
	private List<IViewGizmo> viewBalls;
	static final int GRID_WIDTH = 20;
	private STATE state;
	private CUR_GIZMO selectedGizmo;
	private Vect selectedGizmoCoords;

	public Board(MainWindow mainWindow, IModel model) {
		super();
		this.mainWindow = mainWindow;
		this.model = model;
		model.addObserver(this);
		viewGizmos = new LinkedList<>();
		viewBalls = new LinkedList<>();
		state = BUILD;
		selectedGizmo = NONE;

		addMouseListener(new BoardMouseListener(mainWindow, model));
		setBackground(model.getBackgroundColour());
		setSize(new Dimension(400, 400));
		setPreferredSize(getSize());
		setMinimumSize(getSize());
		setMaximumSize(getSize());
	}

	public IModel getModel(){
		return (model);
	}

	public String getGizmoName(IGizmo gizmo){
		switch (gizmo.getID().charAt(0)) {
			case ('A'):
				return ("Absorber");
			case ('C'):
				return ("Circle");
			case ('L'):
				return ("Left-Flipper");
			case ('R'):
				return ("Right-Flipper");
			case ('S'):
				return ("Square");
			default:
				return ("Triangle");
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

	private  void removeKeyConnections(Map<Integer, KeyTrigger> map, IGizmo gizmo){
		for(KeyTrigger t : map.values()){
			Set<IGizmo> gizmos = t.getGizmosToTrigger();
			gizmos.stream().filter(gizmo1 -> gizmo1.equals(gizmo)).forEach(gizmos::remove);
		}
	}

	private void removeGizmoConnections(IGizmo gizmo){
		for(IGizmo gizmo1 : model.getGizmos()){
			Set<IGizmo> gizmosToTrigger = gizmo1.getGizmosToTrigger();
			gizmosToTrigger.stream().filter(gizmo2 -> gizmo2.equals(gizmo)).forEach(gizmosToTrigger::remove);
		}
	}

	public void removeGizmo(Vect coords){
		IGizmo gizmo = model.getGizmo(coords);

		model.getGizmos().remove(gizmo);
		removeKeyConnections(model.getKeyPressedTriggers(), gizmo);
		removeKeyConnections(model.getKeyReleasedTriggers(), gizmo);
		removeGizmoConnections(gizmo);
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

	public void setState(STATE state) {
		selectedGizmoCoords = null;
		this.state = state;
	}

	public void setSelectedGizmo(CUR_GIZMO gizmo) {
		selectedGizmoCoords = null;
		this.selectedGizmo = gizmo;
	}

	public void setSelectedGizmoCoords(Vect gizmoCoords){
		this.selectedGizmoCoords = gizmoCoords;
	}

	public STATE getState(){
		return (state);
	}

	public CUR_GIZMO getSelectedGizmo(){
		return (selectedGizmo);
	}

	public Vect getSelectedGizmoCoords(){
		return (selectedGizmoCoords);
	}

    @Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		List<IGizmo> gizmos = model.getGizmos();
		List<IBall> balls = model.getBalls();

		viewGizmos.clear();
		viewBalls.clear();

		for(IGizmo gizmo : gizmos){
			if(gizmo instanceof ITriangleGizmo)
				viewGizmos.add(new TriangleView(gizmo));
			else if(gizmo instanceof ISquareGizmo)
				viewGizmos.add(new SquareView(gizmo));
			else if(gizmo instanceof IFlipper)
				viewGizmos.add(new FlipperView((IFlipper) gizmo));
			else if(gizmo instanceof ICircleGizmo)
				viewGizmos.add(new CircleView((ICircleGizmo) gizmo));
			else if(gizmo instanceof IAbsorber)
				viewGizmos.add(new AbsorberView(gizmo));
		}

		viewBalls.addAll(balls.stream().map(BallView::new).collect(Collectors.toList()));

		if(!state.equals(RUN))
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
