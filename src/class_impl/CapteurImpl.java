package class_impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import implementation_interfaces.AlgoDiffusion;
import implementation_interfaces.Capteur;
import implementation_interfaces.Observer;

public class CapteurImpl implements Capteur  {
	
	private String nameCapteur; 
	private AlgoDiffusion strategy; 
	private List<Observer> observers;
	private boolean lock;
	
	public CapteurImpl(String nameCapteur, Strategy strategy) {
		
		this.lock = false;
		this.nameCapteur = nameCapteur; 
		observers = new ArrayList<>();
		switch(strategy) {
		case DiffusionAtomique : this.setStrategy(new DiffusionAtomique()); break;
		case DiffusionSequentielle : this.setStrategy(new DiffusionSequentielle()); break;
		case DiffusionEpoque : this.setStrategy(new DiffusionEpoque()); break;
		}
		
		
	}
	
	public void setStrategy(AlgoDiffusion strategy) {
		this.strategy = strategy; 
		this.strategy.configure(this);
	}
	
	
	public AlgoDiffusion getStrategy() {
		return this.strategy;
	}

	@Override
	public void detack(Observer observer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		Logger.getGlobal().severe("Je vous informe que je suis le capteur  j'ai effectué un tick et je delegue le travail à la strategie");
		this.strategy.valueWritten();

		
	}

	@Override
	public void attach(Observer observer) {
		// TODO Auto-generated method stub
		this.observers.add(observer);
		
	}

	@Override
	public void detach(Observer observer) {
		// TODO Auto-generated method stub
		this.observers.remove(observer);
		
		
	}

	@Override
	public Integer getValue(Observer observer) {
		// TODO Auto-generated method stub
		return this.strategy.valueRead(observer);
	}

	@Override
	public void loch() {
		// TODO Auto-generated method stub
		lock = true;
		Logger.getGlobal().severe("Je suis bloqué");		
	}
	
	@Override
	public void unlock() {
		lock = false ;
		Logger.getGlobal().severe("Je vous informe que je suis le capteur et je suis débloqué");
	}
	


	@Override
	public boolean isLock() {
		// TODO Auto-generated method stub
		return lock;
	}

	@Override
	public void lock() {
		// TODO Auto-generated method stub
		lock = true ;
		Logger.getGlobal().severe("Je vous informe que je suis le capteur et je suis bloqué");

		
	}

	public List<Observer> getObservers() {
		// TODO Auto-generated method stub
		return Collections.unmodifiableList(observers);
	}

}