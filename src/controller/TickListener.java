package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.GameModel;
import model.IModel;

public class TickListener implements ActionListener {
	GameModel gameLoop;
	private IModel model;

	public TickListener(IModel model){
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.tick();
	}

}
