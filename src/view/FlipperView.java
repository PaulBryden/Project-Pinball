package view;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.IFlipper;
import model.IGizmo;
import observer.IObservable;
import observer.IObserver;

public class FlipperView implements IViewGizmo, IObserver{
	IFlipper gizmo;
	JPanel board;
	public FlipperView(JPanel board, IFlipper gizmo){
		this.gizmo=gizmo;
		this.board=board;
	}
	@Override
	public void update(Observable o, Object arg) {
		//update graphic
	}
	@Override
	public Graphics GetViewObject() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void update(IObservable obsv, Object o) {
		// TODO Auto-generated method stub
		
	}

}
