package interfacesAsync;

import java.util.concurrent.Future;

public interface ObserverAsync extends Observer<Future<Void>> {
	
	public Future<Void> update(Subject subject);   // subect joue le role de capteur 

}
