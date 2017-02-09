package view;

import java.awt.*;
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
		setSize(new Dimension(400, 400));
		setPreferredSize(getSize());
		setMinimumSize(getSize());
		setMaximumSize(getSize());
	}

	public void addViewGizmo(IViewGizmo gizmo){
		viewGizmos.add(gizmo);
		revalidate();
		repaint();
	}

	public void removeViewGizmo(IViewGizmo gizmo){
		viewGizmos.remove(gizmo);
		revalidate();
		repaint();
	}

    @Override
    public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(IViewGizmo viewGizmo : viewGizmos){
			viewGizmo.paint(g);
		}
    }

	@Override
	public void update(IObservable obsv, Object o) {
	}



}
