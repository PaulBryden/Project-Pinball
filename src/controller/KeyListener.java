package controller;

import java.awt.event.KeyEvent;

import model.IModel;

public class KeyListener implements java.awt.event.KeyListener{
	private IModel model;
	private boolean pressed;

	public KeyListener(IModel model){
		this.model = model;
		pressed = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!pressed) {
			model.processKeyTrigger(e.getKeyChar());
			pressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (pressed) {
			model.processKeyTrigger(e.getKeyChar());
			pressed = false;
		}
	}
}
