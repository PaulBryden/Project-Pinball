package view;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.GizmoList;

public class Board extends JPanel implements Observer{
	
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
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}



}
