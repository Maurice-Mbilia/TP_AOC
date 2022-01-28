package interfacesAsync;

public interface Observer<T>{
	/**
	 * 
	 * @param subject
	 * @return
	 */
	public T update(Subject subject);

	//void attach(Observer observer); 
}
