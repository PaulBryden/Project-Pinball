package controller;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.IModel;
import view.MainWindow;

public class frictionSliderListener implements ChangeListener {
	
	MainWindow mainWindow;

    public frictionSliderListener(MainWindow mainWindow){
        this.mainWindow = mainWindow;
    }
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		IModel model = mainWindow.getBoard().getModel();
		JSlider source = (JSlider)e.getSource();
		model.setFrictionMu(source.getValue()/1000.0);
		//System.out.println(source.getValue()/1000.0);
		model.setFrictionMu2(source.getValue()/1000.0);

	}

}
