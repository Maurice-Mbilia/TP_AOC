package interfacesAsync;


public interface Subject<T> {
	
	public void attach(Observer observer);
	
	public void detack(Observer observer);
	
	public T getValue (Observer observer);

	void unlock(); 

}
