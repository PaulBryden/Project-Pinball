package view;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
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
	private JToolBar sideToolBar;
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
		sideToolBar = new JToolBar();
		toolbar = new BuildToolBar(this, actionListener);
		statusBar = new StatusBar();
		setUpKeyListeners();
	}

	public void build() {
		setLayout(new BorderLayout());
		setTitle("Gizmo Ball");
		setJMenuBar(menuBar);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		add(toolbar, BorderLayout.NORTH);
		add(board, BorderLayout.CENTER);
		add(statusBar, BorderLayout.SOUTH);
		setVisible(true);
		setResizable(false);
		pack();
	}

	public void addSideToolBar(JToolBar sideToolBar) {
		add(this.sideToolBar, BorderLayout.EAST);
		pack();
	}

	public JToolBar getSideToolBar() {
		return (sideToolBar);
	}
	
	public void stopRunning() {
		if (toolbar instanceof RunToolBar) {
			((RunToolBar) toolbar).stop();
		}
	}

	public void toggleView() {
		remove(toolbar);
		if (toolbar instanceof RunToolBar) {
			stopRunning();
			toolbar = new BuildToolBar(this, actionListener);
			setStatusLabel("");
		} else {
			toolbar = new RunToolBar(this, actionListener);
			remove(sideToolBar);
			sideToolBar = new JToolBar();
			setStatusLabel("Stopped");
		}
		add(toolbar, BorderLayout.NORTH);
		pack();
	}

	public void enableClientView() {
		remove(sideToolBar);
		remove(toolbar);
		board.setState(RUN);
		remove(sideToolBar);
		remove(toolbar);
		setJMenuBar(new JMenuBar());
		setStatusLabel("Connected");
		revalidate();
		repaint();
	}

	public Board getBoard() {
		return (board);
	}

	public void setStatusLabel(String status) {
		statusBar.setStatus(status);
	}

	public void setWarningLabel(String warning) {
		statusBar.setStatus(warning);
	}

	public void showHostDialog() {
		stopRunning();
		HostDialog hostDialog = new HostDialog(this);
		hostDialog.build();
	}

	public void showClientDialog() {
		stopRunning();
		ClientDialog clientDialog = new ClientDialog(this);
		clientDialog.build();
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
