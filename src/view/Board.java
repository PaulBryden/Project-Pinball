package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import model.IModel;

public class Board extends JPanel implements Observer {
	
	private IModel model;
	private List<IViewGizmo> viewGizmos;

	//TODO: Generate View Elements and Store in List
	public Board(IModel model) {
		super();
		this.model = model;
		model.addObserver(this);
		viewGizmos = new ArrayList<>();

		setLayout(new GridLayout(20, 20));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.BLACK, Color.BLACK)));
		setSize(new Dimension(400, 400));
		setPreferredSize(getSize());
		setMinimumSize(getSize());
		setMaximumSize(getSize());
	}
	public IModel getModel(){
		return (model);
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
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}
