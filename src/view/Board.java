package view;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import controller.BoardMouseListener;
import model.IAbsorber;
import model.IBall;
import model.ICircleGizmo;
import model.ICounterGizmo;
import model.IFlipper;
import model.IGizmo;
import model.IModel;
import model.ISpinner;
import model.ISquareGizmo;
import model.ITriangleGizmo;
import physics.Vect;
import static java.awt.Color.RED;
import static view.CUR_GIZMO.NONE;
import static view.STATE.*;

public class Board extends JPanel implements Observer {

	private static final long serialVersionUID = -4998929957953130907L;
	private MainWindow mainWindow;
	private IModel model;
	private List<IViewGizmo> viewGizmos;
	private List<IViewGizmo> viewBalls;
	static final int GRID_WIDTH = 20; // Width of one grid cell in pixels
	private STATE state;
	private CUR_GIZMO selectedGizmo;
	private Vect selectedGizmoCoords;
	private Color gridColour;

	public Board(MainWindow mainWindow, IModel model) {
		super();
		this.mainWindow = mainWindow;
		this.model = model;
		model.addObserver(this);
		viewGizmos = new LinkedList<>();
		viewBalls = new LinkedList<>();
		state = BUILD;
		selectedGizmo = NONE;
		gridColour = Color.WHITE;

		addMouseListener(new BoardMouseListener(mainWindow, model));
		setBackground(model.getBackgroundColour());
		setSize(new Dimension(400, 400));
		setPreferredSize(getSize());
		setMinimumSize(getSize());
		setMaximumSize(getSize());
	}

	public IModel getModel() {
		return (model);
	}

	public String getGizmoName(IGizmo gizmo) {
		switch (gizmo.getID().charAt(0)) {
			case ('A'): return ("Absorber");
			case ('C'): return ("Circle");
			case ('L'): return ("Left-Flipper");
			case ('R'): return ("Right-Flipper");
			case ('S'): return ("Square");
			case ('N'): return ("Counter gizmo");
			case ('P'): return ("Spinner Gizmo");
			case ('T'): return ("Triangle");
			default: return ("Unknown");
		}
	}

	public void updateKeyConnectionsInfo(Vect coords) {
		ConnectionSidePanel csp = (ConnectionSidePanel) mainWindow.getSidePanel();
		IGizmo gizmo = model.getGizmo(coords);
		if (gizmo == null) {
			csp.setKeyConnectionsVisible(false);
			return;
		}
		String triggers = gizmo.getGizmosToTrigger().stream().map(g -> g.getID()).collect(Collectors.joining(", "));
		String triggeredBy = model.getGizmos().stream().filter(g -> g.getGizmosToTrigger().contains(gizmo)).map(g -> g.getID()).collect(Collectors.joining(", "));
		csp.setExistingConnectionInfo(gizmo.getID(), getKeyConnections(gizmo), triggers.equals("") ? "[None]" : triggers, triggeredBy.equals("") ? "[None]" : triggeredBy);
		csp.setKeyConnectionsVisible(true);
	}

	private String getKeyConnections(IGizmo gizmo){
		List<String> l = new ArrayList<>();
		for(int i : model.getKeyPressedTriggers().keySet()){
			for(IGizmo gizmoToTrigger : model.getKeyPressedTriggers().get(i).getGizmosToTrigger()){
				if(gizmoToTrigger.equals(gizmo)){
					l.add(KeyEvent.getKeyText(i));
				}
			}
		}

		for(int i : model.getKeyReleasedTriggers().keySet()){
			for (IGizmo gizmoToTrigger : model.getKeyReleasedTriggers().get(i).getGizmosToTrigger()){
				if(gizmoToTrigger.equals(gizmo) && !l.contains(KeyEvent.getKeyText(i))){
					l.add(KeyEvent.getKeyText(i));
				}
			}
		}

		if(l.isEmpty())
			return "[None]";
		return String.join(", ", l);
	}

	private void drawGrid(Graphics g) {
		g.setColor(gridColour);
		int l = 3;
		for (int x = 0; x < 21 * GRID_WIDTH; x += GRID_WIDTH) {
			for (int y = 0; y < 21 * GRID_WIDTH; y += GRID_WIDTH) {
				g.drawLine(x - l, y, x + l, y);
				g.drawLine(x, y - l, x, y + l);
			}
		}
	}

	private void drawConnections(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.setColor(RED);

		for (IGizmo gizmo : model.getGizmos()) {
			for (IGizmo gizmoToTrigger : gizmo.getGizmosToTrigger()) {
				g2D.drawLine((int) gizmo.getGridCoords().x() * GRID_WIDTH + (GRID_WIDTH / 2),
						(int) gizmo.getGridCoords().y() * GRID_WIDTH + (GRID_WIDTH / 2),
						(int) (getGizmoName(gizmoToTrigger).equals("Right-Flipper")
								? (gizmoToTrigger.getGridCoords().x() + 1) : gizmoToTrigger.getGridCoords().x())
								* GRID_WIDTH + (GRID_WIDTH / 2),
						(int) gizmoToTrigger.getGridCoords().y() * GRID_WIDTH + (GRID_WIDTH / 2));
			}
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

	public void setSelectedGizmoCoords(Vect gizmoCoords) {
		this.selectedGizmoCoords = gizmoCoords;
	}

	public STATE getState() {
		return (state);
	}

	public CUR_GIZMO getSelectedGizmo() {
		return (selectedGizmo);
	}

	public Vect getSelectedGizmoCoords() {
		return (selectedGizmoCoords);
	}

	public Color getGridColour() {
		return gridColour;
	}

	public void setGridColour(Color colour) {
		this.gridColour = colour;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		setBackground(model.getBackgroundColour());

		List<IGizmo> gizmos = model.getGizmos();
		List<IBall> balls = model.getBalls();

		viewGizmos.clear();
		viewBalls.clear();

		for (IGizmo gizmo : gizmos) {
			if (gizmo instanceof ITriangleGizmo)
				viewGizmos.add(new TriangleView(gizmo));
			else if (gizmo instanceof ISquareGizmo)
				viewGizmos.add(new SquareView(gizmo));
			else if (gizmo instanceof IFlipper)
				viewGizmos.add(new FlipperView((IFlipper) gizmo));
			else if (gizmo instanceof ICircleGizmo)
				viewGizmos.add(new CircleView((ICircleGizmo) gizmo));
			else if(gizmo instanceof ISpinner)
				viewGizmos.add(new SpinnerView((ISpinner) gizmo));
			else if(gizmo instanceof IAbsorber)
				viewGizmos.add(new AbsorberView(gizmo));
			else if (gizmo instanceof ICounterGizmo)
				viewGizmos.add(new CounterGizmoView(gizmo, model.getTextColour()));
		}

		viewBalls.addAll(balls.stream().map(BallView::new).collect(Collectors.toList()));

		for (IViewGizmo viewGizmo : viewGizmos) {
			viewGizmo.paint(g);
		}

		for (IViewGizmo viewBall : viewBalls) {
			viewBall.paint(g);
		}
		if (!state.equals(RUN))
			drawGrid(g);

		if (state.equals(GIZMO_CONNECT) || state.equals(RM_GIZMO_CONNECT))
			drawConnections(g);
	}

	public void reRender() {
		revalidate();
		repaint();
	}

	@Override
	public void update(Observable o, Object arg) {
		reRender();
	}
}
