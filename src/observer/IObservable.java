package observer;

public interface IObservable {
	
	public void attach(IObserver obs);
	public void notifyAllObservers();
}
