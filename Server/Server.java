package Server;

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

import Shared.*;

public class Server extends UnicastRemoteObject implements ServerInterface {
    public Server() throws RemoteException {
        super();
    }

    public TaskData calculate(TaskData data) throws RemoteException {
        System.out.println("Server data a=" + data.a + ", b=" + data.b);
        data.a = data.a + 10;
        data.b = data.b + 10;
        return data;
    }

    public static void main(String args[]) throws Exception {
        if (args.length < 2) {
            System.out.println("Server usage: <host> <port>");
            return;
        }

        String host = args[0];
        String port = args[1];

        String bindAddr = "//" + host + ":" + port + "/dijkstra";

        //if (System.getSecurityManager() == null)
        Naming.rebind(bindAddr, new Server());

        System.out.println("Server started " + bindAddr);
    }
}
