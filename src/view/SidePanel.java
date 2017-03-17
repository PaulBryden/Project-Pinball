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

	public static final String BUILD_INSTRUCTIONS = "In build mode you can build your own boards by placing and editing balls and gizmos, and adding your own connections.";

	private static final long serialVersionUID = 8844919255973893688L;

	private JTextArea instructionTextArea;
	private GridBagConstraints constraints;

	/**
	 * This constructor creates an empty side panel without building it. Should
	 * only be used by sub-classes.
	 */
	protected SidePanel() {
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
			if (instructionTextArea != null) {
				this.remove(this.getComponentCount() - 1);
				instructionTextArea = null;
			}
			return;
		}
		if (instructionTextArea == null) {
			addInstructionPanel(instructions);
		} else {
			instructionTextArea.setText(instructions);
		}
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
		constraints.gridy = row++;
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
		instructionTextArea = new JTextArea();
		instructionTextArea.setEditable(false);
		instructionTextArea.setPreferredSize(new Dimension(180, 80));
		instructionTextArea.setWrapStyleWord(true);
		instructionTextArea.setLineWrap(true);
		instructionTextArea.setText(instructions);
		instructionTextArea.setBackground(new Color(0, 0, 0, 0));
		instructionTextArea.setHighlighter(null);
		panel.add(instructionTextArea);
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		return panel;
	}

}
