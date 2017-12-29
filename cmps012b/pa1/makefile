JAVASRC = Extrema.java
SOURCES = README makefile Extrema.java
MAINCLASS = Extrema
CLASSES = Extrema.class
JARFILE = myExtrema
JARCLASSES = Extrema.class
SUBMIT = submit cmps012b-pt.u14 pa1

all: myExtrema
        myExtrema:Extrema.class
	echo Main-class: Extrema > Manifest
	jar cvfm myExtrema Manifest Extrema.class
	rm Manifest
	chmod +x myExtrema

Extrema.class:Extrema.java
	javac -Xlint Extrema.java
clean:
	rm -f Extrema.class myExtrema
		      
submit:README makefile Extrema.java
	submit cmps012b-pt.u14 pa1 README makefile Extrema.java
			           
			           
