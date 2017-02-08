package view;

import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.IGizmo;
import model.IPolygon;
import observer.IObservable;
import observer.IObserver;

public class SquareView implements IViewGizmo, IObserver{
	IPolygon gizmo;
	JPanel board;
	public SquareView(JPanel board, IPolygon gizmo){
		this.gizmo=gizmo;
		this.board=board;
	}

	@Override
	public void update(Observable o, Object arg) {
	
		
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
