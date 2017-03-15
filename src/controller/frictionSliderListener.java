package controller;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import view.MainWindow;

public class frictionSliderListener implements ChangeListener {
	
	MainWindow mainWindow;

    public frictionSliderListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		

	}

}
