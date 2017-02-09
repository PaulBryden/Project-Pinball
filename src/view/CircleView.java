package view;

import java.awt.Graphics;
import java.util.Observable;

import model.ICircle;
import observer.IObservable;
import observer.IObserver;

public class CircleView implements IViewGizmo, IObserver{
	private ICircle gizmo;

	public CircleView(ICircle gizmo){
		this.gizmo = gizmo;
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

	public void paint(Graphics g) {
		g.fillOval((200 - (20/2)), (200 - (20/2)),20 , 20);
	}

	@Override
	public void update(IObservable obsv, Object o) {
		// TODO Auto-generated method stub
	}

}
