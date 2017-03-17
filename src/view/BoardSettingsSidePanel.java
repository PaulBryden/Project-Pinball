package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import controller.FrictionSliderListener;
import controller.GravitySliderListener;
import model.IModel;

public class BoardSettingsSidePanel extends SidePanel {

	private static final long serialVersionUID = 694148402161787020L;

	public BoardSettingsSidePanel(IModel model) {
        super();

        JSlider gravitySlider = new JSlider(JSlider.HORIZONTAL,-50,50, (int)Math.round(model.getGravity()));
        JSlider frictionSlider = new JSlider(JSlider.HORIZONTAL,0,100, (int)Math.round(model.getFrictionMu()*1000));
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

        gravitySlider.setMinimumSize(new Dimension(160,50));
        frictionSlider.setMinimumSize(new Dimension(160,50));
        
        gravitySlider.addChangeListener(new GravitySliderListener(model));
        frictionSlider.addChangeListener(new FrictionSliderListener(model));
        
        this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.weightx = 1;
		c.insets = new Insets(5, 3, 0, 3);
		c.fill = GridBagConstraints.HORIZONTAL;
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
		add(createTitledPanel("Physics", 1, gravityPanel, frictionPanel), c);
		c.gridy = 2;
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1;
		add(Box.createVerticalGlue(), c);
    }
}
