package class_impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import implementation_interfaces.Capteur;
import implementation_interfaces.Observer;
import implementation_interfaces.ObserverAsync;
import implementation_interfaces.Subject;
import implementation_interfaces.SubjectAsync;

public class Canal implements ObserverAsync, SubjectAsync  {
	
	private String canal_name; 
	private Capteur capteur;
	private final static int DelayMin = 1000;
	private final static int DelayMax = 3000; 
	private final static int POOL_NUMBER = 5; 
	private final ScheduledExecutorService executorservice;
	List<Observer<SubjectAsync>> afficheurObservers;
	
	public Canal(String nameCanal, Capteur capteur) {
		
		this.capteur = capteur;
		this.canal_name = nameCanal; 
		executorservice = Executors.newScheduledThreadPool(POOL_NUMBER);
		afficheurObservers = new ArrayList<>();
		capteur.attach(this);
				
		}	

	@Override
	public void detack(Observer observer) {
		// TODO Auto-generated method stub
		this.afficheurObservers.remove(observer);
		
	}

	@Override
	public Future<Integer> getValue(Observer observer) {
		// TODO Auto-generated method stub
		int randomTime = getRandomNumber();
		Observer observerCanal = this;
		
		return executorservice.schedule(new Callable<Integer>(){
		
			@Override
			public Integer call() {
				return capteur.getValue(observerCanal);
		}
			}, randomTime, TimeUnit.MILLISECONDS);
	}

	private int getRandomNumber() {
		// TODO Auto-generated method stub
		return (int) ((Math.random()*(DelayMax - DelayMin)) + DelayMin);
	}

	@Override
	public void unlock() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attach(Observer observer) {
		// TODO Auto-generated method stub
		this.afficheurObservers.add(observer);
		
	}

	@Override
	public Future<Void> update(Subject subject) { // classe avec un nom plutot que le lambda expression ou une classe anonyme
		// TODO Auto-generated method stub
		
		/*
		 * return (Future<Void>) executorservice.submit(() -> {
		 * afficheurObservers.update(subject); });
		 * 
		 * }
		 */
		
		int randomTime = getRandomNumber(); 
		SubjectAsync canal = this; 
		return executorservice.schedule(new Callable<Void>() {
			
			@Override
			public Void call() {
				for(Observer<SubjectAsync> print : afficheurObservers) {
					Logger.getGlobal().info(canal_name + " " + "Je vous informe que l'afficheur" + " " +  " " + print + " " + "est notifié");
					print.update(canal); 
				
				}
				
				return null;
			}
		}, randomTime, TimeUnit.MILLISECONDS);
		
				
	}

}
