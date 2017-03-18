package view;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.PrimaryActionListener;

public abstract class AbstractToolBar extends JToolBar {

	private static final long serialVersionUID = -3009990934865534605L;

	private PrimaryActionListener listener;

	private Map<String, JButton> buttons;
	
	AbstractToolBar(String mode, PrimaryActionListener listener) {
		super(mode);
		this.listener = listener;
		listener.addToolBar(this);
		this.buttons = new HashMap<>();
		setFloatable(false);
		populateButtons();
	}
	
	protected abstract void populateButtons();

	public void disableButton(String name) {
		JButton button = buttons.get(name);
		if (button != null && button.isEnabled()) {
			button.setEnabled(false);
			ButtonFactory.setButtonIcon(button, name + "_disabled");
		}
	}
	
	public void enableButton(String name) {
		JButton button = buttons.get(name);
		if (button != null && !button.isEnabled()) {
			button.setEnabled(true);
			ButtonFactory.setButtonIcon(button, name);
		}
	}

	void addButton(String name, String toolTip) {
		JButton button = ButtonFactory.createButton(name, toolTip, listener);
		buttons.put(name, button);
		this.add(button);
	}

}
