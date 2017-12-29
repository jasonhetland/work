JAVASRC = Search.java 
SOURCES = README makefile Search.java
MAINCLASS = Search
CLASSES = Search.class 
JARFILE = Search 
JARCLASSES = Search.class 
SUBMIT = submit cmps012b-pt.u14 pa2 
 
all: Search 
 
Search: Search.class
	echo Main-class: Search > Manifest
	jar cvfm Search Manifest Search.class
	rm Manifest
	chmod +x Search
 
Search.class: Search.java
	javac -Xlint Search.java 
 
clean:
	rm Search.class Search 
 
submit: README makefile Search.java
	submit cmps012b-pt.u14 pa2 README makefile Search.java 

