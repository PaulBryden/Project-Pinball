package view;

import java.awt.Graphics;
import java.util.Observable;

import javax.swing.JPanel;

import model.ICircle;
import observer.IObservable;
import observer.IObserver;

public class BallView implements IViewGizmo, IObserver{
	private ICircle gizmo;
	private JPanel board;

	public BallView(JPanel board, ICircle gizmo){
		this.gizmo=gizmo;
		this.board=board;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		//UPDATE GRAPHICS OBJECT
		
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
