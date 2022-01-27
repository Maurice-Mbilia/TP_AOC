package class_impl_test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import class_impl.Afficheur;
import class_impl.Canal;
import class_impl.CapteurImpl;
import class_impl.Strategy;
import implementation_interfaces.AlgoDiffusion;

class DiffusionAtomiqueTest {
	
	public static CapteurImpl capteur = new CapteurImpl("capteur_A", Strategy.DiffusionAtomique);
	
	public static Canal canal_1 = new Canal("canal_A", capteur); 
	public static Afficheur afficheur_1 = new Afficheur("afficheur_A", canal_1);
	
	public static Canal canal_2 = new Canal("canal_B", capteur); 
	public static Afficheur afficheur_2 = new Afficheur("afficheur_B", canal_2);
	
	public static Canal canal_3 = new Canal("canal_C", capteur); 
	public static Afficheur afficheur_3 = new Afficheur("afficheur_C", canal_3);
	
	public static Canal canal_4 = new Canal("canal_D", capteur); 
	public static Afficheur afficheur_4 = new Afficheur("afficheur_D", canal_4);
	
	public static Canal canal_5 = new Canal("canal_E", capteur); 
	public static Afficheur afficheur_5 = new Afficheur("afficheur_E", canal_5);
	
	
	@Test
	void valueWritten() {
		
		int value = 1;
		capteur.tick();
		AlgoDiffusion strategy = capteur.getStrategy(); 
		Integer valueAfterTick = strategy.execute(canal_1);
		assertEquals(value, valueAfterTick);
	}
	@Test
	void lock() {
		assertTrue(capteur.isLock());
	}
	
	@Test
	void atomiqueTest() {
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		try { 
			scheduledExecutorService.scheduleAtFixedRate(capteur::tick,2, 10, TimeUnit.SECONDS);
			scheduledExecutorService.awaitTermination(120, TimeUnit.SECONDS); 
			
		} catch (Exception e) {
			Logger.getGlobal().info("Désolé, l'exécution de la tache ne s'est pas passée comme prévu" + e.getMessage());
		} finally {
			if (scheduledExecutorService != null) {
				scheduledExecutorService.shutdown();
			}
		}
		Logger.getGlobal().info("simulator is terminate ");
		
		Set<Integer> results1 = afficheur_1.getResults(); 
		afficheur_1.writeInFile("ATOMIQUE");
		
		Set<Integer> results2 = afficheur_2.getResults(); 
		afficheur_2.writeInFile("ATOMIQUE");
		
		Set<Integer> results3 = afficheur_3.getResults(); 
		afficheur_3.writeInFile("ATOMIQUE");
		
		Set<Integer> results4 = afficheur_4.getResults(); 
		afficheur_4.writeInFile("ATOMIQUE");
		
		Set<Integer> results5 = afficheur_5.getResults(); 
		afficheur_5.writeInFile("ATOMIQUE");
		
		assertTrue(results1.size()==results2.size() && results2.size()==results3.size() && results3.size()==results4.size() && results3.size()==results4.size(), "Les sous suites sont tous egaux");
		
		for(int i = 0; i < results1.size(); i++) {
			
			assertTrue(results1.toArray()[i] == results2.toArray()[i] && results1.toArray()[i] == results3.toArray()[i] && results1.toArray()[i] == results4.toArray()[i] && results1.toArray()[i] == results5.toArray()[i]);
			
			assertTrue(results1.toArray()[i] == results2.toArray()[i]);
			
		}
			
		Logger.getGlobal().info("	resultat of " + " " + afficheur_1.toString());
        Logger.getGlobal().info("	resultat of " + " " + afficheur_2.toString());
        Logger.getGlobal().info("	resultat of " + " " + afficheur_3.toString());
        Logger.getGlobal().info("	resultat of " + " " + afficheur_4.toString());
        Logger.getGlobal().info("	resultat of " + " " + afficheur_5.toString());	
	}

}