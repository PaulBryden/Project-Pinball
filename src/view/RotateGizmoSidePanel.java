package view;

import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class RotateGizmoSidePanel extends SidePanel {
	
	private static final long serialVersionUID = 6039203273405132745L;

	public RotateGizmoSidePanel(){
        super();
        JButton leftBtn = new JButton("Left");
        JButton rightBtn = new JButton("Right");
        JButton oneEightyBtn = new JButton("180");

        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;

        constraints.gridy = 0;
        add(leftBtn, constraints);

        constraints.gridy = 1;
        add(rightBtn, constraints);

        constraints.gridy = 2;
        add(oneEightyBtn, constraints);
    }
}
