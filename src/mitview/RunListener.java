package mitview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import model.Constants;
import model.IModel;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class RunListener implements ActionListener {

	private Timer timer;
	private IModel model;

	public RunListener(IModel m) {
		model = m;
		timer = new Timer((int) (1000 * Constants.TICK_TIME), this);
	}

	@Override
	public final void actionPerformed(final ActionEvent e) {

		if (e.getSource() == timer) {
			model.tick();
		} else
			switch (e.getActionCommand()) {
			case "Start":
				timer.start();
				break;
			case "Stop":
				timer.stop();
				break;
			case "Tick":
				model.tick();
				break;
			case "Quit":
				System.exit(0);
				break;
			}
	}
}
