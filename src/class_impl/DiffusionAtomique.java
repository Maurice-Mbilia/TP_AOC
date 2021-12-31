package class_impl;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import implementation_interfaces.AlgoDiffusion;
import implementation_interfaces.Observer;

public class DiffusionAtomique implements AlgoDiffusion  {
	
	private Integer value;
	private CapteurImpl capteur;
	Set<Observer> semaphores;
	List<Observer> canaux;
	
	public DiffusionAtomique() {
		semaphores = new HashSet<>();
	}

	@Override
	public void configure(CapteurImpl capteur) {
		// TODO Auto-generated method stub
		this.capteur = capteur;
		this.canaux = this.capteur.getObservers();
		
	}
	
	@Override
	public void valueWritten() {
		
	}
	
	public void notifyAllObservers() {
		
		Observer observer = null;  
		for(int i = 0; i < canaux.size(); i++) {
		
			observer.update(this.capteur);
			Logger.getGlobal().log(Level.OFF, "Je vous informe que le canal" + i + " à été notifié");
		}
				
	}

	@Override
	public Integer valueRead(Observer observerCanal) {
		// TODO Auto-generated method stub
		Objects.requireNonNull(observerCanal, "Il ne faut pas que le calan soit nul");
		this.semaphores.add(observerCanal);
		
		if(this.canaux.size() == this.semaphores.size()) {
			this.capteur.unlock();
			semaphores.clear();
			Logger.getGlobal().log(Level.OFF, "Oups, l'affichage de la liste de differents canaux");
			
		}
				
		return value;
	}

}
