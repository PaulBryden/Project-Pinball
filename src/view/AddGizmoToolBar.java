package view;

import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class AddGizmoToolBar extends JToolBar{
    public AddGizmoToolBar() {
        super("Place Gizmo Toolbar");
        JButton ballBtn = new JButton("Ball");
        JButton absorberBtn = new JButton("Absorber");
        JButton circleBtn = new JButton("Circle");
        JButton lineBtn = new JButton("Line");
        JButton squareBtn = new JButton("Square");
        JButton triangleBtn = new JButton("Triangle");
        JButton lFlipperBtn = new JButton("L.Flipper");
        JButton rFlipperBtn = new JButton("R.Flipper");

        setLayout(new GridBagLayout());
        setFloatable(false);
        setRollover(true);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;

        constraints.gridy = 0;
        add(ballBtn, constraints);

        constraints.gridy = 1;
        add(absorberBtn, constraints);

        constraints.gridy = 2;
        add(circleBtn, constraints);


        constraints.gridy = 3;
        add(lineBtn, constraints);

        constraints.gridy = 4;
        add(squareBtn, constraints);

        constraints.gridy = 5;
        add(triangleBtn, constraints);

        constraints.gridy = 6;
        add(lFlipperBtn, constraints);

        constraints.gridy = 7;
        add(rFlipperBtn, constraints);
    }
}
