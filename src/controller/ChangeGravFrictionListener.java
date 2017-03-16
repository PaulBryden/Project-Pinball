package controller;

import view.FrictionGravityToolbar;
import view.MainWindow;

import javax.swing.JToolBar;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static view.STATE.*;

public class ChangeGravFrictionListener implements ActionListener{
	private MainWindow mainWindow;

	public ChangeGravFrictionListener(MainWindow mainWindow){
		this.mainWindow = mainWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JToolBar tb = new FrictionGravityToolbar(mainWindow);
		tb.setPreferredSize(new Dimension(5,2000));
		mainWindow.addSideToolBar(tb);
		mainWindow.getBoard().setState(BUILD);
		mainWindow.setStatusLabel("Adjust Sliders to change gravity and friction.");
	}

}
