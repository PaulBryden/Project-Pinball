package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class StatusBar extends JPanel {
	
	private static final long serialVersionUID = 6610405078973935944L;
	private static final Color STATUS_COLOUR = Color.BLUE;
	private static final Color WARNING_COLOUR = Color.RED;
	private JLabel text;
	
	public StatusBar() {
		this.text = new JLabel("");
		this.setPreferredSize(new Dimension(23, 23));
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		this.setLayout(new BorderLayout());
		this.add(text, BorderLayout.WEST);
		text.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
	}
	
	public void setStatus(String status) {
		text.setForeground(STATUS_COLOUR);
		text.setText(status);
	}
	
	public void setWarning(String status) {
		text.setForeground(WARNING_COLOUR);
		text.setText(status);
	}

}
