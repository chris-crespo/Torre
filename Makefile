.PHONY: all test clean

sources = $(shell find src -name "*.java")
files = $(shell find {src,test} -name "*.java")

all: clean run

run: src/*.java
	javac $(sources) -d class -Xlint:unchecked --enable-preview --release 17
	java --enable-preview -cp class Main

clean: 
	rm -rf class/*

test: src/*.java test/*.java
	javac $(files) -d class -cp lib/junit.jar
	java -jar lib/junit.jar -cp class/ --scan-classpath
