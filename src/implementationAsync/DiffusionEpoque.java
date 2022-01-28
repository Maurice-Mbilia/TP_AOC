package implementationAsync;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import interfacesAsync.AlgoDiffusion;
import interfacesAsync.Observer;

public class DiffusionEpoque implements AlgoDiffusion {
	
	private CapteurImpl capteur;
	List<Observer> canals; 
	private Integer value = 0;
	
	public DiffusionEpoque() {
		
	}
	

	@Override
	public void configure(CapteurImpl capteur) {
		// TODO Auto-generated method stub
		this.capteur = capteur; 
		this.canals = this.capteur.getObservers();	
	}	
	@Override
	public void valueWritten() {
		// TODO Auto-generated method stub
		// la valeur s'incremente de 1
		value = value + 1; 
		
		// Notifications
		notifyAllObservers();		
	}
	@Override
	public Integer execute(Observer observer) {
		// TODO Auto-generated method stub
		return value;
	}
	private void notifyAllObservers() {
		// TODO Auto-generated method stub
		// Observer observer = null; 
		for(Observer obs : canals) { // We made with size of canals
			
			obs.update(this.capteur);
			Logger.getGlobal().info("  les canals " + obs + " sont notifiés ");
		}		
	}
}
