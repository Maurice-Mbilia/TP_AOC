package implementationTest;

import static org.junit.Assert.assertFalse;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

public class DiffusionSequentielleTest {

	public static CapteurImpl capteur = new CapteurImpl("le capteur", Strategy.DiffusionSequentielle);

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

		assertFalse(capteur.isLock());
	}

	@Test 
	void executeTest() {
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		try {
			scheduledExecutorService.scheduleAtFixedRate(capteur::tick, 1, 10, TimeUnit.SECONDS);
			scheduledExecutorService.awaitTermination(60, TimeUnit.SECONDS);
		}catch (Exception e){
			Logger.getGlobal().info("The thread execution did not go well" + e.getMessage());
		} finally {
			if(scheduledExecutorService != null) {
				scheduledExecutorService.shutdown();
			}
		}
			Logger.getGlobal().info("scheduledExecutorService is done " + scheduledExecutorService.isTerminated());

			Set<Integer> results1 = afficheur_1.getResults(); 
			afficheur_1.writeInFile("_SEQUENTIELLE");

			Set<Integer> results2 = afficheur_2.getResults(); 
			afficheur_2.writeInFile("_SEQUENTIELLE");

			Set<Integer> results3 = afficheur_3.getResults(); 
			afficheur_3.writeInFile("_SEQUENTIELLE");

			Set<Integer> results4 = afficheur_4.getResults(); 
			afficheur_4.writeInFile("_SEQUENTIELLE");
			
			Set<Integer> results5 = afficheur_5.getResults(); 
			afficheur_5.writeInFile("_SEQUENTIELLE");

			assertTrue(results1.size() == results2.size() && results2.size() == results3.size() && results3.size() == results4.size() && results4.size() == results5.size());

			for (int i = 0; i < results1.size(); i++) {
				assertTrue((results1.toArray()[i] == results2.toArray()[i]) && (results1.toArray()[i] == results3.toArray()[i]) && (results1.toArray()[i] == results4.toArray()[i]) && (results1.toArray()[i] == results5.toArray()[i]));

				
				assertTrue((results2.toArray()[i] == results3.toArray()[i]) && (results2.toArray()[i] == results4.toArray()[i]));

                assertSame(results3.toArray()[i], results4.toArray()[i], "The values are equals ");


			}

			Logger.getGlobal().info("	result of " + " " + afficheur_1.toString());
	        Logger.getGlobal().info("	result of " + " " + afficheur_2.toString());
	        Logger.getGlobal().info("	result of " + " " + afficheur_3.toString());
	        Logger.getGlobal().info("	result of " + " " + afficheur_4.toString());
	        Logger.getGlobal().info("	result of " + " " + afficheur_5.toString());	
		
	}
}