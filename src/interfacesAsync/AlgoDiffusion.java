package interfacesAsync;

import implementationAsync.CapteurImpl;

public interface AlgoDiffusion {
	
		public void configure(CapteurImpl captor);

		public Integer execute (Observer observer);

		public void valueWritten();

}
