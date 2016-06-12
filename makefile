# Dijkstra algorithm using RMI

all: compile run
	echo "Build & run completed."

compile: Client/*.java Server/*.java
	javac Client/*.java
	javac Server/*.java

run: 
	java Server.Server ${REGISTRY_IP} ${PORTS} &
	java Client.Client ${MAP_FILE} ${REGISTRY_IP} ${PORTS}

runServer:
	java Server.Server ${PORTS}
	
runClient:
	java Client.Client ${PORTS}
		
clean:
	rm Client/*.class
	rm Server/*.class




