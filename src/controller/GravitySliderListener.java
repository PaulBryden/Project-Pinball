package controller;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.IModel;
import view.MainWindow;

public class GravitySliderListener implements ChangeListener {
	
	IModel model;

    public GravitySliderListener(IModel model){
        this.model = model;
    }
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		model.setGravity(source.getValue());
	}

}
