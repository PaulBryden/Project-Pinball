package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Constants;
import model.IModel;

public class RunListener  implements ActionListener{
	private IModel model;
	private Timer timer;
	

	public RunListener(IModel model) {
		this.model = model;
		timer = new Timer((int) (1000 * Constants.TICK_TIME), this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == timer) {
			model.tick();
		} else
			switch (e.getActionCommand()) {
				case "Run":
					timer.start();
					break;
				case "Stop":
					timer.stop();
					break;
				case "Tick":
					model.tick();
					break;
			}
	}

}
