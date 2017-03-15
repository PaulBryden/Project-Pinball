package view;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

import controller.ToolBarListener;
import model.IModel;

public abstract class AbstractToolBar extends JToolBar {

	private static final long serialVersionUID = 9159488944045570471L;

	private ToolBarListener listener;

	private Map<String, JButton> buttons;
	
	public AbstractToolBar(String mode, MainWindow mainWindow, IModel model) {
		super(mode);
		this.listener = new ToolBarListener(this);
		this.buttons = new HashMap<>();
		populateButtons();
	}
	
	protected abstract void populateButtons();

	public void disableButton(String name) {
		JButton button = buttons.get(name);
		if (button.isEnabled()) {
			button.setEnabled(false);
			setButtonIcon(button, name + "_disabled");
		}
	}
	
	public void enableButton(String name) {
		JButton button = buttons.get(name);
		if (!button.isEnabled()) {
			button.setEnabled(true);
			setButtonIcon(button, name);
		}
	}

	protected void makeButton(String name, String toolTip) {
		JButton button = new JButton();
		button.setActionCommand(name);
		button.setToolTipText(toolTip);
		button.addActionListener(listener);
		setButtonIcon(button, name);
		buttons.put(name, button);
		this.add(button);
	}
	
	protected void setButtonIcon(JButton button, String name) {
		String iconPath = "/icons/" + name + ".png";
		URL iconURL = RunToolBar.class.getResource(iconPath);
		if (iconURL != null) {
			button.setIcon(new ImageIcon(iconURL, name));
		} else {
			button.setText(name);
		}
	}

}
