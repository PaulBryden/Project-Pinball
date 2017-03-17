package view;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import controller.BuildKeyListener;
import controller.PrimaryActionListener;
import controller.RunKeyListener;
import model.IModel;

import java.awt.BorderLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import static view.STATE.RUN;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -2379162245120133571L;
	private PrimaryActionListener actionListener;
	private IModel model;
	private MenuBar menuBar;
	private JToolBar toolbar;
	private SidePanel sidePanel;
	private Board board;
	private RunKeyListener runKeyListener;
	private BuildKeyListener buildKeyListener;
	private StatusBar statusBar;

	public MainWindow(IModel model) {
		super();
		this.model = model;
		board = new Board(this, this.model);
		actionListener = new PrimaryActionListener(this, model);
		menuBar = new MenuBar(this, actionListener);
		toolbar = new BuildToolBar(actionListener);
		statusBar = new StatusBar();
		setUpKeyListeners();
	}

	public void build() {
		setResizable(false);
		setLayout(new BorderLayout());
		setTitle("Gizmo Ball");
		setJMenuBar(menuBar);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		add(toolbar, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);
		sidePanel = new SidePanel();
		this.add(sidePanel, BorderLayout.EAST);
		pack();
		setVisible(true);
	}

	public void setSidePanel(SidePanel sidePanel) {
		if (this.sidePanel != null)
			this.remove(this.sidePanel);
		this.sidePanel = sidePanel;
		if (sidePanel != null)
			add(this.sidePanel, BorderLayout.EAST);
		revalidate();
		repaint();
	}

	public SidePanel getSidePanel() {
		return (sidePanel);
	}

	public void toggleView() {
		remove(toolbar);
		if (toolbar instanceof RunToolBar) {
			actionListener.pauseGame();
			toolbar = new BuildToolBar(actionListener);
			setSidePanel(new SidePanel());
			setStatusLabel("");
		} else {
			toolbar = new RunToolBar(actionListener);
			setSidePanel(null);
			setStatusLabel("Stopped");
		}
		add(toolbar, BorderLayout.NORTH);
		revalidate();
		repaint();
		pack();
	}

	public void enableClientView() {
		setSidePanel(null);
		remove(toolbar);
		toolbar = new ClientToolBar(actionListener);
		add(toolbar, BorderLayout.NORTH);
		board.setState(RUN);
		setStatusLabel("Connected");
		revalidate();
		repaint();
		pack();
	}

	public Board getBoard() {
		return (board);
	}

	public IModel getModel() {
		return model;
	}
	
	public void setStatusLabel(String status) {
		statusBar.setStatus(status);
	}

	public void setWarningLabel(String warning) {
		statusBar.setWarning(warning);
	}

	private void setUpKeyListeners() {
		// Use an event dispatcher so that key strokes are captured regardless
		// of which element of the frame has focus.
		this.runKeyListener = new RunKeyListener(model, this);
		this.buildKeyListener = new BuildKeyListener(model, this);
		KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		kfm.addKeyEventDispatcher(e -> {
			if (runKeyListener.isListening()) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					runKeyListener.keyPressed(e);
				}
				if (e.getID() == KeyEvent.KEY_RELEASED) {
					runKeyListener.keyReleased(e);
				}
				if (e.getID() == KeyEvent.KEY_TYPED) {
					runKeyListener.keyTyped(e);
				}
				return true;
			} else if (buildKeyListener.isListening()) {
				if (e.getID() == KeyEvent.KEY_PRESSED) {
					buildKeyListener.keyPressed(e);
				}
				if (e.getID() == KeyEvent.KEY_RELEASED) {
					buildKeyListener.keyReleased(e);
				}
				if (e.getID() == KeyEvent.KEY_TYPED) {
					buildKeyListener.keyTyped(e);
				}
				return true;
			}
			return false;
		});
	}

	public RunKeyListener getRunKeyListener() {
		return this.runKeyListener;
	}

	public BuildKeyListener getBuildKeyListener() {
		return this.buildKeyListener;
	}
}
