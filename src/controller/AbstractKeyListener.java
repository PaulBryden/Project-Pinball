package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.IModel;
import view.MainWindow;

public abstract class AbstractKeyListener implements KeyListener {

	protected IModel model;
	protected MainWindow mainWindow;
	private boolean listening;
	private KeyListener mkl;

	AbstractKeyListener(IModel model, MainWindow mainWindow) {
		this.model = model;
		this.mainWindow = mainWindow;
		this.listening = false;
		this.mkl = new MagicKeyListener(new DummyKeyListener());
	}

	public void setListening(boolean listening) {
		this.listening = listening;
	}

	public boolean isListening() {
		return this.listening;
	}

	protected abstract void magicKeyPressed(KeyEvent e);

	protected abstract void magicKeyReleased(KeyEvent e);

	protected abstract void magicKeyTyped(KeyEvent e);

	@Override
	public void keyPressed(KeyEvent e) {
		mkl.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		mkl.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		mkl.keyTyped(e);
	}

	class DummyKeyListener implements KeyListener {

		@Override
		public void keyPressed(KeyEvent e) {
			if (isListening())
				magicKeyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (isListening())
				magicKeyReleased(e);
		}

		@Override
		public void keyTyped(KeyEvent e) {
			if (isListening())
				magicKeyTyped(e);
		}

	}
}