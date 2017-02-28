package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.IModel;

public class RunKeyListener implements KeyListener {

	private IModel model;

	public RunKeyListener(IModel model) {
		this.model = model;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		model.processKeyPressedTrigger(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		model.processKeyReleasedTrigger(e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) { }
	
	public static final KeyListener createListener(IModel model) {
		return new MagicKeyListener(new RunKeyListener(model));
	}

}
