# Dijkstra algorithm using RMI

all: compile
	@echo "Build completed."

compile: Client/*.java Server/*.java
	javac Client/*.java
	javac Server/*.java

run: 
	java Server.Server ${REGISTRY_IP} ${PORTS} &
	java Client.Client ${MAP_FILE} ${REGISTRY_IP} ${PORTS}

runserver:
	java Server.Server ${REGISTRY_IP} ${PORTS}
	
runclient:
	java Client.Client ${MAP_FILE} ${REGISTRY_IP} ${PORTS}

clean:
	rm Client/*.class
	rm Server/*.class




