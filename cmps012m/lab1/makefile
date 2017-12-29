JAVASRC = hello.java hello2.java 
SOURCES = README makefile hello.java hello2.java 
MAINCLASS = hello2 
CLASSES = hello.class hello2.class 
JARFILE = myHello 
JARCLASSES = hello.class hello2.class
SUBMIT = submit cmps012b-pt.u14 lab1 
 
all: myHello
myHello:hello.class hello2.class
	echo Main-class: hello2 > Manifest
	jar cvfm myHello Manifest hello.class hello2.class
	rm Manifest
	chmod +x myHello 
 
hello.class hello2.class :hello.java hello2.java
	javac -Xlint hello.java hello2.java 
 
clean:
	rm -f hello.class hello2.class  myHello 
 
submit:README makefile hello.java hello2.java
	submit cmps012b-pt.u14 lab1 README makefile hello.java hello2.java 

 

