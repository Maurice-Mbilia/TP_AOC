package implementation_interfaces;

public interface Observer<T>{

	public T update(Subject subject);

	//void attach(Observer observer); 
}
