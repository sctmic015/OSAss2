
# Makefile: SCTMIC015

JAVAC = /usr/bin/javac
.SUFFIXES: .java .class

SRCDIR=src/molecule
BINDIR=bin
DOCDIR=doc

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=BarrierReusable.class Propane.class Carbon.class Hydrogen.class RunSimulation.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)
	
docs:
	javadoc -d $(DOCDIR) $(SRCDIR)/*.java
clean:
	rm $(BINDIR)/molecule/*.class
run8_3:
	java -cp bin molecule.RunSimulation "8" "3"
run16_6:
	java -cp bin molecule.RunSimulation "16" "6"
run24_9:
	java -cp bin molecule.RunSimulation "24" "9"
run32_12:
	java -cp bin molecule.RunSimulation "32" "12"
run40_15:
	java -cp bin molecule.RunSimulation "40" "15"
runError:
	java -cp bin molecule.RunSimulation "25" "9"
cleandocs:
	rm -r docs/*

