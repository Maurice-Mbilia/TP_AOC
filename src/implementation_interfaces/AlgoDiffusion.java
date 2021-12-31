package implementation_interfaces;

import class_impl.CapteurImpl;

public interface AlgoDiffusion {
	
		public void configure(CapteurImpl capteur);

		public Integer valueRead(Observer observer);

		void valueWritten();

}
