package class_impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import implementation_interfaces.AlgoDiffusion;
import implementation_interfaces.Observer;

public class DiffusionSequentielle implements AlgoDiffusion { 
	
	private Integer tampon; 
	private Integer value; 
	private CapteurImpl capteur;
	Set<Observer> semaphores; // je dois le changer en Observers c'est beaucoup mieux
	List<Observer> canals;
	
	public DiffusionSequentielle() {
		
		semaphores = new HashSet<>();
	}
	
	@Override
	public void configure(CapteurImpl capteur) {
		// TODO Auto-generated method stub
		this.capteur = capteur;
		this.canals = this.capteur.getObservers();		
	}

	@Override
	public Integer valueRead(Observer observer) {
		// TODO Auto-generated method stub
		this.semaphores.add(observer);
		Logger.getGlobal().log(Level.OFF, "My size");
		if(this.canals.size() == this.semaphores.size()) {
		semaphores.clear();
		Logger.getGlobal().log(Level.OFF, "Semaphore clear"+semaphores.size());
		}
		return tampon;
	}

	@Override
	public void valueWritten() {
		// TODO Auto-generated method stub
		Logger.getGlobal().log(Level.OFF, "Je vous informe que je suis chez la stratégie séquentielle");
		value ++;
		if(semaphores.isEmpty()) {
			tampon = value; 
			Logger.getGlobal().log(Level.OFF, "Voici la valeur a diffuser aux afficheurs" + tampon);
			this.notifyAllObeservers();
		}
		
		
	}

	private void notifyAllObeservers() {
		// TODO Auto-generated method stub
		Observer observer = null; 
		
		for(int i = 0; i < canals.size(); i++) { // ???? size of capteur ???
			observer.update(this.capteur);
			Logger.getGlobal().log(Level.OFF, "Je vous informe que le canal"+ i +" à été notifié");
		}
		
	}

}
