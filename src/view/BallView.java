package view;

import java.awt.*;
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

	public void paint(Graphics g) {
		g.fillOval((200 - (20/2)), (200 - (20/2)),20 , 20);
	}

	@Override
	public void update(IObservable obsv, Object o) {
		// TODO Auto-generated method stub
		
	}

}
