/** Carbon class for threads representing carbon atoms
 * @author SCTMIC015 and UCT Computer Science Department
 */

package molecule;

import java.util.concurrent.atomic.AtomicBoolean;

public class Carbon extends Thread {
	
	private static int carbonCounter =0;
	private int id;
	private Propane sharedPropane;
	public static AtomicBoolean flag = new AtomicBoolean(true);

	/**
	 * Constructor
	 * @param propane_obj
	 */
	public Carbon(Propane propane_obj) {
		Carbon.carbonCounter+=1;       // Counts number of Carbons
		id=carbonCounter;              // id = num Carbons
		this.sharedPropane = propane_obj;  // propane object. Shared propane object the whole time
	}

	/**
	 * Run method to start each thread
	 */
	public void run() {
	    try {

	    	// Test to set the queue's when the first thread is active. Ignores once flag has been changed
			// This can be avoided by setting the queues to 3 and 8 respectively in the propane class
			// This only done once at the beginning. The queues are otherwise reset at the end after barrier.
			if (flag.getAndSet(false) == true){
				sharedPropane.mutex.acquire();
				sharedPropane.carbonQ.release(3);
				sharedPropane.mutex.release();
			}


			sharedPropane.carbonQ.acquire();  // Ensures that only the three carbon threads from the queue pass this point

			sharedPropane.barrier.phase1(); // Ensures that we can only proceed when 8 hydrogens and 3 carbons have reached this point. Barrier is then activated again
				sharedPropane.mutex.acquire(); // Acquire mutex semaphore when reading and writing to shared memory in the propane class
					if (sharedPropane.getCarbon() == 0 && sharedPropane.getHydrogen() == 0){ // Prints group ready for bonding if the number of carbons and hydrogens equals 0
						System.out.println("---Group ready for bonding---");
					}
					sharedPropane.addCarbon(); // adds carbon
					sharedPropane.bond("C"+this.id); // bonds
				sharedPropane.mutex.release(); // release mutex semaphore
			sharedPropane.barrier.phase2(); // phase 2 barrier allows the same 11 threads from above to continue executing once they have all arrived at this barrier. Also resets phase 1 barrier
			sharedPropane.carbonQ.release(); // Ensures that the carbon threads release the carbonQ three times so that the code process repeats itself with new threads


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