package implementation_interfaces;

public interface Cateur extends Subject<Integer>{
	
	public void attack(Observer observer); 
	
	public void detach(Observer observer); 
	
	public Integer getValue(Observer observer);

}
