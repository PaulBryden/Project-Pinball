package view;

import controller.AddAbsorberListener;
import controller.AddBallListener;
import controller.AddCircleListener;
import controller.AddFlipperListener;
import controller.AddLineListener;
import controller.AddSquareListener;
import controller.AddTriangleListener;

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
        ballBtn.addActionListener(new AddBallListener(board));
        add(ballBtn, constraints);

        constraints.gridy = 1;
        absorberBtn.addActionListener(new AddAbsorberListener(board));
        add(absorberBtn, constraints);

        constraints.gridy = 2;
        circleBtn.addActionListener(new AddCircleListener(board));
        add(circleBtn, constraints);

        constraints.gridy = 3;
        lineBtn.addActionListener(new AddLineListener(board));
        add(lineBtn, constraints);

        constraints.gridy = 4;
        squareBtn.addActionListener(new AddSquareListener(board));
        add(squareBtn, constraints);

        constraints.gridy = 5;
        triangleBtn.addActionListener(new AddTriangleListener(board));
        add(triangleBtn, constraints);

        constraints.gridy = 6;
        lFlipperBtn.addActionListener(new AddFlipperListener(board));
        add(lFlipperBtn, constraints);

        constraints.gridy = 7;
        add(rFlipperBtn, constraints);
    }
}
