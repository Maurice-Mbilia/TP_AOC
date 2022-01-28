package interfacesAsync;

/**
 * @author etops mbilia
 *
 */
public interface Capteur extends Subject<Integer>{
	
	public void tick(); 
	
	public void attach(Observer observer); 
	
	public void detach(Observer observer); 
	
	public Integer getValue(Observer observer);
	
	public void lock(); 
	
	// il n'y en a pas sur le diagramme proposé en cours
	public boolean isLock();

	void unlock();
}
