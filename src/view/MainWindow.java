package view;

import javax.swing.*;

import controller.RunKeyListener;
import model.IModel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static java.awt.GridBagConstraints.CENTER;
import static java.awt.GridBagConstraints.VERTICAL;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -2379162245120133571L;
	private IModel model;
	private MenuBar menuBar;
	private JToolBar toolbar;
	private JToolBar sideToolBar;
	private Board board;
	private GridBagConstraints constraints;
	private KeyListener keyListener;
	private JLabel statusLabel;

	public MainWindow(IModel model) {
		super();
		this.model = model;
		board = new Board(this.model);
		menuBar = new MenuBar(this);
		sideToolBar = new JToolBar();
		toolbar = new BuildToolBar(this);
		constraints = new GridBagConstraints();
		statusLabel = new JLabel("");
		setUpKeyListener();
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

	public void toggleView() {
		constraints.fill = VERTICAL;

		remove(toolbar);

		if (toolbar instanceof RunToolBar) {
			((RunToolBar) toolbar).stop();
			toolbar = new BuildToolBar(this);
		} else {
			toolbar = new RunToolBar(this, model);
			remove(sideToolBar);
			sideToolBar = new JToolBar();
		}

		constraints.gridx = 1;
		constraints.gridy = 0;
		add(toolbar, constraints);

		revalidate();
		repaint();
	}

	public Board getBoard() {
		return (board);
	}

	public void setStatusLabel(String status){
		statusLabel.setText(status);
	}

	private void setUpKeyListener() {
		// Use an event dispatcher so that key strokes are captured regardless
		// of which element of the frame has focus.
		this.keyListener = RunKeyListener.createListener(model);
		KeyboardFocusManager kfm = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		kfm.addKeyEventDispatcher(e -> {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                keyListener.keyPressed(e);
                return true;
            }
            if (e.getID() == KeyEvent.KEY_RELEASED) {
                keyListener.keyReleased(e);
                return true;
            }
            return false;
        });
	}
}
