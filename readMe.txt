MakeFIle Details:
Please use all makefile commands from /SCTMIC015_OS2 directory
There is an option to compile the code and options to run the program with a small selection of initial arguements
make -> compiles the code
make run8_3 -> Runs the simulation for 8 hydrogen and 3 carbon molecules
make run16_6 -> Runs the simulation for 16 hydrogen and 6 carbon molecules
make run24_9 -> Runs the simulation for 24 hydrogen and 9 carbon molecules
make run32_12 -> Runs the simulation for 32 hydrogen and 12 carbon molecules
make run40_15 -> Runs the simulation for 40 hydrogen and 15 carbon molecules
make runError -> Runs the simulation for 25 hydrogen and 9 carbon molecules. This will give an error

How to run:
From within the bin directory type:
    java molecule.RunSimulation "number of hydrogen molecules" "number of carbon molecules"

Notes on comments within code:
In the RunSimulation class I commented above the error checking.
In the Carbon class I commented above and on the side of relevant pieces of code explaining what I was doing.
I did not comment in the Hydrogen class as the logic was identical to that of the Carbon class

A brief explanation of my algorithm.

I thought of this problem using a metaphor of a rollercoaster(the barriers) ridden by boys(Hydrogens) and girls(Carbons). A whole lot of boys and girls would arrive
in order to ride this rollercoaster. In order to sort out the chaos 8 boys would have to get in a line and 3 girls in a line. When this has happened
the people in the line would be put on the rollercoaster that would run. Whilst the roller coaster was running another group of 8 boys and 3 girls would line up.
When the rollercoaster ride would finish the people on the rollercoaster would exit giving space for the people in the line to get on the rollercoaster. This
process would continue until there are no people left.

If the program was not aborted due to a mismatch in atom numbers then the following would occur in the hydrogen and carbon classes.

To implement this algorithm I created two atomicBoolean flags in the carbon and hydrogen class that were initially true. These were set to false in a for loop
that also initialised the first Hydrogen (8 permits) and Carbon (3 permits) queues. I then acqired on these queues until all these threads are forced to wait at the
the first barrier (barrier.phase1()). The 11 threads would then enter this barrier. Each thread in this barrier would individually acquire the sharedPropane mutex
and:
1)print out "---Group Ready For Bonding" if there are 0 carbon and hydrogen atoms
2)update the relevant atom count in the sharedPropance object
3)perform the bond action
All 11 threads would then be forced to wait at the second barrier (barrier.phase2()). When all 11 threads reach this barrier the barrier is breached and those threads
can then release locks on the hydrogen and carbon queues. Thereby, allowing threads still waiting on the queues to procede to the first barrier where the process
repeats itself until no threads are left.

The use of the barriers ensure that threads are being processed to bond in batches of 11 at a time. The queues ensure that there are always 8 hydrogen and 3
carbon threads waiting to enter the first barrier as required. The critical section between the two barriers was locked to ensure that no values in the sharedPropane
object were falsely changed due to race conditions.


