package model;

import java.util.ArrayList;
import java.util.List;

import observer.IObservable;
import observer.IObserver;

public class GizmoList implements IObservable{
	private ArrayList<IGizmo> gizmoList= new ArrayList<>();
	public List<IObserver> observers = new ArrayList<>();

	public ArrayList<IGizmo>  returnGizmoList(){
		return gizmoList;
	}
	public void addGizmo(IGizmo gizmo){
		notifyAllObservers();
	}
	public void removeGizmo(IGizmo gizmo){
		notifyAllObservers();
	}
	@Override
	public void attach(IObserver obs) {
		observers.add(obs);
	}
	@Override
	public void notifyAllObservers() {
		for(IObserver o : observers)
		{
			o.update(this, null);
		}		
	}
}
