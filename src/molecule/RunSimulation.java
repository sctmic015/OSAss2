package molecule;

import molecule.Carbon;
import molecule.Propane;
import molecule.Hydrogen;

public class RunSimulation {

	/**
	 This class is sent the number of 
	 */
	public static void main(String[] args) {
		int no_hydrogens = Integer.parseInt(args[0]);
		int no_carbons = Integer.parseInt(args[1]);

		// Error checking to see whether there is the correct number of Hydrogen and Carbon atoms. Exits the program if there is not
		if (no_hydrogens / 8 != no_carbons / 3){
			System.out.println("There is an incorrect number of atoms.");
			System.out.println("Exiting program");
			System.exit(1);
		}

		System.out.println("Starting simulation with "+no_hydrogens+" H and "+no_carbons + " C");
		
		Carbon[] carbons = new Carbon[no_carbons];
		Hydrogen[] hydrogens = new Hydrogen[no_hydrogens];



		Propane sharedPropane = new Propane();
		
		for (int i=0;i<no_carbons;i++) {
			carbons[i]=new Carbon(sharedPropane); // call constructor
			carbons[i].start(); // start thread
		}
		for (int i=0;i<no_hydrogens;i++) {
			hydrogens[i]= new Hydrogen(sharedPropane);
			hydrogens[i].start();
		}
		
		


	}

}
