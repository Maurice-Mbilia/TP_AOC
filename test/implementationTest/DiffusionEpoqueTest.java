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
	
	 //@BeforeEach
	
	public static CapteurImpl capteur = new CapteurImpl("capteur_A", Strategy.DiffusionEpoque);
	
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
			scheduledexecutor.scheduleAtFixedRate(capteur::tick, 2, 4, TimeUnit.SECONDS);
			
			//scheduledexecutor.schedule(capteur::tick, 10, TimeUnit.SECONDS);
			scheduledexecutor.awaitTermination(120, TimeUnit.SECONDS); 
			
		} catch (Exception exception) {
			Logger.getGlobal().info(" Sorry! the thread execution is not good ");
		} finally {
			if (scheduledexecutor != null) {
				scheduledexecutor.shutdown();
			}
		}
		Logger.getGlobal().info(" End of test ");
		
		Set<Integer> results1 = afficheur_1.getResults(); 
		afficheur_1.writeInFile("EPOQUE");
		assertFalse(results1.isEmpty());
		
		Set<Integer> results2 = afficheur_2.getResults(); 
		afficheur_2.writeInFile("EPOQUE");
		assertFalse(results2.isEmpty());
		
		Set<Integer> results3 = afficheur_3.getResults(); 
		afficheur_3.writeInFile("EPOQUE");
		assertFalse(results3.isEmpty());
		
		Set<Integer> results4 = afficheur_4.getResults(); 
		afficheur_4.writeInFile("EPOQUE");
		assertFalse(results4.isEmpty());
		
		Set<Integer> results5 = afficheur_5.getResults(); 
		afficheur_5.writeInFile("EPOQUE");
		assertFalse(results5.isEmpty());
		
		Logger.getGlobal().info("	result of " + " " + afficheur_1.toString());
        Logger.getGlobal().info("	result of " + " " + afficheur_2.toString());
        Logger.getGlobal().info("	result of " + " " + afficheur_3.toString());
        Logger.getGlobal().info("	result of " + " " + afficheur_4.toString());
        Logger.getGlobal().info("	result of " + " " + afficheur_5.toString());	
	}

}
