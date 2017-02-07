package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import model.GizmoList;
import observer.IObservable;
import observer.IObserver;

public class Board extends JPanel implements IObserver{
	
	private List<IViewGizmo> viewGizmos;
	private GizmoList gizmos;

	//TODO: Generate View Elements and Store in List
	public Board(GizmoList gizmos){
		super();
		viewGizmos = new ArrayList<>();
		this.gizmos = gizmos;

		setLayout(new GridLayout(20, 20));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.BLACK)));
	}

    @Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i < 400; i++){
			add(new Cell(i % 2 == 0 ? Color.YELLOW : Color.BLUE));
		}
    }

	@Override
	public void update(IObservable obsv, Object o) {
		// TODO Auto-generated method stub
		
	}



}
