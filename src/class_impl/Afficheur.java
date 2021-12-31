package class_impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import implementation_interfaces.Observer;
import implementation_interfaces.ObserverDeCapteur;
import implementation_interfaces.Subject;

public class Afficheur implements ObserverDeCapteur {

	private String nameAfficheur;
	private Set<Integer> results;

	public Afficheur(String nameAfficheur, Subject canal) {
		this.nameAfficheur = nameAfficheur;
		results = new HashSet<Integer>();
		canal.attach(this);	
	}

	public String getNameAfficheur() {
		return nameAfficheur;
	}

	public Set<Integer> getResults(){
		return results;
	}

	@Override
	public Void update(Subject subject) {
		// TODO Auto-generated method stub
		Future<Integer> future = (Future<Integer>)subject.getValue(this);
		try {
			Integer result = future.get();
			this.results.add(result);

		} catch (ExecutionException e) {
			Logger.getGlobal().severe("Interrupted exception caught");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Logger.getGlobal().severe("Interrupted exception caught");
		}

		return null;
	}

	public void writeInFile(String strategy) {
		try {
			String pathFile = nameAfficheur + "_" + strategy + ".txt";
			FileWriter fileWriter = new FileWriter(pathFile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (Integer result : results) {
				Logger.getGlobal().info(" " + result);
				bufferedWriter.write(result.toString() + " ");
			}
			// Une la lecture de buffer fini, il faut fermer la connexion
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			Logger.getGlobal().severe("Interrupted exception caught");
		}
	}	
	
	public void afficherResults() {
		
	}
	
	@Override
	public String toString() {
		StringBuilder afficherResults = new StringBuilder("Je suis l'afficheur : " + getNameAfficheur());
		for(Integer value : results) {
			afficherResults.append(" " + value);
		}
		return afficherResults.toString();
	}

}
