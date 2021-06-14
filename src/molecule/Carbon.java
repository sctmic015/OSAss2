package molecule;

import java.util.concurrent.atomic.AtomicBoolean;

public class Carbon extends Thread {
	
	private static int carbonCounter =0;
	private int id;
	private Propane sharedPropane;
	public AtomicBoolean flag = new AtomicBoolean(true);

	public Carbon(Propane propane_obj) {
		Carbon.carbonCounter+=1;       // Counts number of Carbons
		id=carbonCounter;              // id = num Carbons
		this.sharedPropane = propane_obj;  // propane object. Shared propane object the whole time
	}
	
	public void run() {
	    try {


			sharedPropane.carbonQ.acquire();

			sharedPropane.barrier.phase1();
			sharedPropane.mutex.release();
			sharedPropane.bond("C"+this.id);
			sharedPropane.mutex.acquire();
			sharedPropane.barrier.phase2();
			sharedPropane.carbonQ.release();


		}
	    catch (InterruptedException ex) { /* not handling this  */}
	   // System.out.println(" ");
	}

}
/**
 sharedPropane.mutex.acquire();
 if (q == true){
 sharedPropane.carbonQ.release(3);
 q = false;
 }
 sharedPropane.mutex.release();
 //System.out.print();
 sharedPropane.carbonQ.acquire();**/