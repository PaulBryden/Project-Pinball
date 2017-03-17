package view;

import controller.*;

import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class AddGizmoSidePanel extends SidePanel {
	
	private static final long serialVersionUID = 5655448783959185202L;

	public AddGizmoSidePanel(MainWindow mainWindow) {
        super();
        JButton ballBtn = new JButton("Ball");
        JButton absorberBtn = new JButton("Absorber");
        JButton circleBtn = new JButton("Circle");
        JButton squareBtn = new JButton("Square");
        JButton triangleBtn = new JButton("Triangle");
        JButton lFlipperBtn = new JButton("L.Flipper");
        JButton rFlipperBtn = new JButton("R.Flipper");
        JButton CWSpinnerBtn = new JButton("CW.Spinner");
        JButton CCWSpinnerBtn = new JButton("CCW.Spinner");
        
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;

        constraints.gridy = 0;
        ballBtn.addActionListener(new AddBallListener(mainWindow));
        add(ballBtn, constraints);

        constraints.gridy = 1;
        absorberBtn.addActionListener(new AddAbsorberListener(mainWindow));
        add(absorberBtn, constraints);

        constraints.gridy = 2;
        circleBtn.addActionListener(new AddCircleListener(mainWindow));
        add(circleBtn, constraints);

        constraints.gridy = 3;
        squareBtn.addActionListener(new AddSquareListener(mainWindow));
        add(squareBtn, constraints);

        constraints.gridy = 4;
        triangleBtn.addActionListener(new AddTriangleListener(mainWindow));
        add(triangleBtn, constraints);

        constraints.gridy = 5;
        lFlipperBtn.addActionListener(new AddLFlipperListener(mainWindow));
        add(lFlipperBtn, constraints);

        constraints.gridy = 6;
        rFlipperBtn.addActionListener(new AddRFlipperListener(mainWindow));
        add(rFlipperBtn, constraints);
        
        constraints.gridy = 7;
   //     CWSpinnerBtn.addActionListener(new AddCWSpinnerListener(mainWindow));
        add(CWSpinnerBtn, constraints);
        
        constraints.gridy = 8;
    //    CCWSpinnerBtn.addActionListener(new AddCCWSpinnerListener(mainWindow));
        add(CWSpinnerBtn, constraints);
        
    }
}
