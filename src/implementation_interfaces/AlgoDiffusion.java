package implementation_interfaces;

import class_impl.CapteurImpl;

public interface AlgoDiffusion {
	
		public void configure(CapteurImpl captor);

		public Integer execute (Observer observer);

		public void valueWritten();

}
