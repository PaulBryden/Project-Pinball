package view;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.GizmoList;
import observer.IObservable;
import observer.IObserver;

public class Board extends JPanel implements IObserver{
	
	private List<IViewGizmo> viewGizmos;
	private GizmoList gizmos;

	public Board(GizmoList gizmos){
		super();
		this.viewGizmos = new ArrayList<>();
		this.gizmos = new GizmoList();
		//Generate View Elements and Store in List
	}

	public void reDrawAll(){
		revalidate();
		repaint();
	}

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

	@Override
	public void update(IObservable obsv, Object o) {
		// TODO Auto-generated method stub
		
	}



}
