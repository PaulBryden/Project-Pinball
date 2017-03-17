package view;

import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.PrimaryActionListener;

public class ConnectionSidePanel extends SidePanel {
	
	private static final long serialVersionUID = 5258214590215188225L;
	private PrimaryActionListener listener;
	
	public ConnectionSidePanel(PrimaryActionListener listener) {
		super();
		this.listener = listener;
		addButton("add_connection", "Add a connection between gizmos");
		addButton("remove_connection", "Remove a connection between gizmos");
	}

	private void addButton(String name, String toolTip) {
		JButton button = new JButton();
		button.setActionCommand(name);
		button.setToolTipText(toolTip);
		button.addActionListener(listener);
		setButtonIcon(button, name);
		this.add(button);
	}

	private void setButtonIcon(JButton button, String name) {
		String iconPath = "/icons/" + name + ".png";
		URL iconURL = RunToolBar.class.getResource(iconPath);
		if (iconURL != null) {
			button.setIcon(new ImageIcon(iconURL, name));
		} else {
			button.setText(name);
		}
	}
	
}
