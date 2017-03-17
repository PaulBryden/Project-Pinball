package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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

	protected JPanel createInstructionPanel(String instructions) {
		JPanel panel = new JPanel();
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setPreferredSize(new Dimension(180, 80));
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setText(instructions);
		textArea.setBackground(new Color(0, 0, 0, 0));
		panel.add(textArea);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		return panel;
	}

	protected void build(String instructions, JPanel... panels) {
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.insets = new Insets(5, 3, 0, 3);
		c.fill = GridBagConstraints.HORIZONTAL;
		int row;
		for (row = 0; row < panels.length; row++) {
			c.gridy = row;
			add(panels[row], c);

		}
		c.gridy = row++;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		add(Box.createVerticalGlue(), c);
		if (instructions != null && instructions.length() != 0) {
			c.gridy = row++;
			c.fill = GridBagConstraints.HORIZONTAL;
			c.weighty = 0;
			add(createInstructionPanel(instructions), c);
		}
	}

	protected void build(JPanel... panels) {
		build(null, panels);
	}
}
