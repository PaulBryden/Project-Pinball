package view;

import controller.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.JButton;

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
		c.insets = new Insets(5, 3, 0, 3);
		c.fill = GridBagConstraints.HORIZONTAL;
		add(createTitledPanel("Balls", 2, ballBtn), c);
		c.gridy = 1;
		add(createTitledPanel("Static gizmos", 2, circleBtn, squareBtn, triangleBtn), c);
		c.gridy = 2;
		add(createTitledPanel("Dynamic gizmos", 2, lFlipperBtn, rFlipperBtn, absorberBtn), c);
		c.gridy = 3;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		add(Box.createVerticalGlue(), c);
	}

}
