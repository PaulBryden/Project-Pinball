package view;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.GizmoList;
import observer.IObservable;
import observer.IObserver;

public class Board extends JPanel implements IObserver{
	
	ArrayList<IViewGizmo> viewGizmos;
	GizmoList gizmos;
	public Board(GizmoList gizmos){
		//Generate View Elements and Store in List
	}
	public void reDrawAll(){
		
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
