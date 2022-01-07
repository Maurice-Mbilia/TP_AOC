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
	
	private Integer value = 0;
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
		
		 Logger.getGlobal().log(Level.OFF, "Je vous informe que je suis chez la stratégie");
	        if (!this.capteur.isLock()) {
	            value++;
	            Logger.getGlobal().log(Level.OFF, "Voici la valeur a diffuser aux afficheurs" + value);
	            this.capteur.lock();
	            Logger.getGlobal().log(Level.OFF, "Je vous informe que la stratégie a verrouiller le capteur");
	            this.notifyAllObservers();
	        }


		
	}
	
	public void notifyAllObservers() {
		
		int i = 0;  
		for(Observer observer: canaux) {
			
			i ++;
			observer.update(this.capteur);
			Logger.getGlobal().log(Level.OFF, "Je vous informe que le canal" + i + " à été notifié");
		}
				
	}

	@Override
	public Integer valueRead(Observer observerCanal) {
		// TODO Auto-generated method stub
		Objects.requireNonNull(observerCanal, "Il ne faut pas que le canal soit nul");
		this.semaphores.add(observerCanal);
		
		if(this.canaux.size() == this.semaphores.size()) {
			this.capteur.unlock();
			semaphores.clear();
			Logger.getGlobal().log(Level.OFF, "l'affichage de la liste de differents canaux");
			
		}
				
		return value;
	}

}
