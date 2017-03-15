package controller;

import java.awt.event.KeyEvent;

import model.IModel;
import view.MainWindow;

public class RunKeyListener extends AbstractKeyListener {

	public RunKeyListener(IModel model, MainWindow mainWindow) {
		super(model, mainWindow);
	}

	@Override
	protected void magicKeyPressed(KeyEvent e) {
		if (model.isClient()) {
			model.addKeyToSend(" Pressed " + e.getKeyCode() + " /n");
		} else {
			model.processKeyPressedTrigger(e.getKeyCode());
		}
	}

	@Override
	protected void magicKeyReleased(KeyEvent e) {
		if (model.isClient()) {
			model.addKeyToSend(" Released " + e.getKeyCode() + " /n");
		} else {
			model.processKeyReleasedTrigger(e.getKeyCode());
		}
	}

	@Override
	protected void magicKeyTyped(KeyEvent e) {
		// Do nothing when key is typed
	}

}
