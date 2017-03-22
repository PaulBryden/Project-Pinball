package view;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import controller.PrimaryActionListener;

public abstract class AbstractToolBar extends JToolBar {

	private static final long serialVersionUID = -3009990934865534605L;

	private PrimaryActionListener listener;
	private Map<String, JButton> buttons;
	private Set<JToggleButton> toggleButtons;
	
	AbstractToolBar(String mode, PrimaryActionListener listener) {
		super(mode);
		this.listener = listener;
		listener.addToolBar(this);
		this.buttons = new HashMap<>();
		this.toggleButtons = new HashSet<>();
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

	void addToggleButton(String name, String toolTip) {
		JToggleButton button = ButtonFactory.createToggleButton(name, toolTip, listener);
		toggleButtons.add(button);
		this.add(button);
	}
	
	void connectToggleButtons() {
		ButtonFactory.makeToggleButtonsExclusive(toggleButtons.toArray(new JToggleButton[toggleButtons.size()]));
	}

}
