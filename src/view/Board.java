package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

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
		setLayout(new GridBagLayout());

		reDrawAll();
	}

	private void reDrawAll(){
		int count = 0;
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;

		for(int i = 0; i < 20; i++){
			constraints.gridy = i;
			for(int j = 0; j < 20; j++){
				constraints.gridx = j;
				add(new JButton("" + count), constraints);
				count++;
			}
		}

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
