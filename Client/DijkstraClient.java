package Client;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.*;
import java.util.*;

import Shared.*;

public class DijkstraClient {
    public DijkstraClient(String host, String[] serverPorts) throws Exception {
        workerServersCount = serverPorts.length;
        workerServers = new ServerInterface[workerServersCount];
        
        for(int i=0; i<workerServersCount; ++i) {
            Registry reg = LocateRegistry.getRegistry(host, Integer.parseInt(serverPorts[i]));
            workerServers[i] = (ServerInterface) reg.lookup("server");
        }
        executor = Executors.newFixedThreadPool(workerServersCount);
    }
    
    private int workerServersCount;
    private ExecutorService executor;
    private ServerInterface[] workerServers;
    //private Map map;

    public void run() throws InterruptedException {
        int nodesCount = 10; // TODO
        Integer[] distances = new Integer[nodesCount];
        Integer[] prevNodes = new Integer[nodesCount];
        
        for(int i=0; i<nodesCount; ++i)
            distances[i] = prevNodes[i] = Integer.MAX_VALUE;
        
        int initialNode = 0; // TODO
        int currentNode = initialNode;
        
        System.out.println("Sending initial data to workers...");
        
        List<Callable<Object>> calls = new ArrayList<>();
        for(int i=0; i<workerServersCount; ++i) {
            final int workerIndex = i;
            calls.add(Executors.callable(() -> {
                System.out.println("Client worker " + workerIndex);
            }));
        }
        executor.invokeAll(calls);

        System.out.println("Client all workers done - Repeating");

        calls = new ArrayList<>();
        for(int i=0; i<workerServersCount; ++i) {
            final int workerIndex = i;
            calls.add(Executors.callable(() -> {
                System.out.println("Client worker " + workerIndex);
            }));
        }
        executor.invokeAll(calls);

        System.out.println("Client all workers done");
    }
}
