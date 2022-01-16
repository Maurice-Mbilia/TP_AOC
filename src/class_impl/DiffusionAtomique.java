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
	Set<Observer> observers;
	List<Observer> canals;
	
	public DiffusionAtomique() {
		observers = new HashSet<>();
	}

	
	@Override
	public void configure(CapteurImpl capteur) {
		// TODO Auto-generated method stub
		this.capteur = capteur;
		this.canals = this.capteur.getObservers();
		
	}
	
	@Override
	public void valueWritten() {
		
		 Logger.getGlobal().info(" Je vous informe que je suis chez la stratégie ");
	        if (!this.capteur.isLock()) {
	            value++;
	            Logger.getGlobal().info(" la valeur de diffusion est : " + value);
	            this.capteur.lock();
	            Logger.getGlobal().info(" Le capteur est bloqué pour l'instant ");
	            this.notifyAllObeservers();
	        }
	
	}
	
	/*
	 * @Override public void notifyAll() {
	 * 
	 * int i = 0; for(Observer observer: canal) {
	 * 
	 * i ++; observer.update(this.capteur); }
	 * 
	 * }
	 */

	@Override
	public Integer execute(Observer observerCanal) {
		// TODO Auto-generated method stub
		Objects.requireNonNull(observerCanal, " le canal ne doit pas être nul");
		this.observers.add(observerCanal);
		
		if(this.canals.size() == this.observers.size()) {
			this.capteur.unlock();
			observers.clear();
			Logger.getGlobal().info(" les valeurs qui s'affichent sont : ");
			
		}
		
		return value;
	}


	public void notifyAllObeservers() {
		// TODO Auto-generated method stub
		
		int i = 0;  
		for(Observer observer: canals) {
			
			i ++;
			observer.update(this.capteur);
			Logger.getGlobal().info(" Je vous informe que le canal " + i + " sont notifiés ");
		}
		
	}

}
