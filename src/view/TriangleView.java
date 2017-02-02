package view;

import java.awt.Graphics;
import java.util.Observable;

import javax.swing.JPanel;

import model.IPolygon;
import observer.IObservable;
import observer.IObserver;

public class TriangleView implements IViewGizmo, IObserver{
	private IPolygon gizmo;
	private JPanel board;

	public TriangleView(JPanel board, IPolygon gizmo){
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
