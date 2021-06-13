package molecule;

public class Carbon extends Thread {
	
	private static int carbonCounter =0;
	private int id;
	private Propane sharedPropane;
	private static boolean q = true;

	public Carbon(Propane propane_obj) {
		Carbon.carbonCounter+=1;       // Counts number of Carbons
		id=carbonCounter;              // id = num Carbons
		this.sharedPropane = propane_obj;  // propane object. Shared propane object the whole time
	}
	
	public void run() {
	    try {	 
	    	 // you will need to fix below
			sharedPropane.mutex.acquire();
			if (q == true){
				sharedPropane.carbonQ.release(3);
				q = false;
			}
			sharedPropane.mutex.release();
			//System.out.print();
			sharedPropane.carbonQ.acquire();
			sharedPropane.barrier.phase1();
			sharedPropane.bond("C"+ this.id);
			//sharedPropane.barrier.b_wait();

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