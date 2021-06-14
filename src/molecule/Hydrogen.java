package molecule;

import java.util.concurrent.atomic.AtomicBoolean;

public class Hydrogen extends Thread {

	private static int hydrogenCounter =0;
	private int id;
	private Propane sharedPropane;
	private AtomicBoolean flag = new AtomicBoolean(true);


	public Hydrogen(Propane propane_obj) {
		Hydrogen.hydrogenCounter+=1;
		id=hydrogenCounter;
		this.sharedPropane = propane_obj;

		
	}
	
	public void run() {

	    try {


			sharedPropane.hydrogensQ.acquire();

			sharedPropane.barrier.phase1();
			sharedPropane.mutex.acquire();
			sharedPropane.bond("H"+this.id);
			sharedPropane.mutex.release();
			sharedPropane.barrier.phase2();
			sharedPropane.hydrogensQ.release();
		}
	   catch (InterruptedException ex) { /* not handling this  */}
	    //System.out.println(" ");
	}

}

/**
 sharedPropane.mutex.acquire();
 if (q2 == true){
 sharedPropane.hydrogensQ.release(8);
 q2 = false;
 }
 sharedPropane.mutex.release();
 sharedPropane.hydrogensQ.acquire(); **/