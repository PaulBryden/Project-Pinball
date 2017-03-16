package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
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
        
        setLayout(new GridBagLayout());
        
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

        gravitySlider.setMinimumSize(new Dimension(100,50));
        frictionSlider.setMinimumSize(new Dimension(100,50));
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;

        
        constraints.gridy = 0;
        add(gravityLabel, constraints);
        
        constraints.gridy = 1;
        gravitySlider.addChangeListener(new GravitySliderListener(model));
        add(gravitySlider, constraints);
        
        constraints.gridy = 2;
        add(frictionLabel, constraints);
        
        constraints.gridy = 3;
        frictionSlider.addChangeListener(new FrictionSliderListener(model));
        add(frictionSlider, constraints);
    }
}
