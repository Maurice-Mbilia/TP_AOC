package interfacesAsync;


public interface Subject<T> {
	
	/**
	 * 
	 * @param observer
	 */
	public void attach(Observer observer);
	
	/**
	 * 
	 * @param observer
	 */
	public void detack(Observer observer);
	
	/**
	 * 
	 * @param observer
	 * @return
	 */
	public T getValue (Observer observer);
	/**
	 * 
	 */
	void unlock(); 

}
