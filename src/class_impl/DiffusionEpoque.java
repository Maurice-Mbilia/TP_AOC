package class_impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import implementation_interfaces.AlgoDiffusion;
import implementation_interfaces.Observer;

public class DiffusionEpoque implements AlgoDiffusion {
	
	private CapteurImpl capteur;
	List<Observer> canals; 
	private Integer value;
	
	public DiffusionEpoque() {
		
	}
	

	@Override
	public void configure(CapteurImpl capteur) {
		// TODO Auto-generated method stub
		this.capteur = capteur; 
		this.canals = this.capteur.getObservers();	
	}

	@Override
	public Integer execute(Observer observer) {
		// TODO Auto-generated method stub
		return value;
	}

	private void notifyAllObservers() {
		// TODO Auto-generated method stub
		Observer observer = null; 
		
		for(int i = 0; i < canals.size(); i++) { // ???? size of capteur ???
			observer.update(this.capteur);
			Logger.getGlobal().info("  les canals " + i + " sont notifiés ");
		}		
	}

	@Override
	public void valueWritten() {
		// TODO Auto-generated method stub
		value ++; 
		notifyAllObservers();
		
	}
}
