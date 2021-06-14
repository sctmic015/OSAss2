package molecule;

public class Carbon extends Thread {
	
	private static int carbonCounter =0;
	private int id;
	private Propane sharedPropane;
	private boolean flag = true;

	public Carbon(Propane propane_obj) {
		Carbon.carbonCounter+=1;       // Counts number of Carbons
		id=carbonCounter;              // id = num Carbons
		this.sharedPropane = propane_obj;  // propane object. Shared propane object the whole time
	}
	
	public void run() {
	    try {
	    	sharedPropane.mutex.acquire();
	    	if (sharedPropane.carbonQ.availablePermits() == 0 && sharedPropane.hydrogensQ.availablePermits() == 0){
	    		System.out.println("Bonding");
	    		sharedPropane.carbonQ.release(3);
	    		sharedPropane.hydrogensQ.release(8);
			}
			sharedPropane.mutex.release();

	    	sharedPropane.carbonQ.acquire();
	    	sharedPropane.bond("C"+this.id);

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