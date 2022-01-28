package implementationAsync;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import interfacesAsync.Observer;
import interfacesAsync.ObserverDeCapteur;
import interfacesAsync.Subject;

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
		Future<Integer> future = (Future<Integer>)subject.getValue(this); // cast future
		try {
			Integer result = future.get();
			this.results.add(result);

		} catch (ExecutionException e) {
			Logger.getGlobal().info(" Interrupted exception caught ");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			Logger.getGlobal().info(" Interrupted exception caught ");
		}

		return null;
	}

	public void writeInFile(String strategy) {
		try {
			String pathFile = nameAfficheur + " " + strategy + ".txt";
			FileWriter fileWriter = new FileWriter(pathFile);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

			for (Integer result : results) {
				Logger.getGlobal().info(" " + result);
				bufferedWriter.write(result.toString() + " ");
			}
			// Une fois que la lecture de buffer finie, il faut fermer la connexion
			bufferedWriter.close();
			fileWriter.close();
		} catch (IOException e) {
			Logger.getGlobal().info("Interrupted exception caught");
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
