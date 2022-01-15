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
	private Integer value = 0; 
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
	public void valueWritten() {

		// TODO Auto-generated method stub
		Logger.getGlobal().info( "	Pas de confusion je suis une stratégie séquentielle ");
		value ++;
		if(semaphores.isEmpty()) {
			tampon = value; 
			Logger.getGlobal().info( "Voici la valeur a diffuser aux afficheurs " + tampon);
			this.notifyAllObeservers();
		}


	}

	@Override
	public Integer execute(Observer observer) {


		// TODO Auto-generated method stub
		this.semaphores.add(observer);
		Logger.getGlobal().info(" Ma taille est : ");
		if(this.canals.size() == this.semaphores.size()) {
			semaphores.clear();
			Logger.getGlobal().log(Level.OFF, "Semaphore clear "+semaphores.size());
		}
		return tampon;
	}

	public void notifyAllObeservers() {
		// TODO Auto-generated method stub

		int i = 0 ;
		for (Observer observer : canals) {
			i++ ;
			observer.update(this.capteur);
			Logger.getGlobal().info("	les canals	" + i + "	sont notifiés	");
		}

	}
}


