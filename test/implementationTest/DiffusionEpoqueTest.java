package implementationTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import implementationAsync.Afficheur;
import implementationAsync.Canal;
import implementationAsync.CapteurImpl;
import implementationAsync.Strategy;
import interfacesAsync.AlgoDiffusion;

class DiffusionEpoqueTest {
	
	public static CapteurImpl capteur = new CapteurImpl("capteur_A", Strategy.DiffusionEpoque);
	
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
		
		//int value = 1;
		capteur.tick();
		AlgoDiffusion strategy = capteur.getStrategy(); 
		Integer valueOfTick = strategy.execute(canal_1);
		assertNotNull(valueOfTick);
		
	}
	@Test
	void lock() {
		assertFalse(capteur.isLock(), "UNLOCKED");
	}
	
	@Test
	void executeEpoqueTest() {
		ScheduledExecutorService scheduledexecutor = Executors.newSingleThreadScheduledExecutor(); 
		try { 
			scheduledexecutor .scheduleAtFixedRate(capteur::tick, 1, 8, TimeUnit.SECONDS);
			scheduledexecutor .awaitTermination(180, TimeUnit.SECONDS); 
			
		} catch (Exception exception) {
			Logger.getGlobal().info(" The thread execution did not go well ");
		} finally {
			if (scheduledexecutor != null) {
				scheduledexecutor.shutdown();
			}
		}
		Logger.getGlobal().info(" End of test ");
		
		Set<Integer> results1 = display_1.getResults(); 
		display_1.writeValues("EPOQUE");
		assertFalse(results1.isEmpty());
		
		Set<Integer> results2 = display_2.getResults(); 
		display_2.writeValues("EPOQUE");
		assertFalse(results2.isEmpty());
		
		Set<Integer> results3 = display_3.getResults(); 
		display_3.writeValues("EPOQUE");
		assertFalse(results3.isEmpty());
		
		Set<Integer> results4 = display_4.getResults(); 
		display_4.writeValues("EPOQUE");
		assertFalse(results4.isEmpty());
		
		Set<Integer> results5 = display_5.getResults(); 
		display_5.writeValues("EPOQUE");
		assertFalse(results5.isEmpty());
		
		Logger.getGlobal().info("	result of " + " " + display_1.toString());
        Logger.getGlobal().info("	result of " + " " + display_2.toString());
        Logger.getGlobal().info("	result of " + " " + display_3.toString());
        Logger.getGlobal().info("	result of " + " " + display_4.toString());
        Logger.getGlobal().info("	result of " + " " + display_5.toString());	
	}

}
