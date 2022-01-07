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
	
	
	@Test
	void valueWritten() {
		
		int value = 1;
		capteur.tick();
		AlgoDiffusion strategy = capteur.getStrategy(); 
		Integer valueAfterTick = strategy.valueRead(canal_1);
		assertEquals(value, valueAfterTick);
	}
	@Test
	void lock() {
		assertTrue(capteur.isLock());
	}
	
	@Test
	void atomiqueTest() {
		ScheduledExecutorService simulator = Executors.newSingleThreadScheduledExecutor();
		try { 
			simulator.scheduleAtFixedRate(capteur::tick, 1, 2	, TimeUnit.MINUTES);
			simulator.awaitTermination(2, TimeUnit.MINUTES); 
			
		} catch (Exception e) {
			Logger.getGlobal().severe("L'exécution du thread ne s'est pas bien passée " + e.getMessage());
		} finally {
			if (simulator != null) {
				simulator.shutdown();
			}
		}
		Logger.getGlobal().severe("simulator is terminate " + simulator.isTerminated());
		
		Set<Integer> results1 = afficheur_1.getResults(); 
		afficheur_1.writeInFile("_ATOMIQUE");
		
		Set<Integer> results2 = afficheur_2.getResults(); 
		afficheur_1.writeInFile("_ATOMIQUE");
		
		Set<Integer> results3 = afficheur_3.getResults(); 
		afficheur_1.writeInFile("_ATOMIQUE");
		
		Set<Integer> results4 = afficheur_4.getResults(); 
		afficheur_1.writeInFile("_ATOMIQUE");
		
		assertTrue(results1.size()==results2.size() && results2.size()==results3.size() && results3.size()==results4.size(), "Les sous suites sont tous egaux");
		
		for(int i = 0; i < results1.size(); i++) {
			
			assertTrue(results1.toArray()[i] == results2.toArray()[i] && results1.toArray()[i] == results3.toArray()[i] && results1.toArray()[i] == results4.toArray()[i], "Les sous suites sont tous egaux");
			
			assertTrue(results2.toArray()[i] == results3.toArray()[i]
                    && results2.toArray()[i] == results4.toArray()[i]
            , "Les sous suites sont tous egaux");
			
			assertSame(results3.toArray()[i], results4.toArray()[i], "Les sous suites sont tous egaux");	
			
		}
		
	
		Logger.getGlobal().severe("-------- resultat de " + afficheur_1.toString());
        Logger.getGlobal().info("-------- resultat de " + afficheur_2.toString());
        Logger.getGlobal().warning("-------- resultat de " + afficheur_3.toString());
        Logger.getGlobal().log(Level.OFF, "-------- resultat de " + afficheur_4.toString());


	
	
	
	}

}


