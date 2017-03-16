package controller;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.IModel;

public class FrictionSliderListener implements ChangeListener {
	
	IModel model;

    public FrictionSliderListener(IModel model){
    	this.model = model;
    }
	
	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		model.setFrictionMu(source.getValue()/1000.0);
		model.setFrictionMu2(source.getValue()/1000.0);

	}

}
