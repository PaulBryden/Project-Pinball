package view;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Observable;

import javax.swing.JPanel;

import model.IFlipper;
import observer.IObservable;
import observer.IObserver;

public class FlipperView implements IViewGizmo, IObserver{
	private IFlipper gizmo;
	private JPanel board;

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

	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;

		g2D.fill(new RoundRectangle2D.Float(300, 300, 40, 10, 10, 10));
	}

	@Override
	public void update(IObservable obsv, Object o) {
		// TODO Auto-generated method stub
		
	}

}
