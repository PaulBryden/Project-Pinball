package view;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class SidePanel extends JPanel {
	
	private static final long serialVersionUID = 8844919255973893688L;

	public SidePanel() {
		this.setPreferredSize(new Dimension(200, 200));
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
	}

}
