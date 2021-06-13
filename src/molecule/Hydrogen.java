package molecule;

public class Hydrogen extends Thread {

	private static int hydrogenCounter =0;
	private int id;
	private Propane sharedPropane;
	private static boolean q2 = true;


	public Hydrogen(Propane propane_obj) {
		Hydrogen.hydrogenCounter+=1;
		id=hydrogenCounter;
		this.sharedPropane = propane_obj;

		
	}
	
	public void run() {

	    try {
	    	 // you will need to fix below
			sharedPropane.mutex.acquire();
			if (q2 == true){
				sharedPropane.hydrogensQ.release(8);
				q2 = false;
			}
			sharedPropane.mutex.release();
			sharedPropane.hydrogensQ.acquire();
			sharedPropane.barrier.phase1();
			sharedPropane.bond("H"+ this.id);
			//sharedPropane.barrier.b_wait();
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