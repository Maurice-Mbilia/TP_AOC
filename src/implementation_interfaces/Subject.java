package implementation_interfaces;


public interface Subject<T> {
	
	public void attack(Observer observer);
	
	public void detack(Observer observer);
	
	public T getValue (Observer observer); 

}
