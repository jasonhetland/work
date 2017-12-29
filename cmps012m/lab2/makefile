JAVASRC = FileReverse.java
SOURCES = README makefile FileReverse.java
MAINCLASS = FileReverse
CLASSES = FileReverse.class
JARFILE = FileReverse
JARCLASSES = FileReverse.class
SUBMIT = submit cmps012b-pt.u14 lab2

all: FileReverse

FileReverse: FileReverse.class
	echo Main-class: FileReverse > Manifest
	jar cvfm FileReverse Manifest FileReverse.class
	rm Manifest
	chmod +x FileReverse

FileReverse.class: FileReverse.java
	javac -Xlint FileReverse.java
clean:
	rm FileReverse.class FileReverse
submit: README makefile  FileReverse
	submit cmps012b-pt.u14 lab2 README makefile FileReverse.java
