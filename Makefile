
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
run:
	java -cp bin molecule.RunSimulation $(hydrogen) $(carbon)
cleandocs:
	rm -r docs/*

