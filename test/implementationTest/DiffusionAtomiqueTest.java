package implementationTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import implementationAsync.Afficheur;
import implementationAsync.Canal;
import implementationAsync.CapteurImpl;
import implementationAsync.Strategy;
import interfacesAsync.AlgoDiffusion;

class DiffusionAtomiqueTest {
	
	public static CapteurImpl capteur = new CapteurImpl("capteur_A", Strategy.DiffusionAtomique);
	
	public static Canal canal_1 = new Canal("canal_A", capteur); 
	public static Afficheur display_1 = new Afficheur("display_A", canal_1);
	
	public static Canal canal_2 = new Canal("canal_B", capteur); 
	public static Afficheur display_2 = new Afficheur("display_B", canal_2);
	
	public static Canal canal_3 = new Canal("canal_C", capteur); 
	public static Afficheur display_3 = new Afficheur("display_C", canal_3);
	
	public static Canal canal_4 = new Canal("canal_D", capteur); 
	public static Afficheur display_4 = new Afficheur("display_D", canal_4);
	
	public static Canal canal_5 = new Canal("canal_E", capteur); 
	public static Afficheur display_5 = new Afficheur("display_E", canal_5);
	
	
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
			Logger.getGlobal().info("Sorry, the task execution did not go well" + e.getMessage());
		} finally {
			if (scheduledExecutorService != null) {
				scheduledExecutorService.shutdown();
			}
		}
		Logger.getGlobal().info("simulator is done ");
		
		Set<Integer> results1 = display_1.getResults(); 
		display_1.writeValues("ATOMIQUE");
		
		Set<Integer> results2 = display_2.getResults(); 
		display_2.writeValues("ATOMIQUE");
		
		Set<Integer> results3 = display_3.getResults(); 
		display_3.writeValues("ATOMIQUE");
		
		Set<Integer> results4 = display_4.getResults(); 
		display_4.writeValues("ATOMIQUE");
		
		Set<Integer> results5 = display_5.getResults(); 
		display_5.writeValues("ATOMIQUE");
		
		assertTrue(results1.size()==results2.size() && results2.size()==results3.size() && results3.size()==results4.size() && results3.size()==results4.size());
		
		for(int i = 0; i < results1.size(); i++) {			
			assertTrue((results1.toArray()[i] == results2.toArray()[i]) && (results1.toArray()[i] == results3.toArray()[i]) && (results1.toArray()[i] == results4.toArray()[i]) && (results1.toArray()[i] == results5.toArray()[i]));			
			assertTrue(results1.toArray()[i] == results2.toArray()[i]);
			
		}			
		Logger.getGlobal().info("	result of " + " " + display_1.toString());
        Logger.getGlobal().info("	result of " + " " + display_2.toString());
        Logger.getGlobal().info("	result of " + " " + display_3.toString());
        Logger.getGlobal().info("	result of " + " " + display_4.toString());
        Logger.getGlobal().info("	result of " + " " + display_5.toString());	
	}
}