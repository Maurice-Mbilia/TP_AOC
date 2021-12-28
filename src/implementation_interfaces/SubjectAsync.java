package implementation_interfaces;

import java.util.concurrent.Future;

public interface SubjectAsync extends Subject<Future<Integer>>{
	
	// Subject c'est notre classe Update
	public Future<Integer> getValue(Observer observer); 
}
