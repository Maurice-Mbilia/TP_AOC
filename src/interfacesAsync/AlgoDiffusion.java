package interfacesAsync;

import implementationAsync.CapteurImpl;

public interface AlgoDiffusion {
		/**
		 * 
		 * @param captor
		 */
		public void configure(CapteurImpl captor);
		
		/**
		 * 
		 * @param observer
		 * @return
		 */
		public Integer execute (Observer observer);
		/**
		 * 
		 */
		public void valueWritten();

}
