FLAGS = -std=c99 -Wall
SOURCES = FileReverse.c
OBJECTS = FileReverse.o
EXEBIN = FileReverse

all: FileReverse

FileReverse : FileReverse.o
	gcc -o FileReverse FileReverse.o

FileReverse.o : FileReverse.c
	gcc -c -std=c99 -Wall FileReverse.c

clean :
	rm FileReverse FileReverse.o
