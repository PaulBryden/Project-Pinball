package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AbstractToolBar;

public class ToolBarListener implements ActionListener {
	
	private AbstractToolBar toolBar;
	
	public ToolBarListener(AbstractToolBar toolBar) {
		this.toolBar = toolBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "run":
			toolBar.enableButton("pause");
			toolBar.disableButton("run");
			break;
		case "pause":
			toolBar.enableButton("run");
			toolBar.disableButton("pause");
			break;
		default:
			System.out.println(e.getActionCommand());
		}
	}

}
