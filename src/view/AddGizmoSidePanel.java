package view;

import controller.*;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class AddGizmoSidePanel extends SidePanel {

	private static final long serialVersionUID = 5655448783959185202L;

	public AddGizmoSidePanel(MainWindow mainWindow) {
		super();
		JButton ballBtn = ButtonFactory.createButton("ball", "Place a ball", new AddBallListener(mainWindow));
		JButton absorberBtn = ButtonFactory.createButton("absorber", "Place an absorber",
				new AddAbsorberListener(mainWindow));
		JButton circleBtn = ButtonFactory.createButton("circle", "Place a circle gizmo",
				new AddCircleListener(mainWindow));
		JButton squareBtn = ButtonFactory.createButton("square", "Place a square gizmo",
				new AddSquareListener(mainWindow));
		JButton triangleBtn = ButtonFactory.createButton("triangle", "Place a triangle gizmo",
				new AddTriangleListener(mainWindow));
		JButton lFlipperBtn = ButtonFactory.createButton("left_flipper", "Place a left flipper",
				new AddLFlipperListener(mainWindow));
		JButton rFlipperBtn = ButtonFactory.createButton("right_flipper", "Place a right flipper",
				new AddRFlipperListener(mainWindow));
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		add(createPanel("Balls", ballBtn), c);
		c.gridy = 1;
		add(createPanel("Static gizmos", circleBtn, squareBtn, triangleBtn), c);
		c.gridy = 2;
		add(createPanel("Dynamic gizmos", lFlipperBtn, rFlipperBtn, absorberBtn), c);
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		add(Box.createVerticalGlue(), c);
	}

	private JPanel createPanel(String title, JButton... buttons) {
		JPanel panel = new JPanel();
		int rows = (buttons.length + 1) / 2;
		panel.setLayout(new GridLayout(rows, 2, 5, 5));
		for (JButton button : buttons) {
			panel.add(button);
		}
		panel.add(Box.createHorizontalGlue());
		panel.setBorder(BorderFactory.createTitledBorder(title));
		return panel;
	}
}
