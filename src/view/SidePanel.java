package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class SidePanel extends JPanel {

	static final String BUILD_INSTRUCTIONS = "In build mode you can build your own boards by placing and editing balls and gizmos, and adding your own connections.";

	private static final long serialVersionUID = 8844919255973893688L;

	private JLabel instructionLabel;
	private GridBagConstraints constraints;

	/**
	 * This constructor creates an empty side panel without building it. Should
	 * only be used by sub-classes.
	 */
	SidePanel() {
		this.setPreferredSize(new Dimension(200, 200));
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	}

	/**
	 * This constructor creates and builds a SidePanel containing nothing but an
	 * instruction panel.
	 * 
	 * @param instructions
	 */
	public SidePanel(String instructions) {
		this();
		build(instructions);
	}

	public void setInstructions(String instructions) {
		if (instructions == null || instructions.length() == 0) {
			if (instructionLabel != null) {
				this.remove(this.getComponentCount() - 1);
				instructionLabel = null;
			}
			return;
		}
		if (instructionLabel == null) {
			addInstructionPanel(instructions);
		} else {
			instructionLabel.setText("<html>" + instructions + "</html>");
		}
	}

	JPanel createTitledPanel(String title, int cols, JComponent... components) {
		JPanel panel = new JPanel();
		int rows = (components.length + cols - 1) / cols;
		panel.setLayout(new GridLayout(rows, cols, 5, 5));
		for (JComponent component : components) {
			panel.add(component);
		}
		if (components.length == 1 && cols != 1)
			panel.add(Box.createHorizontalGlue());
		panel.setBorder(BorderFactory.createTitledBorder(title));
		return panel;
	}

	protected void build(String instructions, JPanel... panels) {
		this.setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.weightx = 1;
		constraints.insets = new Insets(5, 3, 3, 3);
		constraints.fill = GridBagConstraints.HORIZONTAL;
		int row;
		for (row = 0; row < panels.length; row++) {
			constraints.gridy = row;
			add(panels[row], constraints);
		}
		constraints.gridy = row;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weighty = 1;
		add(Box.createVerticalGlue(), constraints);
		addInstructionPanel(instructions);
	}

	protected void build(JPanel... panels) {
		build(null, panels);
	}

	private void addInstructionPanel(String instructions) {
		if (instructions != null && instructions.length() != 0) {
			constraints.gridy = this.getComponentCount();
			constraints.fill = GridBagConstraints.HORIZONTAL;
			constraints.weighty = 0;
			add(createInstructionPanel(instructions), constraints);
		}
	}

	private JPanel createInstructionPanel(String instructions) {
		JPanel panel = new JPanel();
		instructionLabel = new JLabel();
		instructionLabel.setText("<html>" + instructions + "</html>");
		instructionLabel.setBackground(new Color(0, 0, 0, 0));
		instructionLabel.setVisible(true);
		instructionLabel.setFont(instructionLabel.getFont().deriveFont(Font.PLAIN));
		panel.setLayout(new BorderLayout());
		panel.add(Box.createRigidArea(new Dimension(5, 5)), BorderLayout.NORTH);
		panel.add(Box.createRigidArea(new Dimension(5, 5)), BorderLayout.WEST);
		panel.add(Box.createRigidArea(new Dimension(5, 5)), BorderLayout.EAST);
		panel.add(Box.createRigidArea(new Dimension(5, 5)), BorderLayout.SOUTH);
		panel.add(instructionLabel, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		return panel;
	}

}
