package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import model.Constants;
import model.IModel;

public class RunListener  implements ActionListener{
	IModel model;
	Timer timer;
	

	public RunListener(IModel m) {
		model = m;
		timer = new Timer((int) (1000 * Constants.TICK_TIME), this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
