package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class SidePanel extends JPanel {
	
	private static final long serialVersionUID = 8844919255973893688L;

	public SidePanel() {
		this.setPreferredSize(new Dimension(200, 200));
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	}

	protected JPanel createTitledButtonPanel(String title, JButton... buttons) {
		JPanel panel = new JPanel();
		int rows = (buttons.length + 1) / 2;
		panel.setLayout(new GridLayout(rows, 2, 5, 5));
		for (JButton button : buttons) {
			panel.add(button);
		}
		if (buttons.length == 1)
			panel.add(Box.createHorizontalGlue());
		panel.setBorder(BorderFactory.createTitledBorder(title));
		return panel;
	}
}
