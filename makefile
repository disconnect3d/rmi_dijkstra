# Dijkstra algorithm using RMI

LIBS  = 
CFLAGS = -std=c++11

#SRC=$(shell find source -name '*.cpp')

JDK= /usr/lib/jvm/java-8-oracle
CLASSPATH=dist
LD_LIBRARY_PATH=dist
DIST=dist

# Compile all
all: $(SRC)
	clear; source ./launcher
	
# Compile all and execute
xm: all
	./launcher viewer 8
	
# Execute
x:
	./launcher viewer 8
	
java: server client $(DIST)/java.policy 

server: $(DIST)/Server.java $(DIST)/ServerInterface.java $(DIST)/TaskData.java
	cd $(DIST); \
	$(JDK)/bin/javac ServerInterface.java Server.java TaskData.java
	cd $(DIST); $(JDK)/bin/javah -jni Server
	
client: $(DIST)/Client.java 
	cd $(DIST); $(JDK)/bin/javac Client.java	
	
# Clean binary files
clean:
	cd $(DIST); rm -f client *.class *.so *.h *.o







