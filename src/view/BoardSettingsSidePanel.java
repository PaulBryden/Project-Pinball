
package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import controller.FrictionSliderListener;
import controller.GravitySliderListener;
import controller.PrimaryActionListener;
import model.IModel;

public class BoardSettingsSidePanel extends SidePanel {

	private static final long serialVersionUID = 694148402161787020L;

	public BoardSettingsSidePanel(MainWindow mainWindow) {
		super();

		IModel model = mainWindow.getModel();
		PrimaryActionListener listener = mainWindow.getActionListener();
		
		JSlider gravitySlider = new JSlider(JSlider.HORIZONTAL, -50, 50, (int) Math.round(model.getGravity()));
		JSlider frictionSlider = new JSlider(JSlider.HORIZONTAL, 0, 100,
				(int) Math.round(model.getFrictionMu() * 1000));
		JLabel gravityLabel = new JLabel("Gravity", JLabel.CENTER);
		JLabel frictionLabel = new JLabel("Friction", JLabel.CENTER);

		gravitySlider.setPaintLabels(true);
		frictionSlider.setPaintLabels(true);
		gravitySlider.setPaintTicks(true);
		frictionSlider.setPaintTicks(true);

		gravitySlider.setMajorTickSpacing(25);
		gravitySlider.setMinorTickSpacing(5);
		frictionSlider.setMajorTickSpacing(25);
		frictionSlider.setMinorTickSpacing(5);

		gravitySlider.setMinimumSize(new Dimension(160, 50));
		frictionSlider.setMinimumSize(new Dimension(160, 50));

		gravitySlider.addChangeListener(new GravitySliderListener(model));
		frictionSlider.addChangeListener(new FrictionSliderListener(model));

		JPanel gravityPanel = new JPanel();
		gravityPanel.setLayout(new BoxLayout(gravityPanel, BoxLayout.PAGE_AXIS));
		gravityLabel.setAlignmentX(CENTER_ALIGNMENT);
		gravityPanel.add(gravityLabel);
		gravityPanel.add(gravitySlider);
		JPanel frictionPanel = new JPanel();
		frictionPanel.setLayout(new BoxLayout(frictionPanel, BoxLayout.PAGE_AXIS));
		frictionLabel.setAlignmentX(CENTER_ALIGNMENT);
		frictionPanel.add(frictionLabel);
		frictionPanel.add(frictionSlider);
		
		JPanel physicsPanel = createTitledPanel("Physics", 1, gravityPanel, frictionPanel);
		
		JPanel bgPanel = new JPanel();
		bgPanel.setLayout(new BorderLayout(5, 5));
		JButton bgButton = ButtonFactory.createColourButton(model.getBackgroundColour(), "background", "Choose a background colour", listener);
		bgPanel.add(bgButton, BorderLayout.WEST);
		bgPanel.add(new JLabel("Background"), BorderLayout.CENTER);
		
		JPanel textColourPanel = new JPanel();
		textColourPanel.setLayout(new BorderLayout(5, 5));
		JButton textColourButton = ButtonFactory.createColourButton(model.getTextColour(), "text_colour", "Choose a text colour", listener);
		textColourPanel.add(textColourButton, BorderLayout.WEST);
		textColourPanel.add(new JLabel("Text"), BorderLayout.CENTER);
		
		JPanel gridColourPanel = new JPanel();
		gridColourPanel.setLayout(new BorderLayout(5, 5));
		JButton gridColourButton = ButtonFactory.createColourButton(mainWindow.getBoard().getGridColour(), "grid_colour", "Choose a grid colour", listener);
		gridColourPanel.add(gridColourButton, BorderLayout.WEST);
		gridColourPanel.add(new JLabel("Grid"), BorderLayout.CENTER);
		
		JPanel colourPanel = createTitledPanel("Colours", 1, bgPanel, textColourPanel, gridColourPanel);
		
		build("Use the sliders to adjust the physical constants of this board.", physicsPanel, colourPanel);
	}

}
