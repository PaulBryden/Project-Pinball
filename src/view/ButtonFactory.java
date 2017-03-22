package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import controller.PrimaryActionListener;

class ButtonFactory {
	
	private static JButton makeBaseButton(String name, String toolTip, ActionListener listener) {
		JButton button = new JButton();
		button.setActionCommand(name);
		button.setToolTipText(toolTip);
		button.addActionListener(listener);
		return button;
	}
	
	public static JButton createButton(String name, String toolTip, ActionListener listener) {
		JButton button = makeBaseButton(name, toolTip, listener);
		setButtonIcon(button, name);
		return button;
	}
	
	static void setButtonIcon(JButton button, String name) {
		String iconPath = "/icons/" + name + ".png";
		URL iconURL = RunToolBar.class.getResource(iconPath);
		if (iconURL != null) {
			button.setIcon(new ImageIcon(iconURL, name));
		} else {
			button.setText(name);
		}
	}
	
	public static JButton createColourButton(Color colour, String name, String toolTip, ActionListener listener) {
		JButton button = makeBaseButton(name, toolTip, listener);
		button.setSize(new Dimension(20, 10));
		setButtonColour(button, colour);
		return button;
	}
	
	public static void setButtonColour(JButton button, Color colour) {
		button.setBackground(colour);
	}

	private static JToggleButton makeBaseToggleButton(String name, String toolTip, ActionListener listener) {
		JToggleButton button = new JToggleButton();
		button.setActionCommand(name);
		button.setToolTipText(toolTip);
		button.addActionListener(listener);
		return button;
	}

	static void setButtonIcon(JToggleButton button, String name) {
		String iconPath = "/icons/" + name + ".png";
		URL iconURL = RunToolBar.class.getResource(iconPath);
		if (iconURL != null) {
			button.setIcon(new ImageIcon(iconURL, name));
		} else {
			button.setText(name);
		}
	}
	
	public static JToggleButton createToggleButton(String name, String toolTip, PrimaryActionListener listener) {
		JToggleButton button = makeBaseToggleButton(name, toolTip, listener);
		setButtonIcon(button, name);
		return button;
	}

	public static void makeToggleButtonsExclusive(JToggleButton... buttons) {
		for (JToggleButton button : buttons) {
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (JToggleButton b : buttons) {
						if (!b.equals(e.getSource()))
							b.setSelected(false);
						else
							b.setSelected(true);
					}
				}
			});
		}
	}

}
