package class_impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import implementation_interfaces.AlgoDiffusion;
import implementation_interfaces.Observer;

public class DiffusionEpoque implements AlgoDiffusion {
	
	private Integer value;
	private CapteurImpl capteur;
	List<Observer> canals; 
	
	public DiffusionEpoque() {
		
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
		return value;
	}

	private void notifyAllObservers() {
		// TODO Auto-generated method stub
		Observer observer = null; 
		
		for(int i = 0; i < canals.size(); i++) { // ???? size of capteur ???
			observer.update(this.capteur);
			Logger.getGlobal().log(Level.OFF, "Je vous informe que le canal"+ i +" à été notifié");
		}		
	}

	@Override
	public void valueWritten() {
		// TODO Auto-generated method stub
		value ++; 
		notifyAllObservers();
		
	}
}
