package Client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.*;
import java.util.concurrent.*;


public class DijkstraClient {
    public DijkstraClient(String[] serverPorts) {
        workerServers = new ServerInterface[serverPorts.length];
        
        for(int i=0; i<serverPorts.length; ++i)
            workerServers[i] = (ServerInterface) Naming.lookup("//" + host + ":" + serverPort[i] + "/dijkstra");
        
        executorService = Executors.newFixedThreadPool(workerServers);
    }
    
    private int workerServersCount;
    private ExecutorService executorService;
    private ServerInterface[] workerServers;
    //private Map map;

    public void run() {
        Integer[] distances = new Integer[nodesCount];
        Integer[] prevNodes = new Integer[nodesCount];
        
        for(int i=0; i<nodesCount; ++i)
            distances[i] = prevNodes[i] = Integer.MAX_VALUE;
        
        int initialNode = 0; // TODO
        int currentNode = initialNode;
        
        System.out.println("Sending initial data to workers...");
        for(int i=0; i<serverPorts.length; ++i)
            executorService.execute(new Runnable() {
                System.out.println("Client worker " + i);
            });
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        System.out.println("Client all workers done - Repeating");
        
        for(int i=0; i<serverPorts.length; ++i)
            executorService.execute(new Runnable() {
                System.out.println("Client worker " + i);
            });
        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        System.out.println("Client all workers done");
    }
}