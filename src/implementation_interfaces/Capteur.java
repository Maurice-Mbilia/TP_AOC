package implementation_interfaces;

public interface Capteur extends Subject<Integer>{
	
	public void tick(); 
	
	public void attach(Observer observer); 
	
	public void detach(Observer observer); 
	
	public Integer getValue(Observer observer);
	
	public void lock(); 
	
	public boolean isLock(); // il n'y en a pas sur le diagramme proposé en cours

	void unlock();

	void loch();
	

}
