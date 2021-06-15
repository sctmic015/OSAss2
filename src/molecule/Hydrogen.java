/** Hydrogen class for threads representing hydrogen atoms
 * @author SCTMIC015 and UCT Computer Science Department
 */


package molecule;

import java.util.concurrent.atomic.AtomicBoolean;

public class Hydrogen extends Thread {

	private static int hydrogenCounter =0;
	private int id;
	private Propane sharedPropane;
	public static AtomicBoolean flag = new AtomicBoolean(true); // Used to initially

	/**
	 * Constructor
	 * @param propane_obj
	 */
	public Hydrogen(Propane propane_obj) {
		Hydrogen.hydrogenCounter+=1;
		id=hydrogenCounter;
		this.sharedPropane = propane_obj;

		
	}

	/**
	 * Run method to start each thread
	 */
	public void run() {

	    try {

			if (flag.getAndSet(false) == true){
				sharedPropane.mutex.acquire();
				sharedPropane.hydrogensQ.release(8);
				sharedPropane.mutex.release();
			}


			sharedPropane.hydrogensQ.acquire();

			sharedPropane.barrier.phase1();
				sharedPropane.mutex.acquire();
					if (sharedPropane.getCarbon() == 0 && sharedPropane.getHydrogen() == 0){
						System.out.println("---Group ready for bonding---");
					}
					sharedPropane.addHydrogen();
					sharedPropane.bond("H"+this.id);
				sharedPropane.mutex.release();
			sharedPropane.barrier.phase2();
			sharedPropane.hydrogensQ.release();
		}
	   catch (InterruptedException ex) { /* not handling this  */}
	    //System.out.println(" ");
	}

}

