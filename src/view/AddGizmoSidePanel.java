
package view;

import controller.*;

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
		JButton counterBtn = ButtonFactory.createButton("counter", "Place a counter gizmo",
				new AddCounterListener(mainWindow));
		JButton CWSpinnerBtn = ButtonFactory.createButton("cw_spinner", "Place a Clockwise Spinner",
				new AddRFlipperListener(mainWindow));

		JPanel ballsPanel = createTitledPanel("Balls", 2, ballBtn);
		JPanel staticPanel = createTitledPanel("Static gizmos", 2, circleBtn, squareBtn, triangleBtn);
		JPanel dynamicPanel = createTitledPanel("Dynamic gizmos", 2, lFlipperBtn, rFlipperBtn, absorberBtn, counterBtn);
		build(ballsPanel, staticPanel, dynamicPanel);
	}

}
