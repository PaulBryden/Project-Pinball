package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;

import controller.BuildKeyListener;
import controller.RunKeyListener;
import model.IModel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

import static java.awt.Color.BLUE;
import static java.awt.Color.RED;
import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.VERTICAL;
import static view.STATE.RUN;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -2379162245120133571L;
	private IModel model;
	private MenuBar menuBar;
	private JToolBar toolbar;
	private JToolBar sideToolBar;
	private Board board;
	private GridBagConstraints constraints;
	private RunKeyListener runKeyListener;
	private BuildKeyListener buildKeyListener;
	private JLabel statusLabel;

	public MainWindow(IModel model) {
		super();
		this.model = model;
		board = new Board(this, this.model);
		menuBar = new MenuBar(this);
		sideToolBar = new JToolBar();
		toolbar = new BuildToolBar(this);
		constraints = new GridBagConstraints();
		statusLabel = new JLabel("");
		setUpKeyListeners();
	}

	public void build() {
		setLayout(new GridBagLayout());
		setTitle("Gizmo Ball");
		setJMenuBar(menuBar);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(800, 550);

		constraints.fill = VERTICAL;
		constraints.gridx = 1;
		constraints.gridy = 0;
		add(toolbar, constraints);

		constraints.fill = CENTER;
		constraints.gridy = 1;
		add(board, constraints);

		constraints.gridy = 2;
		add(statusLabel, constraints);

		setVisible(true);
	}

	public void addSideToolBar(JToolBar sideToolBar) {
		constraints.fill = VERTICAL;
		constraints.gridx = 0;
		constraints.gridy = 1;

		remove(this.sideToolBar);
		this.sideToolBar = sideToolBar;
		add(this.sideToolBar, constraints);

		revalidate();
		repaint();
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
		constraints.fill = VERTICAL;

		remove(toolbar);

		if (toolbar instanceof RunToolBar) {
			stopRunning();
			toolbar = new BuildToolBar(this);
			setStatusLabel("");
		} else {
			toolbar = new RunToolBar(this, model);
			remove(sideToolBar);
			sideToolBar = new JToolBar();
			setStatusLabel("Stopped");
		}

		constraints.gridx = 1;
		constraints.gridy = 0;
		add(toolbar, constraints);

		revalidate();
		repaint();
	}

	public void enableClientView() {
		constraints.fill = VERTICAL;
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
		statusLabel.setForeground(BLUE);
		statusLabel.setText(status);
	}

	public void setWarningLabel(String warning) {
		statusLabel.setForeground(RED);
		statusLabel.setText(warning);
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
