package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.IGizmo;

public class BallView implements IViewGizmo, Observer{
	IGizmo gizmo;
	JPanel board;
	public BallView(JPanel board, IGizmo gizmo){
		this.gizmo=gizmo;
		this.board=board;
	}
	@Override
	public void paint(JPanel j) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(Observable o, Object arg) {
		paint(board);
		
	}

}
