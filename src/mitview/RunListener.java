package mitview;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import model.GameModel;

/**
 * @author Murray Wood Demonstration of MVC and MIT Physics Collisions 2014
 */

public class RunListener implements ActionListener {

	private Timer timer;
	private GameModel model;

	public RunListener(GameModel m) {
		model = m;
		timer = new Timer(20, this);
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
