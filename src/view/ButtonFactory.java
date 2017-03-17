package view;

import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonFactory {
	
	public static JButton createButton(String name, String toolTip, ActionListener listener) {
		JButton button = new JButton();
		button.setActionCommand(name);
		button.setToolTipText(toolTip);
		button.addActionListener(listener);
		setButtonIcon(button, name);
		return button;
	}
	
	public static void setButtonIcon(JButton button, String name) {
		String iconPath = "/icons/" + name + ".png";
		URL iconURL = RunToolBar.class.getResource(iconPath);
		if (iconURL != null) {
			button.setIcon(new ImageIcon(iconURL, name));
		} else {
			button.setText(name);
		}
	}

}
