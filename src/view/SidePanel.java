package view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class SidePanel extends JPanel {
	
	private static final long serialVersionUID = 8844919255973893688L;

	public SidePanel() {
		this.setPreferredSize(new Dimension(200, 200));
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	}

	protected JPanel createTitledPanel(String title, int cols, JComponent... components) {
		JPanel panel = new JPanel();
		int rows = (components.length + cols - 1) / cols;
		panel.setLayout(new GridLayout(rows, cols, 5, 5));
		for (JComponent component : components) {
			panel.add(component);
		}
		if (components.length == 1)
			panel.add(Box.createHorizontalGlue());
		panel.setBorder(BorderFactory.createTitledBorder(title));
		return panel;
	}
	
}
