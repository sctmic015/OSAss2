package molecule;

public class Hydrogen extends Thread {

	private static int hydrogenCounter =0;
	private int id;
	private Propane sharedPropane;
	private boolean flag = true;


	public Hydrogen(Propane propane_obj) {
		Hydrogen.hydrogenCounter+=1;
		id=hydrogenCounter;
		this.sharedPropane = propane_obj;

		
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

			sharedPropane.hydrogensQ.acquire();
			sharedPropane.bond("H"+this.id);


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