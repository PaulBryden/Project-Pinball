package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Constants;
import model.IModel;
import view.MainWindow;

public class RunListener implements ActionListener {

	private MainWindow mainWindow;
	private IModel model;
	private Timer timer;

	public RunListener(MainWindow mainWindow, IModel model) {
		this.mainWindow = mainWindow;
		this.model = model;
		timer = new Timer((int) (1000 * Constants.TICK_TIME), this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(timer)) {
			model.tick();
		} else {
			switch (e.getActionCommand()) {
			case "run":
				timer.start();
				mainWindow.getRunKeyListener().setListening(true);
				mainWindow.setStatusLabel("Running");
				break;
			case "tick":
				if (timer.isRunning())
					timer.stop();
				model.tick();
				mainWindow.setStatusLabel("Ticking");
				break;
			default:
				// do nothing
			}
		}
	}

	public void pause() {
		timer.stop();
		mainWindow.getRunKeyListener().setListening(false);
		mainWindow.setStatusLabel("Stopped");
	}

}
