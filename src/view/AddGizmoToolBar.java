package view;

import controller.*;

import javax.swing.JButton;
import javax.swing.JToolBar;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class AddGizmoToolBar extends JToolBar{
    public AddGizmoToolBar(Board board) {
        super("Place Gizmo Toolbar");
        JButton ballBtn = new JButton("Ball");
        JButton absorberBtn = new JButton("Absorber");
        JButton circleBtn = new JButton("Circle");
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
        ballBtn.addActionListener(new AddBallListener(board));
        add(ballBtn, constraints);

        constraints.gridy = 1;
        absorberBtn.addActionListener(new AddAbsorberListener(board));
        add(absorberBtn, constraints);

        constraints.gridy = 2;
        circleBtn.addActionListener(new AddCircleListener(board));
        add(circleBtn, constraints);

        constraints.gridy = 3;
        squareBtn.addActionListener(new AddSquareListener(board));
        add(squareBtn, constraints);

        constraints.gridy = 4;
        triangleBtn.addActionListener(new AddTriangleListener(board));
        add(triangleBtn, constraints);

        constraints.gridy = 5;
        lFlipperBtn.addActionListener(new AddLFlipperListener(board));
        add(lFlipperBtn, constraints);

        constraints.gridy = 6;
        rFlipperBtn.addActionListener(new AddRFlipperListener(board));
        add(rFlipperBtn, constraints);
    }
}
