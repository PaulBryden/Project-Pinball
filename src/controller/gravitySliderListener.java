package controller;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.MainWindow;

public class gravitySliderListener implements ChangeListener {
	
	MainWindow mainWindow;

    public gravitySliderListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		

	}

}
