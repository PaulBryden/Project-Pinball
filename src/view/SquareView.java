package view;

import java.awt.*;
import java.util.Observable;

import javax.swing.*;

import model.IPolygon;
import observer.IObservable;
import observer.IObserver;

public class SquareView implements IViewGizmo, IObserver {

	private IPolygon gizmo;
	private JPanel board;

	public SquareView(JPanel board, IPolygon gizmo){
		this.gizmo=gizmo;
		this.board=board;
	}

	public void paint(Graphics g){
		g.drawRect(100,200,20,20);
		g.setColor(Color.BLUE);
		g.fillRect(100,200,20,20);
	}

	@Override
	public Graphics GetViewObject() {
		return null;
	}

	@Override
	public void update(IObservable obsv, Object o) {

	}

	@Override
	public void update(Observable o, Object arg) {

	}
}
